package syr.project.medical_assistant_implement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import android.view.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_prescription_detail.*
import kotlinx.android.synthetic.main.fragment_prescription_detail.view.*
import java.io.Serializable

private const val ARG_MOV1 = "prescription"
private const val ARG_MOV2 = "prescriptionid"

class PrescriptionDetailFragment : Fragment() {

    private var prescription: PrescriptionData? = null
    private var prescriptionid:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        //setHasOptionsMenu(true)
        arguments?.let {
            prescription = it.getSerializable(ARG_MOV1) as PrescriptionData
            prescriptionid = it.getInt(ARG_MOV2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prescription_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PresUsername.text=prescription?.username
        PrescriptionDate.text=prescription?.prescriptiondate
        PresDoctor.text=prescription?.doctorname
        PresSpec.text=prescription?.specialty
        val url = prescription?.prescriptionpath
        val picasso = Picasso.Builder(prescriptionImage.context).listener { _, _, e -> e.printStackTrace() }.build()
        picasso.load(url).into(prescriptionImage)
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(prescriptionImage)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(prescription: PrescriptionData, prescriptionid: Int) =
            PrescriptionDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOV1, prescription as Serializable)
                    putInt(ARG_MOV2, prescriptionid)
                }
            }
    }
}