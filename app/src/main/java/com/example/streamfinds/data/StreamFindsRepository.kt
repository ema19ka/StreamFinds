package com.example.streamfinds.data

import com.example.streamfinds.model.FindDTO
import com.example.streamfinds.network.MovieDbAPI

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface StreamFindsRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getFindItems(): List<FindDTO>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */

class NetworkStreamFindsRepository(
    private val movieDbAPI: MovieDbAPI
) : StreamFindsRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getFindItems(): List<FindDTO> = movieDbAPI.findItems()
}
