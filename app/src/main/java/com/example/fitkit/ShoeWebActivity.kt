package com.example.fitkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_shoe_web.*

class ShoeWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoe_web)
        kivi_web.webViewClient = WebViewClient()
        kivi_web.settings.javaScriptEnabled = true
        kivi_web.loadUrl(intent.getStringExtra("shoe try-on").toString())
    }
}