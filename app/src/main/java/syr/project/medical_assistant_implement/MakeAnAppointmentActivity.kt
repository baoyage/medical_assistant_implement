package syr.project.medical_assistant_implement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MakeAnAppointmentActivity : AppCompatActivity(), SpecialtyFragment.OnRecyclerInteractionListener,
DoctorsRecommendedFragment.OnRecyclerInteractionListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_an_appointment)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Make an appointment"
        appBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.appointmentContainer, SpecialtyFragment()).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inflate the menu into toolbar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onSpecialtyClicked(){
        val appBar = supportActionBar
        supportFragmentManager.beginTransaction().replace(R.id.appointmentContainer, DoctorsRecommendedFragment.newInstance()
        ).addToBackStack(null).commit()
    }
    override fun onDoctorClicked(){
        val appBar = supportActionBar
        supportFragmentManager.beginTransaction().replace(R.id.appointmentContainer, DoctorSelectedFragment.newInstance()
        ).addToBackStack(null).commit()
    }

}