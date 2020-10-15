package com.qonversion.android.sdk.billing

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchaseHistoryRecord
import java.text.SimpleDateFormat
import java.util.*

fun BillingResult.toReadableDescription() =
    "DebugMessage: $debugMessage; ResponseCode: ${responseCode.getBillingResponseCodeName()}."

fun PurchaseHistoryRecord.toReadableDescription() =
    "ProductId: ${this.sku}; PurchaseTime: ${this.purchaseTime.convertLongToTime()}; PurchaseToken: ${this.purchaseToken}"

fun Purchase.toReadableDescription() =
    "ProductId: ${this.sku}; OrderId: ${this.orderId}; PurchaseToken: ${this.purchaseToken}"

private fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    return format.format(date)
}

private fun @receiver:BillingClient.BillingResponseCode Int.getBillingResponseCodeName(): String {
    return when (this) {
        BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED -> "FEATURE_NOT_SUPPORTED"
        BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> "SERVICE_DISCONNECTED"
        BillingClient.BillingResponseCode.OK -> "OK"
        BillingClient.BillingResponseCode.USER_CANCELED -> "USER_CANCELED"
        BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> "SERVICE_UNAVAILABLE"
        BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> "BILLING_UNAVAILABLE"
        BillingClient.BillingResponseCode.ITEM_UNAVAILABLE -> "ITEM_UNAVAILABLE"
        BillingClient.BillingResponseCode.DEVELOPER_ERROR -> "DEVELOPER_ERROR"
        BillingClient.BillingResponseCode.ERROR -> "ERROR"
        BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> "ITEM_ALREADY_OWNED"
        BillingClient.BillingResponseCode.ITEM_NOT_OWNED -> "ITEM_NOT_OWNED"
        BillingClient.BillingResponseCode.SERVICE_TIMEOUT -> "SERVICE_TIMEOUT"
        else -> "$this"
    }
}