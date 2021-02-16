package com.britishbroadcast.myinternalapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import java.io.*

class MainActivity : AppCompatActivity() {

    private val fileName = "internal.html"
    private lateinit var file: File

    private lateinit var inputEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        file = File(fileName)

        inputEditText = findViewById(R.id.main_edittext)
        submitButton = findViewById(R.id.main_button)
        webView = findViewById(R.id.web_view)


        submitButton.setOnClickListener {
            writeToInternalStorage()
        }

        //writeToInternalStorage()

        readFromInternalStorage()
    }

    fun writeToInternalStorage() {

        val fileOutput = openFileOutput(fileName, Context.MODE_PRIVATE)
        val text = inputEditText.text.toString()
        inputEditText.text.clear()

        fileOutput.write(text.toByteArray())
        fileOutput.close()

        try {
            readFromInternalStorage()
        } catch (e: Exception) {

            Log.d("TAG_X", "Error ${e.localizedMessage}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(java.lang.Exception::class)
    fun readFromInternalStorage() {

//        try {
        webView.loadUrl("file:///$filesDir/$file")
        showNotification()
//        } catch (e: Exception) {
//            Log.d("TAG_X", e.toString())
//        }

//        val fileReader = FileReader("$filesDir/$fileName")
//        val bufferedReader = BufferedReader(fileReader)
//
//        val stringBuffer = StringBuffer()
//        var input: String? = ""
//
//        while( {input = bufferedReader.readLine(); input }()  != null ){
//            stringBuffer.append(input)
//        }
//
//        Log.d("TAG_X_fromInternal", stringBuffer.toString())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification() {

        val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Since android Oreo (8.0)
        val notificationChannel = NotificationChannel(
                "111",
                "Main notification channel.",
                NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat
                .Builder(this,"CHANNEL")
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentText("This is for Shrey")
                .setContentTitle("Web Page Ready!!!")
                .setSmallIcon(R.drawable.ic_launcher_background)
//                .build()
        notification.setChannelId("111")

        Log.d("TAG_X", "Show notification")
        notificationManager.notify(111, notification.build())

    }
}










