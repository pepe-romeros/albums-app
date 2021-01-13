package com.example.albumlist.utils

object AppIdlingResource {
    private const val RESOURCE = "ALBUM"

    @JvmField
    val countingIdlingResource = EspressoIdlingResource(RESOURCE)

    fun startProcess() {
        countingIdlingResource.increment()
    }

    fun endProcess() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}