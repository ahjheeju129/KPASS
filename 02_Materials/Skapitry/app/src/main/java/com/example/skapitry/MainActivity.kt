package com.example.skapitry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skapitry.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://apis.openapi.sk.com/puzzle/place/congestion/rltm/pois/7493463?lat=37.568085523663385&lng=126.98605733268329")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("appkey", "i13iX8RZs9ZIp55v3nER7DkoB3lsp3q5hxODSjL9")
            .build()

        val response = client.newCall(request).enqueue(object : Callback  {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }
            // main thread말고 별도의 thread에서 실행해야 함.
            override fun onResponse(call: Call, response: Response) {
                Thread{
                    var str = response.body?.string()
                    binding.text.text = str
                    println(str)
                }.start()
            }
        })


    }
}