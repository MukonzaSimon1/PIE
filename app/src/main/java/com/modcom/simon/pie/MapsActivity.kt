package com.modcom.simon.pie

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.modcom.simon.pie.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setupGPSandLocateME()
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        //get gps infor and add my current location to the map
    }


        private fun  setupGPSandLocateME(){
            //requesting for runtime permissions
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    1)

            }else{
                //permission was granted
                val fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
                mMap.isMyLocationEnabled=true
                //getting last known location
                fusedLocationClient.lastLocation.addOnSuccessListener{ location->
                  if(location!=null){
                      mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                      //MAPTYPE_TERRAIN
                      //MAP_TYPE_HYBRID
                      //adding current location on the map
                      var icon: BitmapDescriptor =
                          BitmapDescriptorFactory.fromBitmap(
                              resizeMapIcon("car", 100, 100)
                          )
                      //source-startingpoint
                      val currentLocation= LatLng(location.latitude,location.longitude)
                      val destination = LatLng(-1.307780,36.732550 )


                      mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,16f))
                      mMap.addMarker(MarkerOptions().position(currentLocation).title("I am here").icon(icon))





                  } else(
                          Toast.makeText(
                              this,
                              "We cannot retrieve your current location",
                              Toast.LENGTH_SHORT
                          ).show()
                  )
                }
            }



        }

    private fun resizeMapIcon(iconname:String, height:Int, width:Int): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(
            resources,
            resources.getIdentifier(iconname,"drawable", packageName)
        )
        val resizedImage = Bitmap.createScaledBitmap(imageBitmap,width,height,false)
        return resizedImage
    }

}



