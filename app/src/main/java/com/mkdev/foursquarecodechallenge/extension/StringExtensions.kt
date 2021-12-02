package com.mkdev.foursquarecodechallenge.extension


fun Int.computeLocationDistance():String {
    var result = (this / 1000).toString()
    if (result == "0") {
        result = " 1"
    }
    return result
}