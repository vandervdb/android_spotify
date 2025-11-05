package org.vander.spotifyclient.domain.auth

import org.vander.spotifyclient.data.remote.dto.TokenResponseDto

fun interface IAuthRemoteDatasource {
    suspend fun fetchAccessToken(code: String): Result<TokenResponseDto>
}
