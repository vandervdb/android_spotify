package org.vander.spotifyclient.data.playlist.mapper

import org.vander.spotifyclient.data.playlist.dto.SpotifyPlaylistDto
import org.vander.spotifyclient.data.playlist.dto.SpotifyPlaylistsResponseDto
import org.vander.spotifyclient.domain.data.Playlist
import org.vander.spotifyclient.domain.data.SpotifyPlaylistsResponse

fun SpotifyPlaylistsResponseDto.toDomain(): SpotifyPlaylistsResponse {
    return SpotifyPlaylistsResponse(
        items = items.map { it.toDomain() }
    )
}

fun SpotifyPlaylistDto.toDomain(): Playlist {
    return Playlist(
        id = id,
        name = name,
        imageUrl = images.firstOrNull()?.url.orEmpty()
    )
}
