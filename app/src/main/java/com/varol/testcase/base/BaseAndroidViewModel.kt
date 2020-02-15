package com.varol.testcase.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.varol.testcase.internal.navigation.NavigationCommand
import com.varol.testcase.internal.util.Event
import com.varol.testcase.internal.util.Failure
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class BaseAndroidViewModel : AndroidViewModel {

    protected val context: Context

    private val isProgressVisible = MutableLiveData<Boolean>()

    constructor(context: Context) : super(context.applicationContext as Application) {
        this.context = context.applicationContext as Application
    }

    private val _failure = MutableLiveData<Event<Failure>>()
    var failure = _failure

    private val _success = MutableLiveData<Event<String>>()
    var success = _success

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    fun showProgress() {
        isProgressVisible.postValue(true)
    }

    fun hideProgress() {
        isProgressVisible.postValue(false)
    }

    val isLoading = isProgressVisible

    protected open fun handleFailure(failure: Failure) {
        hideProgress()
        this._failure.value = Event(failure)
    }

    protected fun getBackgroundScheduler(): Scheduler = Schedulers.io()

    protected fun getMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    protected fun startCountdown(period: Long, timeUnit: TimeUnit): Observable<Long> {
        return Observable.interval(period, timeUnit)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getBackgroundScheduler())
    }

    protected fun startTimer(period: Long, timeUnit: TimeUnit, repeat: Long = 1): Observable<Long> {
        return Observable.timer(period, timeUnit)
            .repeat(repeat)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getBackgroundScheduler())
    }

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
