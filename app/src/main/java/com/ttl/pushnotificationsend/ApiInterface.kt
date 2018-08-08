package com.ttl.pushnotificationsend

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.URI

/**
 * Created by fahad.waqar on 2/8/2018.
 */
interface ApiInterface {


    @Headers("Content-Type: application/json" ,"Authorization: key=AIzaSyBiuyS6Vkm2-ycEP-qaL1kI0U3lM2huc5E")
    @POST()
    fun admissionSave(@Url url : String , @Body notification :PushNotificationSendModel): Observable<Response<Any>>

    companion object {
        fun notification() : ApiInterface {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://fcm.googleapis.com/")
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}