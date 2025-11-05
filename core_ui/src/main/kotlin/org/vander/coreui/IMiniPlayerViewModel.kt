package org.vander.coreui

import android.app.Activity
import kotlinx.coroutines.flow.StateFlow
import org.vander.spotifyclient.domain.state.PlayerState
import org.vander.spotifyclient.domain.state.SessionState
import org.vander.spotifyclient.domain.state.UIQueueState

interface IMiniPlayerViewModel {
    val sessionState: StateFlow<SessionState>
    val uIQueueState: StateFlow<UIQueueState>
    val playerState: StateFlow<PlayerState>
    fun startUp(activity: Activity)
    fun togglePlayPause()
    fun skipNext()
    fun skipPrevious()
    fun playTrack(trackId: String)
    fun checkIfTrackSaved(trackId: String)
    fun toggleSaveTrack(trackId: String)
}
