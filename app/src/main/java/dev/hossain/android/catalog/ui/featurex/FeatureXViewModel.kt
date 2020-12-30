package dev.hossain.android.catalog.ui.featurex

import android.content.Context
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dev.hossain.android.catalog.api.WebServiceApi
import dev.hossain.android.catalog.data.AppDatabase
import dev.hossain.android.catalog.data.model.Device
import dev.hossain.android.catalog.data.model.OpenGLVersion
import dev.hossain.android.catalog.data.model.ScreenDensity
import dev.hossain.android.catalog.ui.common.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class FeatureXViewModel @ViewModelInject constructor(private val api: WebServiceApi) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val isOperationInProgress = ObservableField(false)
    val message = ObservableField<Result<String>>()

    fun onSendWebRequest(name: String) {
        Timber.i("Sending web request with name: $name")

        compositeDisposable.add(
            api.hello(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isOperationInProgress.set(true) }
                .doOnSuccess { isOperationInProgress.set(false) }
                .doOnError { isOperationInProgress.set(false) }
                .subscribe({
                    message.set(Result.Success(it.messsage ?: ""))
                }, { error ->
                    Timber.e(error)
                    message.set(Result.Error(error))
                })
        )
    }

    fun testDb(context: Context) {
        GlobalScope.launch {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()

            val dao = db.deviceDao()
            var devices = dao.getAll()
            Timber.d("testDb: devices = $devices")

            val device = Device(modelName = "Hossain Device")
            dao.insert(device)

            devices = dao.getAll()
            Timber.d("testDb: devices = $devices")

            val density = ScreenDensity(20, 1)
            dao.insert(density)

            val oglv = OpenGLVersion("3.2", 1)
            dao.insert(oglv)

            val densities = dao.getAllDeviceInfo()
            Timber.d("testDb: devices = $densities")
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
