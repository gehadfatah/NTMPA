package com.goda.npmoa.domain.common

import com.goda.npmoa.data_layer.model.ErrorServer
import com.goda.npmoa.data_layer.model.ErrorServerWithMessage
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <T> Single<T>.retryIfOffline(): Single<T> {
    return retry { err ->
        val retry: Boolean = (err is UnknownHostException)
        if (retry) Thread.sleep(3000)
        retry
    }
}

fun <T> Flowable<T>.mapNetworkErrors(): Flowable<T> {
    return singleOrError().onErrorResumeNext { error ->

        when (error) {
            is SocketTimeoutException -> Single.error(TimeoutException())
            is HttpException -> {
                val single: Single<T> = when {
                    error.code() == 401 -> {
                        var errorServerWithMessage = Gson()?.fromJson(
                            error.response()?.errorBody()?.string(),
                            ErrorServerWithMessage::class.java
                        )
                        if (errorServerWithMessage != null) {
                            Single.error(UnAuthorizedException(errorServerWithMessage?.message))

                        } else
                            Single.error(UnAuthorizedException(error.message()))
                    }
                    else -> {
                        var errorServer = Gson()?.fromJson(
                            error.response()?.errorBody()?.string(),
                            ErrorServer::class.java
                        )
                        var errorServerWithMessage = Gson()?.fromJson(
                            error.response()?.errorBody()?.string(),
                            ErrorServerWithMessage::class.java
                        )

                        if (errorServer != null && !errorServer.violations.isNullOrEmpty()) {
                            var violation = errorServer.violations[0]?.message
                            if (violation.isNullOrEmpty()) {
                                Single.error(InternalServerErrorException())

                            } else
                                Single.error(RequestErrorException(violation))

                        } else if (errorServerWithMessage != null && !errorServerWithMessage.message.isNullOrEmpty()) {
                            Single.error(RequestErrorException(errorServerWithMessage.message))
                        } else Single.error(InternalServerErrorException())

                    }
                }

                single
            }
            else -> Single.error(error)
        }
    }.toFlowable()
}


fun <T> Single<T>.mapNetworkErrorsPss(): Single<T> {
    return onErrorResumeNext { error ->

        when (error) {
            is SocketTimeoutException -> Single.error(TimeoutException())
            is HttpException -> {
                val single: Single<T> = when {
                    error.code() == 401 -> {
                        var errorServerWithMessage = Gson()?.fromJson(
                            error.response()?.errorBody()?.string(),
                            ErrorServerWithMessage::class.java
                        )
                        if (errorServerWithMessage != null) {
                            Single.error(UnAuthorizedException(errorServerWithMessage?.message))

                        } else
                            Single.error(UnAuthorizedException(error.message()))
                    }

                    else -> {

                       Single.error(InternalServerErrorException())

                    }
                }
                single
            }
            else -> Single.error(error)
        }
    }
}

/*fun Completable.mapNetworkErrors(): Completable {
    return onErrorResumeNext { error ->
        when (error) {
            is HttpException -> {
                when {
                    error.code() == 401 -> Completable.error(UnAuthorizedException())
                    else -> Completable.error(InternalServerErrorException())
                }
            }
            else -> Completable.error(error)
        }
    }
}*/

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}



suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {


                    when (val code = throwable.code()) {
                        401 -> {
                            val errorResponse = try {
                                throwable.response()?.errorBody()?.string()?.let {
                                    Gson()?.fromJson(
                                        it,
                                        ErrorServerWithMessage::class.java
                                    )
                                }
                            } catch (exception: Exception) {
                                null
                            }
                            ResultWrapper.GenericError(code, errorResponse)

                        }
                        else -> {
                            val errorResponse = try {
                                throwable.response()?.errorBody()?.string()?.let {


                                    Gson()?.fromJson(
                                        it,
                                        ErrorServer::class.java
                                    )
                                }
                            } catch (exception: Exception) {
                                null
                            }

                            if (errorResponse == null) {
                                ResultWrapper.GenericError(code, convertErrorBody(throwable))
                            } else
                                ResultWrapper.GenericErrorServer(code, errorResponse)

                        }
                    }

                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorServerWithMessage? {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            /*  val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
              moshiAdapter.fromJson(it)*/

            return Gson()?.fromJson(
                /* error.response()?.errorBody()?*/it,
                ErrorServerWithMessage::class.java
            )
        }
    } catch (exception: Exception) {
        null
    }


}