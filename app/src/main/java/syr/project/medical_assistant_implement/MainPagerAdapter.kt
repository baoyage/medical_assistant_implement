import syr.project.medical_assistant_implement.HomeFragment
import syr.project.medical_assistant_implement.SettingsFragment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import syr.project.medical_assistant_implement.InquiryFragment
import syr.project.medical_assistant_implement.MainActivity

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    tabLayout: TabLayout?,

) :
    androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {

                return HomeFragment()
            }
            1 -> {
                return InquiryFragment()
            }
            else -> {
                return SettingsFragment()
            }

        }


    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->{
                return "Home"
            }
            1 -> {
                return "Inquiry"
            }
            2 -> {
                return "Settings"
            }


        }
        return null

    }


}

//class MainPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }


//}
