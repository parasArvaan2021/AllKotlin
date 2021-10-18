package com.main.kotlin.api.apiexample.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PostsModel(
    @SerializedName("userId")
    val userId: Int?, // 1
    @SerializedName("id")
    val id: Int?, // 1
    @SerializedName("title")
    val title: String?, // sunt aut facere repellat provident occaecati excepturi optio reprehenderit
    @SerializedName("body")
    val body: String? // quia et suscipitsuscipit recusandae consequuntur expedita et cumreprehenderit molestiae ut ut quas totamnostrum rerum est autem sunt rem eveniet architecto
) {


}