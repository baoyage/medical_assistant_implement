package syr.project.medical_assistant_implement.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navi_header.view.*
import syr.project.medical_assistant_implement.*
import syr.project.medical_assistant_implement.appointment.MakeAnAppointmentActivity
import syr.project.medical_assistant_implement.insurance.HealthInsuranceActivity
import syr.project.medical_assistant_implement.login.LoginActivity
import syr.project.medical_assistant_implement.prescription.PrescriptionActivity
import syr.project.medical_assistant_implement.report.ReportActivity
import syr.project.medical_assistant_implement.user.ChangePasswordFragment
import syr.project.medical_assistant_implement.user.ChangePhoneNumberFragment
import syr.project.medical_assistant_implement.user.ChangeUsernameFragment
import syr.project.medical_assistant_implement.wiki.WikiActivity
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isLoggedIn()
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

        val headerView = navView.getHeaderView(0)
        val headerTimeGreetings=headerView.headerTimeGreetings

        val currentTime= DateTimeFormatter
            .ofPattern("yyyyMMdd HH:mm")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault())
            .format(Instant.now())
        val currentHour=currentTime.substring(9,11).toInt()
        if(currentHour<12){
            headerTimeGreetings.text="Good morning, "
        }
        else if(currentHour<18){
            headerTimeGreetings.text="Good afternoon, "
        }
        else{
            headerTimeGreetings.text="Good evening, "
        }


        floatingActionButton.setOnClickListener {
            var dialIntent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + 911));
            startActivity(dialIntent);
        }

//        BottomNavi.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.action_appointment -> {
//                    val intent = Intent(this, MakeAnAppointmentActivity::class.java)
//                    intent.putExtra("action", 0)
//                    startActivity(intent)
//                }
//                R.id.action_insurance->{
//                    val intent = Intent(this, HealthInsuranceActivity::class.java)
//                    intent.putExtra("action", 0)
//                    startActivity(intent)
//                }
//                R.id.action_prescription -> {
//                    val intent = Intent(this, PrescriptionActivity::class.java)
//                    intent.putExtra("action", 0)
//                    startActivity(intent)
//                }
//                R.id.action_wiki ->{
//                    val intent = Intent(this, WikiActivity::class.java)
//                    intent.putExtra("action", 0)
//                    startActivity(intent)
//                }
//            }
//        }
    }


    override fun onResume() {
        super.onResume()
        updateData()
    }


    override fun onStart() {
        super.onStart()
        updateData()

    }

    fun updateData(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid != null){
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            if (firebaseUser != null) {
                if(firebaseUser.email == null)
                    Log.i(firebaseUser!!.email, "onStart: dsafasddsaf")
            }
            Log.i(firebaseUser!!.displayName, "onStart: qwetrtyuyuii")
            val headerView = navView.getHeaderView(0)
            val profileEmail = headerView.profileEmail
            val profileUserName = headerView.profileUserName
            val profileImage= headerView.profileImage



            val profileRef = FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser!!.uid)

            profileRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot != null){
                        profileEmail.text = dataSnapshot.child("useremail").value.toString()
                        Log.i(dataSnapshot.child("useremail").value.toString(), "onStart: qwertytyu")
//                        if(profileEmail.text=="null"){
//                            profileEmail.text="Email"
//                        }
                        profileUserName.text=dataSnapshot.child("username").value.toString()
//                        if(profileUserName.text=="null"){
//                            profileUserName.text=firebaseUser!!.displayName
//                        }
                        Picasso.get().load(dataSnapshot.child("profileImageUrl").value.toString()).fit().into(profileImage)
                        if(dataSnapshot.child("profileImageUrl").value.toString()=="null"){
                            profileImage.setImageResource(R.drawable._8)
//                            Picasso.get().load(FirebaseAuth.getInstance().currentUser?.photoUrl.toString()).fit().into(profileImage)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {


                }

            }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inflate the menu into toolbar

        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item?.itemId) {
            R.id.action_appointment ->{
                val intent = Intent(this, MakeAnAppointmentActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.action_prescription ->{
                val intent = Intent(this, PrescriptionActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.action_report ->{
                val intent = Intent(this, ReportActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.action_insurance -> {
                val intent = Intent(this, HealthInsuranceActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.action_wiki ->{
                val intent = Intent(this, WikiActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }



        }




        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.signout -> {
                signOut()
            }
            R.id.changePassword ->{
                if (mainAct.isDrawerOpen(GravityCompat.START)) {
                    mainAct.closeDrawer(GravityCompat.START)
                }
                supportFragmentManager.beginTransaction().replace(R.id.meContainer, ChangePasswordFragment())
                    .addToBackStack(null).commit()
            }
            R.id.changeImage ->{
                Log.d("SignUp", "Try to show photo selector")
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 0)
            }
            R.id.changeUsername ->{
                if (mainAct.isDrawerOpen(GravityCompat.START)) {
                    mainAct.closeDrawer(GravityCompat.START)
                }
                supportFragmentManager.beginTransaction().replace(R.id.meContainer, ChangeUsernameFragment())
                    .addToBackStack(null).commit()

            }
            R.id.changePhoneNumber ->{
                if (mainAct.isDrawerOpen(GravityCompat.START)) {
                    mainAct.closeDrawer(GravityCompat.START)
                }
                supportFragmentManager.beginTransaction().replace(R.id.meContainer, ChangePhoneNumberFragment())
                    .addToBackStack(null).commit()
            }
            R.id.emergency ->{
                var dialIntent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + 911));
                startActivity(dialIntent);
            }

        }
        updateData()
        return true
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
    var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("SignUp", "Photo was selected")
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, selectedPhotoUri)
            uploadImageToFirebaseStorage()
//            selectphoto_imageview.setImageBitmap(bitmap)
//            R.id.changeImage.alpha = 0f // hide button for selected photo imageview
        }
    }
    private fun uploadImageToFirebaseStorage() {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("SignUp", "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d("SignUp", "File Location: $it")
                    saveImageToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d("SignUp", "Failed to upload image to storage: ${it.message}")
            }


    }
    private fun saveImageToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val currentUserRef = ref!!.child(uid)
        ref.child("profileImageUrl").setValue(profileImageUrl)
            .addOnSuccessListener {
                updateData()
            }
    }


    private fun isLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid // check current uid of authentication!
        if (uid == null) {
            // launch the Login activity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    private fun signOut(){
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }



    override fun onBackPressed() {
        if (mainAct.isDrawerOpen(GravityCompat.START)) {
            mainAct.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }




}




