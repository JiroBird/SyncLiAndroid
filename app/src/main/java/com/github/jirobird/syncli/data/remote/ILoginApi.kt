package com.github.jirobird.syncli.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ILoginApi {

    @GET("/v1/has_partner_name/{userName}")
    suspend fun hasUserName(@Path("userName")userName:String):Boolean

    @GET("/v1/login")
    suspend fun login():String

    @GET("/v1/register_new_user")
    suspend fun registerNewUser():String
}