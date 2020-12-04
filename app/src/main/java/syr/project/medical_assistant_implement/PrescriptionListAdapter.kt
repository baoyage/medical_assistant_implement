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
import kotlinx.android.synthetic.main.prescription_itemlist.view.*

class PrescriptionListAdapter(var modelClass: Class<PrescriptionData>, var query: Query):
    FirebaseRecyclerAdapter<PrescriptionData, PrescriptionListAdapter.PrescriptionViewHolder>(
        FirebaseRecyclerOptions.Builder<PrescriptionData>()
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

    inner class PrescriptionViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val rVPresUsername= view?.rVRUsername
        val rVPresDate= view?.rVRDate
        val rVPresSpecialty= view?.rVRSpecialty
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionListAdapter.PrescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        view=layoutInflater.inflate(R.layout.fragment_prescription,parent,false)
        return PrescriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrescriptionListAdapter.PrescriptionViewHolder, position: Int, prescription: PrescriptionData) {

        holder.rVPresUsername!!.text =prescription.username
        holder.rVPresDate!!.text=prescription.prescriptiondate
        holder.rVPresSpecialty!!.text=prescription.specialty
        /*val url = prescription.prescriptionpath
        val picasso = Picasso.Builder(holder.itemView.context).listener { _, _, e -> e.printStackTrace() }.build()
        picasso.load(url).into(holder.rVPresImage)
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(holder.rVPresImage)*/
    }
    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val mRef = mDatabase.child("prescription")

}
