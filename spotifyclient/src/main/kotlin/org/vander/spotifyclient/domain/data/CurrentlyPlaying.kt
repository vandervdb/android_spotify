package org.vander.spotifyclient.domain.data

data class CurrentlyPlaying(
    val currentlyPlaying: Track? = null,
    val queue: Queue
)
