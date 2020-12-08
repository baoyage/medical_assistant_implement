package syr.project.medical_assistant_implement.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import syr.project.medical_assistant_implement.R

class ReportActivity : AppCompatActivity(), ReportFragment.OnRecyclerInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Report"
        appBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.reportContainer, ReportFragment()).commit()
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
            R.id.reportContainer, ReportDetailFragment.newInstance(
                position
            )
        ).addToBackStack(null).commit()
    }
}