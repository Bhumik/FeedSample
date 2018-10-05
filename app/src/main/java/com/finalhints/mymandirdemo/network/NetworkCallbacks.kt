package com.finalhints.mymandirdemo.network

import io.reactivex.disposables.Disposable

/**
 *
 * @author Bhumik Sapara
 * callback listener to get response of api
 * response object returned here is in form for CDD - code, description, data
 * where code 0 means success and other means failure
 *
 * @param <T>
</T> */
interface NetworkListenerSE<T> {
    fun onSuccess(code: Int, pDescription: String, response: T)

    fun onError(code: Int, pDescription: String)

}

interface NetworkListenerDSE<T> : NetworkListenerSE<T> {
    fun onDisposableReceived(pDisposable: Disposable)
}

interface InteractorCallback<T> {
    fun onSuccess(response: T)
    fun onError(code: Int, message: String)
}
