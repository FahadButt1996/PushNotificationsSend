package com.ttl.pushnotificationsend

import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response


/**
 * Created by fahad.waqar on 06-Aug-18.
 */
class SendNotification  : AppCompatActivity() , View.OnClickListener {

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.send_notification ->{
                callApi()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MultiDex.install(this)
        setContentView(R.layout.activity_main)
        send_notification.setOnClickListener(this@SendNotification)
    }

    private fun callApi() {
        var saveObserve: Observable<Response<Any>>? = null
        var notification : PushNotificationSendModel = PushNotificationSendModel()

        notification?.to = "/topics/PGC"

        val notification_json : JSONObject = JSONObject()
        notification_json.put("body" , notification_body.text.toString())
        notification_json.put("title" , notification_title.text.toString())

        val data_json : JSONObject = JSONObject()
        data_json.put("body" , data_body.text.toString())
        data_json.put("title" , data_title.text.toString())

        notification.data = JSONArray()
        notification.data.put(data_json)

        notification.notification = JSONArray()
        notification.notification.put(notification_json)

        saveObserve = ApiInterface.notification().admissionSave("fcm/send" , notification )
        saveObserve.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { success ->
                            if (success.isSuccessful) {
                                Toast.makeText(this@SendNotification, "success", Toast.LENGTH_SHORT).show()
                            }

                        },

                        { error ->
                            Toast.makeText(this@SendNotification , "Something went wriong" , Toast.LENGTH_SHORT).show()

                        })
    }
}