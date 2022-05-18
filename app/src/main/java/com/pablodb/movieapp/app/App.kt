package com.pablodb.movieapp.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application(){
    val TAG = "App"
    var realm : Realm? = null

    companion object {
        lateinit var instance: App
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this


        initRealm()
    }

    fun initRealm(){
        Realm.init( this )
        val config = RealmConfiguration.Builder()
            .name("movieapp")
            .schemaVersion(1 )
            .migration { realm, oldVersion, newVersion ->
                if (newVersion > oldVersion) {
                    realm.deleteAll()
                }
            }
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(config)
    }
}