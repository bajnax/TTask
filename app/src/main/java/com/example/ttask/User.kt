package com.example.ttask

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("age") val age : Int,
        @SerializedName("avatar") val avatar : String,
        @SerializedName("id") val id : Int,
        @SerializedName("lastSeen") val lastSeen : String,
        @SerializedName("name") val name : String,
        @SerializedName("similarity") val similarity : Int,
        @SerializedName("status") val status : String,
        @SerializedName("unreadMessages") val unreadMessages : Int
)