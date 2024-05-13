package com.dicoding.submissionawalbelajarfundamentalandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.submissionawalbelajarfundamentalandroid.R
import com.dicoding.submissionawalbelajarfundamentalandroid.data.response.GithubResponse
import com.dicoding.submissionawalbelajarfundamentalandroid.data.response.ItemsItem
import com.dicoding.submissionawalbelajarfundamentalandroid.data.retrofit.ApiConfig
import com.dicoding.submissionawalbelajarfundamentalandroid.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
        private const val ITEMS = "items"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListUser.addItemDecoration(itemDecoration)

        findUser()
    }

    private fun findUser() {
        showLoading(true)
        val _user = ApiConfig.getApiService().search(ITEMS)
        _user.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ){
                showLoading(false)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        setUsernameData(responseBody.items)
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
        }
        )
    }

    private fun setUserPict() {
        TODO("Not yet implemented")
    }

    private fun setUsernameData(username: List<ItemsItem>) {
        val search_adapter = SearchAdapter()
        search_adapter.submitList(username)
        binding.rvListUser.adapter = search_adapter

        Glide.with(this@MainActivity)
            .load("https://api.github.com/search/users?q=sidiqpermana")
            .into(binding.)
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
            }
    }
}