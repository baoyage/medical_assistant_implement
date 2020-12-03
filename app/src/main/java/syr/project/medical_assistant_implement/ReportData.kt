package syr.project.medical_assistant_implement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties("selection")
class ReportData: Serializable{

    var id: Int=-1
    var userid: Int=-1
    var username: String=""
    var reportpath: String=""
    var reportdate: String=""
    var doctorname: String=""
    var specialty: String=""

}