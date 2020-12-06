package syr.project.medical_assistant_implement.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties("selection")
class ReportData: Serializable{

    var id: Int=-1
    var username: String=""
    var reportPath: String=""
    var reportDate: String=""
    var doctorid: String=""
    var specialty: String=""
    var location: String=""

}