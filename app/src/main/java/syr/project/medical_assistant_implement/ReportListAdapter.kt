package syr.project.medical_assistant_implement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.report_itemlist.view.*

class ReportListAdapter(var modelClass: Class<ReportData>, var query: Query):
    FirebaseRecyclerAdapter<ReportData, ReportListAdapter.ReportViewHolder>(
        FirebaseRecyclerOptions.Builder<ReportData>()
            .setQuery(query,modelClass)
            .build()
    ){
    var myListener: MyItemClickListener? = null

    interface MyItemClickListener {
        fun onItemClickedFromAdapter(position: Int)

    }
    fun setMyItemClickListener(listener: ReportListAdapter.MyItemClickListener) {
        this.myListener = listener
    }


    inner class ReportViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val rVRUsername= view?.rVRUsername
        val rVRDate= view?.rVRDate
        val rVRSpecialty= view?.rVRSpecialty
        init{
            view!!.setOnClickListener{
                if(myListener!=null){
                    if(adapterPosition!= RecyclerView.NO_POSITION){

                        myListener!!.onItemClickedFromAdapter(adapterPosition)
                    }
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportListAdapter.ReportViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view=layoutInflater.inflate(R.layout.report_itemlist,parent,false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportListAdapter.ReportViewHolder, position: Int, report: ReportData) {
        if (holder==null) {
            return
        }
            holder.rVRUsername!!.text = report.username
            holder.rVRDate!!.text = report.reportDate
            holder.rVRSpecialty!!.text = report.specialty

    }


}
