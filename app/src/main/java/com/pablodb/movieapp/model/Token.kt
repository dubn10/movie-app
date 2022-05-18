package com.pablodb.movieapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Token(@field:SerializedName("success") var success: Boolean = false,
            @field:SerializedName("expires_at") var expires_at: String = "",
            @PrimaryKey @field:SerializedName("request_token") var request_token: String = "") : RealmObject()