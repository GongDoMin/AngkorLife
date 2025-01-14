package com.unionmobile.angkorlife.exception

sealed class ExceptionType : Exception() {
    data class Network(override val message: String) : ExceptionType()

    data class BadRequest(override val message: String) : ExceptionType()

    data class UnAuthorized(override val message: String) : ExceptionType()

    data class NotFound(override val message: String) : ExceptionType()

    data class Conflict(override val message: String) : ExceptionType()

    data object UnKnown : ExceptionType() {
        private fun readResolve(): Any = UnKnown
    }
}
