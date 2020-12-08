package syr.project.medical_assistant_implement.appointment

import android.R
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var calendar: Calendar
    lateinit var comm: Communicator
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()
        // Get the system current date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity!!, // Context
            // Put 0 to system default theme or remove this parameter
            R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this,
            hour,
            minute,
            false,
        )
    }
    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int,) {
        var timeFormat=String.format("%d : %d", hour, minute)
        comm = activity as Communicator
        comm.passDataCom("",timeFormat)
    }
}