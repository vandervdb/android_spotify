package org.vander.spotifyclient.domain.datasource

import org.vander.spotifyclient.data.remote.dto.CurrentlyPlayingWithQueueDto


fun interface IRemoteQueueDataSource {
    suspend fun fetchUserQueue(): Result<CurrentlyPlayingWithQueueDto>
}
