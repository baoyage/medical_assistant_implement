package syr.project.medical_assistant_implement.prescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import syr.project.medical_assistant_implement.R

class PrescriptionActivity : AppCompatActivity(),
    PrescriptionFragment.OnRecyclerInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Prescription"
        appBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.prescriptionContainer, PrescriptionFragment()).commit()
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

    override fun onItemClicked(position: Int) {
        val appBar = supportActionBar
        supportFragmentManager.beginTransaction().replace(
            R.id.prescriptionContainer, PrescriptionDetailFragment.newInstance(
                position!!
            )
        ).addToBackStack(null).commit()
    }

}