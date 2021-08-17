package com.modcom.simon.pie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class DetailActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd
    private  lateinit var  webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        webView = findViewById(R.id.webview)

        //adding back button
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cardnumber = intent.getIntExtra("cardnumber",0)
        when(cardnumber){
            0->{
                webView.loadUrl("file:///android_asset/about.html")


            }
            1->{
                webView.loadUrl("file:///android_asset/causes.html")

            }
            2->{
                webView.loadUrl("file:///android_asset/symptoms.html")


            }
            3->{
                webView.loadUrl("file:///android_asset/prevention.html")


            }
            4->{
                 //tollfree to be implemented


            }
            5->{
                //location to be implemented


            }
        }
        initializeAds()

        loadInterstitialAd()
    }
    private fun initializeAds() {
        MobileAds.initialize(this) { }


    }
    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("inter", adError.message)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd.show(this@DetailActivity)

            }
        })
    }


}