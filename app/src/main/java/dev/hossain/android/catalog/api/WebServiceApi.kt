package dev.hossain.android.catalog.api

import dev.hossain.android.catalog.data.model.ResponseMessage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Web service API.
 * TODO: Replace with your API
 */
interface WebServiceApi {
    @GET("/say/hello")
    fun hello(@Query("name") name: String): Single<ResponseMessage>
}
