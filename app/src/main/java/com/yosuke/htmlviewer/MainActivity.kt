package com.yosuke.htmlviewer

import android.content.Intent
import android.os.Bundle
import android.view.View.*
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFileSelector()
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN or
                SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != CHOSE_FILE_CODE || resultCode != RESULT_OK || data == null) return
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(data.data?.toString() ?: "")
    }

    private fun openFileSelector() {
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "text/html"
            startActivityForResult(it, CHOSE_FILE_CODE)
        }
    }

    companion object {
        private const val CHOSE_FILE_CODE = 12345
    }
}
