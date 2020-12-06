package syr.project.medical_assistant_implement

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Transformations.map
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.RuntimeExecutionException
import com.google.android.gms.tasks.Task
import com.google.common.io.Files.map
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(),OnMapReadyCallback, ClusterManager.OnClusterClickListener<MyClusterItem>, ClusterManager.OnClusterItemClickListener<MyClusterItem> {
    lateinit var currentLocation: Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val REQUEST_LOCATION = 1
    private lateinit var mGoogleMap: GoogleMap
    private val malaysiaCoordinate = LatLng(4.2105, 101.9758)
    private var mSpotMarkerList = ArrayList<Marker>()

    // Declare a variable for the cluster manager.
    private var mClusterManager: ClusterManager<MyClusterItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Map"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            } else {
                Toast.makeText(
                    this,
                    "Location permission is required to locate you!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            val task: Task<Location> = fusedLocationProviderClient.lastLocation

            task.addOnSuccessListener {

                if (it != null) {
                    currentLocation = it
                    animateZoomInCamera(
                        LatLng(
                            currentLocation.latitude,
                            currentLocation.longitude
                        )
                    )
                } else {

                    val REQUEST_CHECK_STATE = 12300 // any suitable ID
                    val builder = LocationSettingsRequest.Builder()
                        .addLocationRequest(reqSetting)

                    val client = LocationServices.getSettingsClient(this)
                    client.checkLocationSettings(builder.build()).addOnCompleteListener { task ->
                        try {
                            val state: LocationSettingsStates = task.result!!.locationSettingsStates
                            Log.d("salam", task.result!!.toString())
                            Log.e(
                                "LOG", "LocationSettings: \n" +
                                        " GPS present: ${state.isGpsPresent} \n" +
                                        " GPS usable: ${state.isGpsUsable} \n" +
                                        " Location present: " +
                                        "${state.isLocationPresent} \n" +
                                        " Location usable: " +
                                        "${state.isLocationUsable} \n" +
                                        " Network Location present: " +
                                        "${state.isNetworkLocationPresent} \n" +
                                        " Network Location usable: " +
                                        "${state.isNetworkLocationUsable} \n"
                            )
                        } catch (e: RuntimeExecutionException) {
                            Log.d("salam", "hei")
                            if (e.cause is ResolvableApiException)
                                (e.cause as ResolvableApiException).startResolutionForResult(
                                    this,
                                    REQUEST_CHECK_STATE
                                )
                        }
                    }

                    val locationUpdates = object : LocationCallback() {
                        override fun onLocationResult(lr: LocationResult) {
                            Log.e("salam", lr.toString())
                            Log.e("salam", "Newest Location: " + lr.locations.last())
                            // do something with the new location...
                            animateZoomInCamera(
                                LatLng(
                                    lr.locations.last().latitude,
                                    lr.locations.last().longitude
                                )
                            )
                        }
                    }

                    fusedLocationProviderClient.requestLocationUpdates(
                        reqSetting,
                        locationUpdates,
                        null /* Looper */
                    )

                    fusedLocationProviderClient.removeLocationUpdates(locationUpdates)
                }
            }

        }
    }


    fun animateZoomInCamera(latLng: LatLng) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
    }

    private val reqSetting = LocationRequest.create().apply {
        fastestInterval = 10000
        interval = 10000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        smallestDisplacement = 1.0f
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap!!
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(malaysiaCoordinate))
        animateZoomInCamera(malaysiaCoordinate)
        mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mGoogleMap.uiSettings.isZoomControlsEnabled = true

        setUpClusterer()

        btnLocate.setOnClickListener {
            fetchLocation()
        }
    }

    private fun setUpClusterer() {
        // Position the map.
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(43.04225775103897, -76.14000102553369), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = ClusterManager(this, mGoogleMap)
        mClusterManager!!.setOnClusterClickListener(this)
        mClusterManager!!.setOnClusterItemClickListener(this)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mGoogleMap.setOnCameraIdleListener(mClusterManager)
        mGoogleMap.setOnMarkerClickListener(mClusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

    private fun addItems() {

        // Set some lat/lng coordinates to start with.
        var lat = 43.04225775103897
        var lng = -76.14000102553369


        // Set the title and snippet strings.
        val title = "This is the title"
        val snippet = "and this is the snippet."

        val offsetItem = MyClusterItem(lat, lng, title, snippet)
        mClusterManager!!.addItem(offsetItem)

    }

    override fun onClusterClick(cluster: Cluster<MyClusterItem>?): Boolean {
        animateZoomInCamera(cluster!!.position)
        return true
    }

    override fun onClusterItemClick(item: MyClusterItem?): Boolean {
        animateZoomInCamera(item!!.position)
        return true
    }
}