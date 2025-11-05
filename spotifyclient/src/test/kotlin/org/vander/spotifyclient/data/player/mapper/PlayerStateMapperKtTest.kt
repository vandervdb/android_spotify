package org.vander.spotifyclient.data.player.mapper

import android.util.Log
import com.spotify.protocol.types.PlayerState
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class PlayerStateMapperKtTest {

    @Before
    fun setUp() {
        // Mock Android's Log to make JVM tests independent of the Android runtime
        mockkStatic(Log::class)
        every { Log.d(any<String>(), any<String>()) } returns 0
        every { Log.e(any<String>(), any<String>()) } returns 0
        every { Log.e(any<String>(), any<String>(), any<Throwable>()) } returns 0
        every { Log.w(any<String>(), any<String>()) } returns 0
        every { Log.i(any<String>(), any<String>()) } returns 0
        every { Log.v(any<String>(), any<String>()) } returns 0
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `toPlayerStateData should not crash and provide sensible defaults with relaxed PlayerState`() {
        // Given a relaxed mock of PlayerState (all fields default to null/false)
        val mockPlayerState = mockk<PlayerState>(relaxed = true)

        // When
        val result = mockPlayerState.toPlayerStateData()

        assertEquals("Unknown Track", result.trackName)
        assertEquals("Unknown Artist", result.artistName)
        assertEquals("", result.coverId)
        assertEquals("", result.trackId)
        assertEquals(false, result.isPaused)
        assertEquals(true, result.playing)
        assertEquals(false, result.shuffling)
        assertEquals(false, result.repeating)
        assertEquals(false, result.stopped)
        assertEquals(false, result.skippingNext)
        assertEquals(false, result.skippingPrevious)
    }

    @Test
    fun `extractSpotifyCoverIdOrNull should return cover ID when valid URI`() {
        val validCoverUri = "ImageId{spotify:image:123456789abcdefg'}"
        val result = validCoverUri.extractSpotifyCoverIdOrNull()
        assertEquals("123456789abcdefg", result)
    }

    @Test
    fun `extractSpotifyCoverIdOrNull should return null when URI does not start with expected format`() {
        val invalidCoverUri = "spotify:image:123456789abcdefg"
        val result = invalidCoverUri.extractSpotifyCoverIdOrNull()
        assertNull(result)
    }

    @Test
    fun `extractSpotifyCoverIdOrNull should return null when URI is completely invalid`() {
        val completelyInvalidUri = "invalid_uri"
        val result = completelyInvalidUri.extractSpotifyCoverIdOrNull()
        assertNull(result)
    }

    @Test
    fun `extractSpotifyCoverIdOrNull should return null when URI has empty value`() {
        val emptyUri = ""
        val result = emptyUri.extractSpotifyCoverIdOrNull()
        assertNull(result)
    }
}
