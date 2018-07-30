package com.example.ttask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    val testApi by lazy {
        TestApi.createWebService()
    }

    val call = testApi.getUsers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        getUsers()
    }

    private fun getUsers() {
        call.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: retrofit2.Response<List<User>>?) {
                if (response != null) {
                    val users: List<User> = response.body()!!
                    recyclerView.adapter = UsersAdapter(users)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }

}
