package com.pablodb.movieapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Movie(@field:SerializedName("adult") var adult: String? = "",
                 @field:SerializedName("backdrop_path") var backdrop_path: String? = "",
                 @PrimaryKey @field:SerializedName("id") var id: String = "",
                 @field:SerializedName("original_language") var original_language: String = "",
                 @field:SerializedName("original_title") var original_title: String = "",
                 @field:SerializedName("overview") var overview: String = "",
                 @field:SerializedName("popularity") var popularity: String = "",
                 @field:SerializedName("poster_path") var poster_path: String? = "",
                 @field:SerializedName("release_date") var release_date: String = "",
                 @field:SerializedName("title") var title: String = "",
                 @field:SerializedName("video") var video: String = "",
                 @field:SerializedName("vote_average") var vote_average: String = "",
                 @field:SerializedName("vote_count") var vote_count: String = "",) : RealmObject()