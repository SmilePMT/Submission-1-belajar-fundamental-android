package com.dicoding.submissionawalbelajarfundamentalandroid.data.retrofit

import com.dicoding.submissionawalbelajarfundamentalandroid.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
interface ApiService{

    @Headers("Authorization: token <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>")
    @GET("search/users")
    fun search(
        @Query("q") username: String
    ): Call<GithubResponse>
}

