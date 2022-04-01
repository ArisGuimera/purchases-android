//  Purchases
//
//  Copyright © 2019 RevenueCat, Inc. All rights reserved.
//

package com.revenuecat.purchases.common

import android.os.Build
import com.revenuecat.purchases.Store
import com.revenuecat.purchases.common.networking.ETagManager
import com.revenuecat.purchases.common.networking.HTTPResult
import com.revenuecat.purchases.strings.NetworkStrings
import com.revenuecat.purchases.utils.filterNotNullValues
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class HTTPClient(
    private val appConfig: AppConfig,
    private val eTagManager: ETagManager
) {

    private val client = OkHttpClient.Builder().build()

    /** Performs a synchronous web request to the RevenueCat API
     * @param path The resource being requested
     * @param body The body of the request, for GET must be null
     * @param authenticationHeaders Map of headers, basic headers are added automatically
     * @return Result containing the HTTP response code and the parsed JSON body
     * @throws JSONException Thrown for any JSON errors, not thrown for returned HTTP error codes
     * @throws IOException Thrown for any unexpected errors, not thrown for returned HTTP error codes
     */
    @Throws(JSONException::class, IOException::class)
    fun performRequest(
        path: String,
        body: Map<String, Any?>?,
        authenticationHeaders: Map<String, String>,
        refreshETag: Boolean = false
    ): HTTPResult {
        val httpRequest = Request.Builder()

        val jsonBody = body?.convert()

        val fullURL: URL
        val urlPathWithVersion = "/v1$path"

        fullURL = URL(appConfig.baseURL, urlPathWithVersion)

        val headers = getHeaders(authenticationHeaders, urlPathWithVersion, refreshETag).toHeaders()

        httpRequest.url(fullURL)
            .headers(headers)

        jsonBody?.let {
            httpRequest.post(it.toString().toRequestBody())
        }

        val request = httpRequest.build()

        log(LogIntent.DEBUG, NetworkStrings.API_REQUEST_STARTED.format(request.method, path))
        client.newCall(request).execute().use { response ->
            log(LogIntent.DEBUG, NetworkStrings.API_REQUEST_COMPLETED.format(request.method, path, response.code))
            val payload = response.body?.string() ?: throw IOException(NetworkStrings.HTTP_RESPONSE_PAYLOAD_NULL)
            val callResult: HTTPResult? = eTagManager.getHTTPResultFromCacheOrBackend(
                response.code,
                payload,
                request,
                urlPathWithVersion,
                refreshETag
            )
            response.close()
            if (callResult == null) {
                log(LogIntent.WARNING, NetworkStrings.ETAG_RETRYING_CALL)
                return performRequest(path, body, authenticationHeaders, refreshETag = true)
            }
            return callResult
        }
    }

    fun clearCaches() {
        eTagManager.clearCaches()
    }

    private fun getHeaders(
        authenticationHeaders: Map<String, String>,
        urlPath: String,
        refreshETag: Boolean
    ): Map<String, String> {
        return mapOf(
            "Content-Type" to "application/json",
            "X-Platform" to getXPlatformHeader(),
            "X-Platform-Flavor" to appConfig.platformInfo.flavor,
            "X-Platform-Flavor-Version" to appConfig.platformInfo.version,
            "X-Platform-Version" to Build.VERSION.SDK_INT.toString(),
            "X-Version" to Config.frameworkVersion,
            "X-Client-Locale" to appConfig.languageTag,
            "X-Client-Version" to appConfig.versionName,
            "X-Observer-Mode-Enabled" to if (appConfig.finishTransactions) "false" else "true"
        )
            .plus(authenticationHeaders)
            .plus(eTagManager.getETagHeader(urlPath, refreshETag))
            .filterNotNullValues()
    }

    private fun Map<String, Any?>.convert(): JSONObject {
        val mapWithoutInnerMaps = mapValues { (_, value) ->
            value.tryCast<Map<String, Any?>>(ifSuccess = { convert() })
        }
        return JSONObject(mapWithoutInnerMaps)
    }

    // To avoid Java type erasure, we use a Kotlin inline function with a reified parameter
    // so that we can check the type on runtime.
    //
    // Doing something like:
    // if (value is Map<*, *>) (value as Map<String, Any?>).convert()
    //
    // Would give an unchecked cast warning due to Java type erasure
    private inline fun <reified T> Any?.tryCast(
        ifSuccess: T.() -> Any?
    ): Any? {
        return if (this is T) {
            this.ifSuccess()
        } else {
            this
        }
    }

    private fun getXPlatformHeader() = when (appConfig.store) {
        Store.AMAZON -> "amazon"
        else -> "android"
    }
}
