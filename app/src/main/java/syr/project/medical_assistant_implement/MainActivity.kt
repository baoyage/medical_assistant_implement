package syr.project.medical_assistant_implement

import MainPagerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tabLayout.setOnClickListener{
//            settings.setOnClickListener {
//                val intent = Intent(this, SettingsActivity::class.java).apply {
//                }
//                startActivity(intent)
//            }
//
//        }





        viewPager.adapter = MainPagerAdapter(supportFragmentManager,tabLayout)
//        viewPager.adapter= MainPagerAdapter()
        tabLayout.setupWithViewPager(viewPager)
    }
}