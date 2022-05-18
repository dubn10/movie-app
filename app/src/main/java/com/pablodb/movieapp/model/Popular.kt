package com.pablodb.movieapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Popular (@PrimaryKey @field:SerializedName("page") var page: Int = 0,
                    @field:SerializedName("results") var results: RealmList<Movie> = RealmList<Movie>(),
                    @field:SerializedName("total_pages") var total_pages: Int = 0,
                    @field:SerializedName("total_results") var total_results: Int = 0) : RealmObject()