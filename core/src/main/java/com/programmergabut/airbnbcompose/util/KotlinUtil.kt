package com.programmergabut.airbnbcompose.util

fun ran(start: Int, end: Int) = (start..end).random()

fun String?.dashIfNullOrEmpty(): String {
    if(this.isNullOrEmpty()){
        return "-"
    }
    return this
}