package org.vander.spotifyclient.domain.error

sealed class SessionError(message: String? = null, cause: Throwable?) : Exception(message) {
    data class AuthFailed(override val cause: Throwable? = null) :
        SessionError("Spotify Authorization Failed", cause)

    data class RemoteConnectionFailed(override val cause: Throwable? = null) :
        SessionError("Spotify Remote Connection Failed", cause)

    data class UnknownError(override val cause: Throwable? = null) :
        SessionError("Unknown error occurred", cause)
}
