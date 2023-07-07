package net.budgetzen.app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById<WebView>(R.id.webview)
        webView.webViewClient = MyWebViewClient()

        webView.settings.javaScriptEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.domStorageEnabled = true

        webView.loadUrl("https://app.budgetzen.net/")
    }
}

private class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "app.budgetzen.net") {
            // This is my web site, so do not override; let my WebView load the page
            return false
        }

        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            view!!.context.startActivity(this)
        }
        return true
    }
}
