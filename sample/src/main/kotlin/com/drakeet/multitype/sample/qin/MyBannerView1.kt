package com.drakeet.multitype.sample.qin

import android.net.http.SslError
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R


class MyBannerView1 : ItemViewBinder<MyBannerView1.Bean1, MyBannerView1.ViewHolder1>() {

    override fun onCreateViewHolder(@NonNull inflater: LayoutInflater, @NonNull parent: ViewGroup): ViewHolder1 {
        val view1: View = inflater.inflate(R.layout.item_banner1, parent, false)
        return ViewHolder1(view1)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder1, @NonNull bean1: Bean1) { //这个我们视为WebView控件，可以远程控制其显示
        holder.webView.loadUrl(bean1.url)
    }

    inner class ViewHolder1(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var webView: WebView

        init {
            webView = itemView.findViewById(R.id.webView)
            val webSettings = webView.settings
            //支持缩放，默认为true。
            webSettings.setSupportZoom(false)
            //调整图片至适合webview的大小
            webSettings.useWideViewPort = true
            // 缩放至屏幕的大小
            webSettings.loadWithOverviewMode = true
            //设置默认编码
            webSettings.defaultTextEncodingName = "utf-8"
            webSettings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                    handler.proceed()
                }
            }
        }
    }

    class Bean1(var url: String)
}