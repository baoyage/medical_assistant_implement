package syr.project.medical_assistant_implement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_report_detail.*
import java.io.Serializable

private const val ARG_MOV1 = "report"
private const val ARG_MOV2 = "reportid"

class ReportDetailFragment : Fragment() {

    private var report: ReportData? = null
    private var reportid:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        //setHasOptionsMenu(true)
        arguments?.let {
            report = it.getSerializable(ARG_MOV1) as ReportData
            reportid = it.getInt(ARG_MOV2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ReportUsername.text=report?.username
        ReportDate.text=report?.reportdate
        ReportDoctor.text=report?.doctorname
        ReportSpec.text=report?.specialty
        val url = report?.reportpath
        val picasso = Picasso.Builder(reportImage.context).listener { _, _, e -> e.printStackTrace() }.build()
        picasso.load(url).into(reportImage)
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(reportImage)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(report: ReportData, reportid: Int) =
            ReportDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOV1, report as Serializable)
                    putInt(ARG_MOV2, reportid)
                }
            }
    }
}