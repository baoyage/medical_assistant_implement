package syr.project.medical_assistant_implement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_item.view.*
import syr.project.medical_assistant_implement.InsuranceListAdapter.*
import syr.project.medical_assistant_implement.R.layout.list_item



class InsuranceListAdapter(context: Context
): RecyclerView.Adapter<InsuranceListHolder>() {

    var insuranceList:Array<String> = context.resources.getStringArray(R.array.insurance_list)
    var myListener: MyItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        view= layoutInflater.inflate( list_item , parent,false)

        return InsuranceListHolder(view)
    }


    inner class InsuranceListHolder(view: View) : RecyclerView.ViewHolder(view!!) {
        val rVInsurance=view.rVList
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




    override fun onBindViewHolder(holder: InsuranceListHolder, position: Int) {
        val insurance=insuranceList[position]
        holder.rVInsurance.text=insurance

    }

    override fun getItemCount(): Int {
        return insuranceList.size
    }
    interface MyItemClickListener {
        fun onItemClickedFromAdapter()
    }
    fun setMyItemClickListener(listener: MyItemClickListener) {
        this.myListener=listener
    }
}