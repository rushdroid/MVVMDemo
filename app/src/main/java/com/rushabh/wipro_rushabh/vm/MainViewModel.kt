package com.rushabh.wipro_rushabh.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rushabh.gojek.retrofit.RetrofitInterface
import com.rushabh.gojek.retrofit.RetrofitServiceGenerator
import com.rushabh.wipro_rushabh.roomDB.Facts
import com.rushabh.wipro_rushabh.roomDB.WiproDB
import com.rushabh.wipro_rushabh.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var facts: MutableLiveData<Facts> = MutableLiveData<Facts>()
    private val context: Context = application.applicationContext
    private val retrofitServiceGenerator: RetrofitServiceGenerator = RetrofitServiceGenerator()
    lateinit var disposable: Disposable
    private var isInternetAvailable = MutableLiveData<Boolean>()
    private var isLoading = MutableLiveData<Boolean>()
    var db = WiproDB(application.applicationContext)

    fun getFacts(): LiveData<Facts> {
        return facts
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun isInternetAvailable(): LiveData<Boolean> {
        return isInternetAvailable
    }

    fun getFactsFromAPI() {

        isLoading.value = true
        val list = db.factDAO().getAll()
        if (list.size > 0) {
            facts.postValue(list.get(0))
            return
        }


        if (!Utils.isConnectedToNetwork(context)) {
            isInternetAvailable.postValue(false)
            isLoading.postValue(false)
            return
        }

        isLoading.postValue(true)
        disposable = retrofitServiceGenerator.createService(RetrofitInterface::class.java)
            .getFacts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                facts.postValue(it)
                db.factDAO().insert(it)
                isLoading.postValue(false)
            }, {
                print(it.message)
                isLoading.postValue(false)
            })
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}