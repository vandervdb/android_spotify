package org.vander.spotifyclient.domain.repository

import kotlinx.coroutines.flow.StateFlow
import org.vander.spotifyclient.domain.data.CurrentlyPlaying

interface SpotifyQueueRepository {
    val currentQueue: StateFlow<CurrentlyPlaying?>
    suspend fun getUserQueue(): Result<CurrentlyPlaying>
}
