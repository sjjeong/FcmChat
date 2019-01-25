package com.googry.fcmchat.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

object QrCodeGenerator {
    fun generate(url: String) =
        BarcodeEncoder().createBitmap(
            MultiFormatWriter().encode(
                url,
                BarcodeFormat.QR_CODE,
                200,
                200
            )
        )
}