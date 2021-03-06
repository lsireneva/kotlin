package com.example.luba.seasonalclone.source

import android.content.Context
import com.example.luba.seasonalclone.models.ProduceByState
import com.example.luba.seasonalclone.models.ProduceItem
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset

val SEASONAL_METADATA_JSON = "seasonal_metadata.json"
val SEASONAL_CALIFORNIA_JSON = "seasonal_california.json"


fun getProduceItemsFromJson (context: Context): List<ProduceItem> {

    val assetManager = context.assets

    val metadataFile = assetManager.open(SEASONAL_METADATA_JSON)
    val metadata = metadataFile.readBytes().toString(Charset.defaultCharset())

    val stateJSONFile = assetManager.open(SEASONAL_CALIFORNIA_JSON)
    val stateJSONString = stateJSONFile.readBytes().toString(Charset.defaultCharset())
    val produceItems = parseJSONFile(metadata, stateJSONString)


    return produceItems;
}

private fun parseJSONFile(jsonString: String, stateJSONString: String): List<ProduceItem> {

    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()

    //parse seasonal_metadata
    val produceItemList: List<ProduceItem> = gsonBuilder.fromJson(jsonString, object :
            TypeToken<List<ProduceItem>>() {}.type)

    //parse seasonal_california
    val californiaProduceIds: List<ProduceByState> = gsonBuilder.fromJson(stateJSONString, object :
            TypeToken<List<ProduceByState>>() {}.type)


    val juneIds = californiaProduceIds.filter { it -> it.months.june }
            .map { it -> it.id }


    val juneProduce = produceItemList.filter { it -> (juneIds.contains(it.id)) }

    return juneProduce
}
