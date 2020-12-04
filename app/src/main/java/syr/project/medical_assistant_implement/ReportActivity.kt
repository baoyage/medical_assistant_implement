package syr.project.medical_assistant_implement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun onItemClicked(report: ReportData, reportid: Int?) {
        val appBar = supportActionBar
        supportFragmentManager.beginTransaction().replace(R.id.reportContainer, ReportDetailFragment.newInstance(report, reportid!!)
        ).addToBackStack(null).commit()
    }
}