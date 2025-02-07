package com.revenuecat.purchases

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This class contains all the entitlements associated to the user.
 * @property all Map of all EntitlementInfo [EntitlementInfo] objects (active and inactive) keyed by
 * entitlement identifier.
 */
@Parcelize
class EntitlementInfos constructor(
    val all: Map<String, EntitlementInfo>
) : Parcelable {

    /**
     * Dictionary of active [EntitlementInfo] objects keyed by entitlement identifier.
     */
    val active = all.filter { it.value.isActive }

    /**
     * Retrieves an specific entitlementInfo by its entitlement identifier. It's equivalent to
     * accessing the `all` map by entitlement identifier.
     */
    operator fun get(s: String) = all[s]

    /** @suppress */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntitlementInfos

        if (all != other.all) return false
        if (active != other.active) return false

        return true
    }

    override fun hashCode(): Int {
        var result = all.hashCode()
        result = 31 * result + active.hashCode()
        return result
    }
}
