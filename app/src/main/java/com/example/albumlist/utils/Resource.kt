package com.example.albumlist.utils;

class Resource<out T>(val status: Status, val data: T?, val message: String? = null) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }
}
