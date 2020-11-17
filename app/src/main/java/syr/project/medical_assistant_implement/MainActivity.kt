package syr.project.medical_assistant_implement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Medical Assistant"

        appBar.setDisplayShowHomeEnabled(true)
//        appBar?.setDisplayHomeAsUpEnabled(true)
//        appBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1B82D2")))
        val toggle = ActionBarDrawerToggle(this, mainAct, myToolbar, 0, 0)
        mainAct.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.meContainer, HomeFragment()).commit()





    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inflate the menu into toolbar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.makeAnAppointment -> {
//                val intent = Intent(this, MakeAnAppointmentActivity::class.java)
//                intent.putExtra("action", 0)
//                startActivity(intent)
//            }
//            R.id.task3 -> {
//                val intent = Intent(this, Task3Activity::class.java)
//                intent.putExtra("action", 0)
//                startActivity(intent)
//            }
//
//        }
//        mainAct.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (mainAct.isDrawerOpen(GravityCompat.START)) {
            mainAct.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }




}




