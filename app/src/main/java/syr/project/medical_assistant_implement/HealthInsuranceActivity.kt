package syr.project.medical_assistant_implement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_health_insurance.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.myToolbar

class HealthInsuranceActivity : AppCompatActivity() ,HealthInsuranceFragment.OnRecyclerInteractionListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_insurance)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Insurance"
//        appBar.setDisplayShowHomeEnabled(true)
        appBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.insuranceContainer, HealthInsuranceFragment()).commit()

        val firebaseUser = FirebaseAuth.getInstance().currentUser



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

    override fun onInsuranceClicked() {
        finish()
//        val appBar = supportActionBar
//        supportFragmentManager.beginTransaction().replace(R.id.appointmentContainer, DoctorsRecommendedFragment.newInstance()
//        ).addToBackStack(null).commit()
    }
}