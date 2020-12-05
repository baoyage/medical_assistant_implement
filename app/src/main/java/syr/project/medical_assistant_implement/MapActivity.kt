package syr.project.medical_assistant_implement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.common.io.Files.map

class MapActivity : AppCompatActivity(),GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    private val PERTH = LatLng(43.04225775103897, -76.14000102553369)
//    private val SYDNEY = LatLng(-33.87365, 151.20689)
//    private val BRISBANE = LatLng(-27.47093, 153.0235)

    private lateinit var markerPerth: Marker
    private lateinit var markerSydney: Marker
    private lateinit var markerBrisbane: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /** Called when the map is ready.  */
    override fun onMapReady(map: GoogleMap) {
        // Add some markers to the map, and add a data object to each marker.
        markerPerth = map.addMarker(
            MarkerOptions()
                .position(PERTH)
                .title("Perth")
        )
        markerPerth.tag = 0
//        markerSydney = map.addMarker(
//                MarkerOptions()
//                        .position(SYDNEY)
//                        .title("Sydney")
//        )
//        markerSydney.tag = 0
//        markerBrisbane = map.addMarker(
//                MarkerOptions()
//                        .position(BRISBANE)
//                        .title("Brisbane")
//        )
//
//        markerBrisbane.tag = 0

        // Set a listener for marker click.
        map.setOnMarkerClickListener(this)
    }

    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
        val clickCount = marker.tag as? Int

        // Check if a click count was set, then display the click count.
        clickCount?.let {
            val newClickCount = it + 1
            marker.tag = newClickCount
            Toast.makeText(
                this,
                "${marker.title} has been clicked $newClickCount times.",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }
}