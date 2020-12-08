package syr.project.medical_assistant_implement.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_health_insurance.*
import kotlinx.android.synthetic.main.activity_main.myToolbar
import syr.project.medical_assistant_implement.R

class HealthInsuranceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_insurance)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Insurance"
        appBar?.setDisplayHomeAsUpEnabled(true)
        var insuranceList:Array<String> = resources.getStringArray(R.array.insurance_list)
        var insuranceOverview:Array<String> = resources.getStringArray(R.array.insurance_description_list)
        insuranceViewPager.adapter = InsurancePageAdapter(insuranceList, insuranceOverview, supportFragmentManager,tabLayout)
        tabLayout.setupWithViewPager(insuranceViewPager)
        insuranceViewPager.currentItem = 0

        insuranceViewPager.setPageTransformer(false) { a, b ->
            val norm = Math.abs(Math.abs(b) - 1)
            a.scaleX = norm/2 + 0.5f
            a.scaleY = norm/2 + 0.5f
        }
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

    var index = -1;
    override fun onResume() {
        super.onResume()
        insuranceViewPager.currentItem = index
    }

    override fun onPause() {
        super.onPause()
        index = insuranceViewPager.currentItem
    }
}