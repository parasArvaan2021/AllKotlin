package com.main.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.adapter.ApiExampleAdapter
import com.main.kotlin.api.apiexample.interface1.ApiExampleInterface
import com.main.kotlin.api.apiexample.model.PostsModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*

class ApiExampleActivity : AppCompatActivity() {

    lateinit var progress: ProgressBar
    lateinit var adapter: ApiExampleAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_example)
        recyclerView = findViewById(R.id.rvApiExampleData)
        progress = findViewById(R.id.progressBar)
        val btnGetMethod: Button = findViewById(R.id.btnGetMethod)
        val btnPostMethod: Button = findViewById(R.id.btnPostMethod)
        val btnSearch: Button = findViewById(R.id.btnSearch)
        val etSearch: EditText = findViewById(R.id.etSearch)


        progress.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(this)


        btnGetMethod.setOnClickListener(View.OnClickListener {
            progress.visibility = View.VISIBLE
            callApiForGetMethod()
        })
        btnPostMethod.setOnClickListener {
            callApiForPostMethod()
        }
        btnSearch.setOnClickListener {

            if (!TextUtils.isEmpty(etSearch.text.toString())) {
                searchData(etSearch.text.toString())
                Log.i("paras", "onCreate: ${etSearch.text.toString()}")
            }

        }


    }

    fun callApiForGetMethod() {
        val apiInterface = ApiExampleInterface.create().getModel()

        apiInterface.enqueue(object : Callback<List<PostsModel>> {


            override fun onResponse(
                call: Call<List<PostsModel>>,
                response: Response<List<PostsModel>>
            ) {
                Log.i("paras", "onResponse: ${response.code()}")
                if (response.isSuccessful) {
                    progress.visibility = View.GONE
                }
                val responseBody = response.body()
                if (responseBody != null) {
                    adapter = ApiExampleAdapter(this@ApiExampleActivity)
                    adapter.setDataInList(responseBody)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@ApiExampleActivity, "Never response", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<PostsModel>>, t: Throwable) {
                Toast.makeText(this@ApiExampleActivity, "paras${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    fun callApiForPostMethod() {
        val sendingData = PostsModel(1, 1, "Kansagara", "Paras")
        val sendData = ApiExampleInterface.create().setUserData(sendingData)
//        val sendData = ApiExampleInterface.create().setUserData(1, 1, "Kansagara", "Paras")

        sendData.enqueue(object : Callback<PostsModel> {
            override fun onResponse(call: Call<PostsModel>, response: Response<PostsModel>) {

                Log.e("paras", "onResponsePost: ${response.code()}")

                if (response.body() != null) {
                    if (response.code() == 201) {
                        val respBody = response.body()
                        if (respBody != null)
                            Toast.makeText(
                                this@ApiExampleActivity,
                                respBody.body,
                                Toast.LENGTH_LONG
                            ).show()

                    } else {
                        Log.i("paras", "Error")
                    }
                }
            }

            override fun onFailure(call: Call<PostsModel>, t: Throwable) {
                Toast.makeText(this@ApiExampleActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun searchData(searchText: String) {
        val apiInterface = ApiExampleInterface.create().searchText(searchText)

        apiInterface.enqueue(object : Callback<PostsModel> {
            override fun onResponse(call: Call<PostsModel>, response: Response<PostsModel>) {
                Log.e("paras", "onResponse: ${response.code()}")
                val responseBody = response.body()

                if (responseBody != null) {
                    Log.e("paras", "onResponse: ${response.body()}")

                } else {
                    Toast.makeText(this@ApiExampleActivity, "Never response", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<PostsModel>, t: Throwable) {
                Toast.makeText(this@ApiExampleActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}

