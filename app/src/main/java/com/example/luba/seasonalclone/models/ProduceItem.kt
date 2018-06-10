package com.example.luba.seasonalclone.models

import com.google.gson.annotations.SerializedName

data class ProduceItem (
        @SerializedName ("ID") val id : String,
        @SerializedName ("Name") val name: String,
        @SerializedName ("Type") val type: String,
        @SerializedName ("Description") val description: String,
        @SerializedName ("imgURL") val image : String,
        @SerializedName("Display") val display: Boolean

)