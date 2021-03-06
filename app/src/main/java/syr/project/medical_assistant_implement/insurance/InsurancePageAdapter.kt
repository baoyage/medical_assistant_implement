package syr.project.medical_assistant_implement.insurance

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import syr.project.medical_assistant_implement.R

class InsurancePageAdapter(insurance_list :Array<String>, insurance_overview :Array<String>, fragmentManager: FragmentManager, tabLayout: TabLayout?
): androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {

    var insuranceList:Array<String> = insurance_list
    var insuranceOverview:Array<String> = insurance_overview
    var insuranceIndex = 0
    var posterTable:MutableMap<String, Int> = mutableMapOf()
    init{
        posterTable[insuranceList[0]]= R.drawable.aarp
        posterTable[insuranceList[1]]= R.drawable.aetna
        posterTable[insuranceList[2]]= R.drawable.amfam
        posterTable[insuranceList[3]]= R.drawable.anthem
        posterTable[insuranceList[4]]= R.drawable.cigna
        posterTable[insuranceList[5]]= R.drawable.emblemhealth
        posterTable[insuranceList[6]]= R.drawable.healthmarkets
        posterTable[insuranceList[7]]= R.drawable.independence
        posterTable[insuranceList[8]]= R.drawable.shelter
        posterTable[insuranceList[9]]= R.drawable.statefarm
        posterTable[insuranceList[10]]= R.drawable.unitedhealthcare
        posterTable[insuranceList[11]]= R.drawable.wellcare
    }

    override fun getItem(p0: Int): Fragment {
        var position=p0
        if(p0<0) {
            position=0
        }
        if(p0>insuranceList.size){
            position=insuranceList.size-1
        }
        var insurancename =insuranceList[position]
        var posterid:Int= posterTable[insuranceList[position]]!!
        var insuranceoverview =insuranceOverview[position]
        return InsuranceViewPagerFragment.newInstance(insurancename, posterid, insuranceoverview)
    }

    override fun getCount(): Int {
        return insuranceList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return insuranceList[position]
    }
}