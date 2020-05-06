package by.darya_zdzitavetskaya.bstu_canteen.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.darya_zdzitavetskaya.bstu_canteen.utils.Event
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Event<Boolean>>()
    val errorMessage = MutableLiveData<Event<String>>()

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    fun <T> Single<T>.applySchedulers(): Single<T> =
        this.observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true.toEvent()) }
            .doFinally { isLoading.postValue(false.toEvent()) }

    fun Completable.applySchedulers(): Completable =
        this.observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true.toEvent()) }
            .doFinally { isLoading.postValue(false.toEvent()) }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}