package syr.project.medical_assistant_implement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.prescription_itemlist.view.*

class PrescriptionListAdapter(var modelClass: Class<PrescriptionData>, var query: Query):
    FirebaseRecyclerAdapter<PrescriptionData, PrescriptionListAdapter.PrescriptionViewHolder>(
        FirebaseRecyclerOptions.Builder<PrescriptionData>()
            .setQuery(query,modelClass)
            .build()
    ){

    var myListener: MyItemClickListener? = null

    interface MyItemClickListener {
        fun onItemClickedFromAdapter(position: Int)

    }

    fun setMyItemClickListener(listener: MyItemClickListener) {
        this.myListener = listener
    }

    inner class PrescriptionViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val rVPresLocation= view?.rVPLocation
        val rVPresDate= view?.rVPDate
        val rVPresSpecialty= view?.rVPSpecialty
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionListAdapter.PrescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        view=layoutInflater.inflate(R.layout.prescription_itemlist,parent,false)
        return PrescriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrescriptionListAdapter.PrescriptionViewHolder, position: Int, prescription: PrescriptionData) {
        if (holder!=null) {
            holder.rVPresLocation!!.text =prescription.location
            holder.rVPresDate!!.text=prescription.prescriptionDate
            holder.rVPresSpecialty!!.text=prescription.specialty
        }

    }
}
