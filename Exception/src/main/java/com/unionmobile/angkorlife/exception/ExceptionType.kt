package com.unionmobile.angkorlife.exception

sealed class ExceptionType(override val message: String) : Exception() {
    data object Network : ExceptionType("Connection failed") {
        private fun readResolve(): Any = Network
    }

    data class BadRequest(val errorMessage: String) : ExceptionType(errorMessage)

    data class UnAuthorized(val errorMessage: String) : ExceptionType(errorMessage)

    data class NotFound(val errorMessage: String) : ExceptionType(errorMessage)

    data class Conflict(val errorMessage: String) : ExceptionType(errorMessage)

    data object UnKnown : ExceptionType("Error occurred") {
        private fun readResolve(): Any = UnKnown
    }
}
