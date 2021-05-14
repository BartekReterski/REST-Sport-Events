package com.sportdata.dazn.interfaces

import com.sportdata.dazn.models.ScheduleEvents
import com.sportdata.dazn.models.SportEvents
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/getEvents")
    fun getSportEvents(): Call<List<SportEvents>>

    @GET("/getSchedule")
    fun getSportSchedule(): Call<List<ScheduleEvents>>
}