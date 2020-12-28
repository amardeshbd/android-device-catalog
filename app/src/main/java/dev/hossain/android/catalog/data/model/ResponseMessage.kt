package dev.hossain.android.catalog.data.model

import com.google.gson.annotations.SerializedName

/**
 * {"message":"Hello Bob! Welcome to mock server."}
 */
class ResponseMessage(
    @SerializedName("message")
    var messsage: String? = null
)
