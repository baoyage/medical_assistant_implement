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
        //fun onItemClickedFromAdapter(position: Int)
        //fun onItemLongClickedFromAdapter(position: Int)
    }

    fun setMyItemClickListener(listener: MyItemClickListener) {
        this.myListener = listener
    }

    inner class ReportViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val rVPresUsername= view?.rVRUsername
        val rVPresDate= view?.rVRDate
        val rVPresSpecialty= view?.rVRSpecialty
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportListAdapter.ReportViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view=layoutInflater.inflate(R.layout.fragment_report,parent,false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportListAdapter.ReportViewHolder, position: Int, report: ReportData) {

        holder.rVPresUsername!!.text =report.username
        holder.rVPresDate!!.text=report.reportdate
        holder.rVPresSpecialty!!.text=report.specialty
        /*val url = report.reportpath
        val picasso = Picasso.Builder(holder.itemView.context).listener { _, _, e -> e.printStackTrace() }.build()
        picasso.load(url).into(holder.rVPresImage)
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(holder.rVPresImage)*/
    }
    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val mRef = mDatabase.child("report")

}
