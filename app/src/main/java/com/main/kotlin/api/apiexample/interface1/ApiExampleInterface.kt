package com.main.kotlin.api.apiexample.interface1


import android.graphics.Interpolator
import com.main.kotlin.api.apiexample.model.PostsModel
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiExampleInterface {

    @GET("posts/")
    fun getModel(): Call<List<PostsModel>>

    @POST("posts")
    fun setUserData(
        @Body sendData: PostsModel
    ): Call<PostsModel>

    @FormUrlEncoded
    @POST("posts")
    fun setUserData(
        @Field("id") id: Int,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Call<PostsModel>


    @GET("posts/{id}")
    fun searchText(@Path("id") title: String): Call<PostsModel>
    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"


        fun create(): ApiExampleInterface {

            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client.build())
                .build()
            return retrofit.create(ApiExampleInterface::class.java)


        }
    }
}