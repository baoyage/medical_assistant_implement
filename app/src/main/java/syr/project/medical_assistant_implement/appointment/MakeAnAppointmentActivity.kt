package syr.project.medical_assistant_implement.appointment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import syr.project.medical_assistant_implement.R

class MakeAnAppointmentActivity : AppCompatActivity(),
    SpecialtyFragment.OnRecyclerInteractionListener,
    DoctorsRecommendedFragment.OnRecyclerInteractionListener, Communicator {

    var datetoset=""
    var timetoset=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_an_appointment)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Make an appointment"
        appBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.makeAnAppointmentContainer, SpecialtyFragment()).commit()
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
        supportFragmentManager.beginTransaction().replace(
            R.id.makeAnAppointmentContainer, DoctorsRecommendedFragment.newInstance()
        ).addToBackStack(null).commit()
    }

    override fun onDoctorClicked(){
        val appBar = supportActionBar
        supportFragmentManager.beginTransaction().replace(
            R.id.makeAnAppointmentContainer, DoctorSelectedFragment.newInstance()
        ).addToBackStack(null).commit()
    }

    override fun passDataCom(editext_1_input: String,editext_2_input: String){
        val bundle = Bundle()
        if(editext_1_input!=""){
            datetoset=editext_1_input
        }
        if(editext_2_input!=""){
            timetoset=editext_2_input
        }
        bundle.putString("input_1_txt",datetoset)
        bundle.putString("input_2_txt",timetoset)
        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = DoctorSelectedFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.makeAnAppointmentContainer, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}