package com.example.luba.seasonalclone.models

import com.google.gson.annotations.SerializedName


data class ProduceByState (
    @SerializedName ("ID") val id : String,
    @SerializedName ("months") val months: ProduceMonth
)

