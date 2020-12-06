package syr.project.medical_assistant_implement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties("selection")
class PrescriptionData: Serializable{

    var id: Int=-1
    var username: String=""
    var prescriptionPath: String=""
    var prescriptionDate: String=""
    var doctorid: String=""
    var specialty: String=""
    var location: String=""

}