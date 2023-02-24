package com.ludimosdemo.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ludimosdemo.models.BallTrackResponse
import com.ludimosdemo.utils.JSONUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BallTrackRepository @Inject constructor(@ApplicationContext private var context : Context) {

    suspend fun getBallTrackResponse(): BallTrackResponse {
        val gson: Gson = GsonBuilder().create()
        return gson.fromJson(JSONUtils.getJSONFileString(context), BallTrackResponse::class.java)
    }

}