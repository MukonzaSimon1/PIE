package com.modcom.simon.pie

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var mAdView: AdView
    private lateinit var tvName: TextView
    companion object {
        private const val SLIDE_NUMBER = 10
    }

    private lateinit var imageSlider: ImageSlider
    private val imageUrls = arrayListOf(
        "http://i.imgur.com/CqmBjo5.jpg",
        "http://i.imgur.com/zkaAooq.jpg",
        "http://i.imgur.com/0gqnEaY.jpg"

    )
    private lateinit var card_about: CircularRevealCardView
    private lateinit var card_causes: CircularRevealCardView
    private lateinit var card_symptoms: CircularRevealCardView
    private lateinit var card_prevention: CircularRevealCardView
    private lateinit var card_tollfree: CircularRevealCardView
    private lateinit var card_location: CircularRevealCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tvName = findViewById(R.id.tvName)
        //receiving data that was sent to previous activity
        val username = intent.getStringExtra("username")
        tvName.text = username

        //initializing the cards
        card_about = findViewById(R.id.card_about)
        card_causes = findViewById(R.id.card_causes)
        card_symptoms = findViewById(R.id.card_symptoms)
        card_prevention = findViewById(R.id.card_prevention)
        card_tollfree = findViewById(R.id.card_tollfree)
        card_location = findViewById(R.id.card_location)

        imageSlider = findViewById(R.id.image_slider)
        card_about.setOnClickListener {
            val cardnumber = 0
            navigateToDetails(cardnumber)


        }

        card_causes.setOnClickListener {
            val cardnumber = 1
            navigateToDetails(cardnumber)
        }

        card_symptoms.setOnClickListener {
            val cardnumber = 2
            navigateToDetails(cardnumber)
        }

        card_prevention.setOnClickListener {
            val cardnumber = 3
            navigateToDetails(cardnumber)
        }

        card_tollfree.setOnClickListener {
            val cardnumber = 4
            navigateToDetails(cardnumber)
        }

        card_location.setOnClickListener {
            val mapsActivity = Intent(this, MapsActivity::class.java)
            startActivity(mapsActivity)
        }
        //add this after setContentView(R.layout.activity_main)
        mAdView = findViewById(R.id.adView)
        loadCarousel()

        initializeAds()
        loadBannerAd()



    }

    private fun loadCarousel() {


        imageSlider.adapter = SliderAdapter(
            this,
            PicassoImageLoaderFactory(),
            imageUrls = imageUrls,
        )

    }

    private fun initializeAds() {
        MobileAds.initialize(this) { }


    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }



    private fun navigateToDetails(cardnumber: Int) {
        //navigating to details with cardnumber
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra("cardnumber", cardnumber)
        )

    }

    override fun onStart() {
        super.onStart()
        //requesting for runtime permissions
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

        }
    }

}
