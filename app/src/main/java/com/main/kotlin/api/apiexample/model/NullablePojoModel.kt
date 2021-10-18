package com.main.kotlin.api.apiexample.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class NullablePojoModel : ArrayList<NullablePojoModel.NullablePojoModelItem>(){
    @Keep
    data class NullablePojoModelItem(
        @SerializedName("userId")
        val userId: Int?, // 10
        @SerializedName("id")
        val id: Int?, // 100
        @SerializedName("title")
        val title: String?, // at nam consequatur ea labore ea harum
        @SerializedName("body")
        val body: String? // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
    )
}