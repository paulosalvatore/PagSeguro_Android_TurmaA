package br.com.paulosalvatore.pagseguroandroidturmab

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.checkSelfPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions

class MainActivity : AppCompatActivity(), LocationListener {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var map: GoogleMap

    val polygonOptions = PolygonOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        supportMapFragment.getMapAsync {
            map = it

            val latitude = -23.5739861
            val longitude = -46.6441447

            val latLng = LatLng(latitude, longitude)

            it.addMarker(MarkerOptions().position(latLng).title("GlobalCode"))

            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17F))

            startLocation()
        }
    }

    private fun startLocation() {
        if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
            && checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )

            return
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationProvider = LocationManager.GPS_PROVIDER

        var bestLocation: Location? = locationManager.getLastKnownLocation(locationProvider)

        locationManager.allProviders.forEach {
            val checkLocation: Location? = locationManager.getLastKnownLocation(it)

            Log.i("CHECK_LOCATION", "CheckLocation '$checkLocation' for provider $it")
            checkLocation?.let {
                if (bestLocation == null || bestLocation!!.accuracy < it.accuracy) {
                    bestLocation = it
                }

                Log.i("CHECK_LOCATION", "Accuracy: ${it.accuracy}")
                Log.i("CHECK_LOCATION", "Time: ${it.time}")
                Log.i("CHECK_LOCATION", "Provider: ${it.provider}")
            }
        }

        bestLocation?.let {
            val latLng = LatLng(it.latitude, it.longitude)

            map.addMarker(MarkerOptions().position(latLng).title("My position"))

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17F))
        }

        locationManager.requestLocationUpdates(
            locationProvider,
            2000L,
            2F,
            this
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> if (grantResults[0] == PERMISSION_GRANTED) {
                startLocation()
            }
        }
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            val latLng = LatLng(location.latitude, location.longitude)

            polygonOptions.add(latLng)

            map.addMarker(MarkerOptions().position(latLng).title("New position"))

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17F))
        }
    }

    override fun onBackPressed() {
        map.addPolygon(polygonOptions)

        return
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Toast.makeText(this, "onStatusChanged $provider - $status", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderEnabled(provider: String?) {
        Toast.makeText(this, "onProviderEnabled $provider", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderDisabled(provider: String?) {
        Toast.makeText(this, "onProviderDisabled $provider", Toast.LENGTH_SHORT).show()
    }
}
