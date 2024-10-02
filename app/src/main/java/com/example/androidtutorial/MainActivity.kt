package com.example.androidtutorial

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val viewModel: mainViewModel by viewModels()
    var tvText:TextView?=null;
     var listView :ListView ?=null;

    private val client = OkHttpClient()
     var adapter :ListAdapter ?=null
    val gson = Gson()
     lateinit  var context: Context;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView=findViewById(R.id.listView)
        context=this
        run("https://dummy.restapiexample.com/api/v1/employees");
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response)
            {
                response.body?.string()?.let { json ->
                    // Convert JSON to data class
                    try {
                        val user = gson.fromJson(json, ExampleJson2KtKotlin::class.java)
                        runOnUiThread {
                            adapter = ListAdapter(user.data, context)
                            listView?.adapter = adapter
                        }
                    } catch (e: Exception) {
                        e.printStackTrace() // Log parsing error
                    }

                }

            }

        })
    }

    data class ExampleJson2KtKotlin (

        @SerializedName("status"  ) var status  : String?         = null,
        @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf(),
        @SerializedName("message" ) var message : String?         = null

    )
    data class Data (

        @SerializedName("id"              ) var id             : Int?    = null,
        @SerializedName("employee_name"   ) var employeeName   : String? = null,
        @SerializedName("employee_salary" ) var employeeSalary : Int?    = null,
        @SerializedName("employee_age"    ) var employeeAge    : Int?    = null,
        @SerializedName("profile_image"   ) var profileImage   : String? = null

    )
}