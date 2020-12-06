package syr.project.medical_assistant_implement.appointment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_doctor.view.*
import syr.project.medical_assistant_implement.R
import syr.project.medical_assistant_implement.appointment.DoctorListAdapter.*

class DoctorListAdapter (context: Context
): RecyclerView.Adapter<DoctorListHolder>(){
    var DoctorList:Array<String> = context.resources.getStringArray(R.array.doctor_list)
    var myListener: MyItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        view= layoutInflater.inflate(R.layout.list_doctor, parent,false)

        return DoctorListHolder(view)
    }

    inner class DoctorListHolder(view: View) : RecyclerView.ViewHolder(view!!) {


        val rVDoctorName=view.rVDoctorName
        init{
            view.setOnClickListener{
                if(myListener!=null){
                    if(adapterPosition!= RecyclerView.NO_POSITION){

                        myListener!!.onItemClickedFromAdapter()
                    }
                }
            }
        }
    }
    override fun onBindViewHolder(holder: DoctorListHolder, position: Int) {
        val doctorName="Amani"
        holder.rVDoctorName.text=doctorName
    }

    override fun getItemCount(): Int {
        return DoctorList.size
    }
    interface MyItemClickListener {
        fun onItemClickedFromAdapter()
    }
    fun setMyItemClickListener(listener: DoctorListAdapter.MyItemClickListener) {
        this.myListener=listener
    }
}
