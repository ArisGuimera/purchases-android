package com.revenuecat.purchases

import com.revenuecat.purchases.interfaces.ProductChangeCallback
import com.revenuecat.purchases.interfaces.PurchaseCallback

internal data class PurchasesState(
    val allowSharingPlayStoreAccount: Boolean? = null,
    val purchaseCallbacks: Map<String, PurchaseCallback> = emptyMap(),
    val productChangeCallback: ProductChangeCallback? = null,
    val appInBackground: Boolean = true,
    val firstTimeInForeground: Boolean = true
)
