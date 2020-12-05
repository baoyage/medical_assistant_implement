package syr.project.medical_assistant_implement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties("selection")
class PrescriptionData: Serializable{

    var id: Int=-1
    var username: String=""
    var prescriptionpath: String=""
    var prescriptiondate: String=""
    var doctorname: String=""
    var specialty: String=""

}