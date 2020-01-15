package com.rushabh.gojek.retrofit

import com.rushabh.wipro_rushabh.roomDB.Facts
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("facts.json")
    fun getFacts(): Observable<Facts>

}