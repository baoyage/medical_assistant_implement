package syr.project.medical_assistant_implement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import syr.project.medical_assistant_implement.SpecialtyListAdapter.*

class SpecialtyListAdapter(context: Context
): RecyclerView.Adapter<SpecialtyListHolder>() {
    var specialtyList:Array<String> = context.resources.getStringArray(R.array.specialties_list)
    var myListener:MyItemClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        view= layoutInflater.inflate(R.layout.list_item, parent,false)

        return SpecialtyListHolder(view)
    }
    inner class SpecialtyListHolder(view: View) : RecyclerView.ViewHolder(view!!) {
        val rVSpecialty=view.rVList
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

    override fun onBindViewHolder(holder: SpecialtyListHolder, position: Int) {
        val specialty=specialtyList[position]
        holder.rVSpecialty.text=specialty
    }

    override fun getItemCount(): Int {
        return specialtyList.size
    }
    interface MyItemClickListener {
        fun onItemClickedFromAdapter()
    }
    fun setMyItemClickListener(listener: MyItemClickListener) {
        this.myListener=listener
    }
}