//  Purchases
//
//  Copyright © 2019 RevenueCat, Inc. All rights reserved.
//
package com.revenuecat.purchases.interfaces

import com.revenuecat.purchases.Offerings
import com.revenuecat.purchases.PurchasesError

/**
 * Interface to be implemented when making calls to fetch [Offering].
 */
@Deprecated(
    "Renamed to ReceiveOfferingsCallback",
    ReplaceWith("ReceiveOfferingsCallback")
)
interface ReceiveOfferingsListener {
    /**
     * Will be called after a successful fetch of offerings.
     *
     * @param offerings
     */
    fun onReceived(offerings: Offerings)

    /**
     * Will be called after an error fetching offerings
     *
     * @param error A [PurchasesError] containing the reason for the failure when fetching offerings.
     */
    fun onError(error: PurchasesError)
}
