package syr.project.medical_assistant_implement.appointment

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DoctorData(

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("name") val name: String,
    @SerializedName("degree") val degree: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("location") val location: String,

) : Serializable
