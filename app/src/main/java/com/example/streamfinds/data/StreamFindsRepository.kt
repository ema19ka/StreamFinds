package com.example.streamfinds.data

import com.example.streamfinds.model.SearchDTO
import com.example.streamfinds.network.MovieDbAPI

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface StreamFindsRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getFindItems(api: String, query: String): List<SearchDTO>
}

/**
 * Network Implementation of Repository that fetch movie list from api.
 */

class NetworkStreamFindsRepository(
    private val movieDbAPI: MovieDbAPI
) : StreamFindsRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getFindItems(api: String, query: String): List<SearchDTO> = movieDbAPI.findItems(api, query)
}
