package syr.project.medical_assistant_implement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_health_insurance.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.myToolbar

class HealthInsuranceActivity : AppCompatActivity() {
    lateinit var myAdapter:InsuranceListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_insurance)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Insurance"
//        appBar.setDisplayShowHomeEnabled(true)
        appBar?.setDisplayHomeAsUpEnabled(true)

        myAdapter= InsuranceListAdapter(this)
        insuranceRecyclerView.layoutManager= LinearLayoutManager(this)
        insuranceRecyclerView.adapter=myAdapter
        insuranceRecyclerView.addItemDecoration(DividerItemDecoration(insuranceRecyclerView.context, DividerItemDecoration.VERTICAL))


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
}