package com.finalhints.mymandirdemo.network

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

private const val ERROR_NO_NETWORK = "Unable to connect to server. Please try again later."
private const val NETWORK_ERROR_CODE = 503
private const val INTERNAL_SERVER_ERROR_CODE = 500

/**
 * @author Bhumik Sapara
 */
private const val TAG = "NetworkExtensions"

fun <T : Any> Observable<T>.getResponse(pListener: NetworkListenerSE<T>) {

    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    if (pListener is NetworkListenerDSE<*>) {
                        (pListener as NetworkListenerDSE<*>).onDisposableReceived(d)
                    }
                }

                override fun onNext(pResponse: T) {
                    Log.d(TAG, "=== getResponse onNext: == ");
                    pListener.onSuccess(200, "SUCCESS", pResponse)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "=== getResponse onError: == ");
                    e.printStackTrace()
                    if (e is HttpException) {
                        Log.d(TAG, "=== getResponse onError - HttpException == " + e.code() + " --" + e.message());
                        pListener.onError(e.code(), e.message())
                    } else if (e is IOException) {
                        pListener.onError(NETWORK_ERROR_CODE, ERROR_NO_NETWORK)
                    } else {
                        pListener.onError(INTERNAL_SERVER_ERROR_CODE, e.message!!)
                    }
                }

                override fun onComplete() {

                }
            })
}

fun <T : Any> Single<T>.getResponse(pListener: NetworkListenerSE<T>) {

    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<T> {
                override fun onSuccess(value: T) {
                    Log.d(TAG, "=== getResponse onNext: == ");
                    pListener.onSuccess(200, "SUCCESS", value)
                }

                override fun onSubscribe(d: Disposable) {
                    if (pListener is NetworkListenerDSE<*>) {
                        (pListener as NetworkListenerDSE<*>).onDisposableReceived(d)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "=== getResponse onError: == ");
                    e.printStackTrace()
                    if (e is HttpException) {
                        Log.d(TAG, "=== getResponse onError - HttpException == " + e.code() + " --" + e.message());
                        pListener.onError(e.code(), e.message())
                    } else if (e is IOException) {
                        pListener.onError(NETWORK_ERROR_CODE, ERROR_NO_NETWORK)
                    } else {
                        pListener.onError(INTERNAL_SERVER_ERROR_CODE, e.message!!)
                    }
                }
            })
}
