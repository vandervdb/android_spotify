package org.vander.spotifyclient.data.local.mapper

import org.vander.spotifyclient.domain.state.PlayerState
import org.vander.spotifyclient.domain.state.PlayerStateData

fun PlayerStateData.toAppPlayerState(isSaved: Boolean): PlayerState {
    return PlayerState(this, isSaved)
}
