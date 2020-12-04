package syr.project.medical_assistant_implement

import android.R
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var calendar: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)



        /*
            **** reference source developer.android.com ***

            DatePickerDialog(Context context)
                Creates a new date picker dialog for the current date using the
                parent context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId)
                Creates a new date picker dialog for the current date.

            DatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener,
            int year, int month, int dayOfMonth)
                Creates a new date picker dialog for the specified date using the parent
                context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId, DatePickerDialog.OnDateSetListener
            listener, int year, int monthOfYear, int dayOfMonth)
                Creates a new date picker dialog for the specified date.
        */

        // Initialize a new date picker dialog and return it
        return TimePickerDialog(
            activity!!, // Context
            // Put 0 to system default theme or remove this parameter
            R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this,
            hour,
            minute,
            false,
            // DatePickerDialog.OnDateSetListener


        )
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

    }

    // When date set and press ok button in date picker dialog
//    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
//        Toast.makeText(
//            activity,
//            "Date Set : ${formatDate(year,month,day)}"
//            , Toast.LENGTH_SHORT
//        ).show()
//
//        // Display the selected date in text view
////        activity!!.findViewById<TextView>(R.id.text_view).text = formatDate(year,month,day)
//    }


    // Custom method to format date
//    private fun formatDate(hour:Int, minute:Int):String{
//        // Create a Date variable/object with user chosen date
//        calendar.set(hour, minute, 0, 0)
//        val chosenDate = calendar.time
//
//        // Format the date picker selected date
//        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
//        return df.format(chosenDate)
//    }


}