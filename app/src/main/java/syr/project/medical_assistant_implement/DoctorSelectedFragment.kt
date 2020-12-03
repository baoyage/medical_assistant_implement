package syr.project.medical_assistant_implement

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_doctor_selected.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity


class DoctorSelectedFragment : Fragment() {
    lateinit var myAdapter:DoctorListAdapter
    private var listener: DoctorSelectedFragment.OnRecyclerInteractionListener? = null
    interface OnRecyclerInteractionListener {
        fun onDoctorClicked()
    }

    //override fun onItemClickedFromAdapter(){
    //    listener?.onDoctorClicked()
    //}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        //dateButton.setOnClickListener(ButtonListener())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_selected, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        myAdapter= DoctorListAdapter(view.context)
//        DoctorRcyclerView.layoutManager= GridLayoutManager(context,1)
//        DoctorRcyclerView.adapter=myAdapter
//        myAdapter.setMyItemClickListener(this)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit.setOnClickListener{
            activity!!.finish()
        }
        dateButton.setOnClickListener{

        }

    }
    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DoctorSelectedFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    fun showDatePickerDialog(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val calendar: Calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(context)
            dialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val format = SimpleDateFormat("yyyy-MM-dd")
                textView.setText(format.format(calendar.getTime()))
                //              textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
            val datePicker = dialog.datePicker
            datePicker.minDate = calendar.getTimeInMillis()
            dialog.show()
        }
    }
}