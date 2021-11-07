package com.example.composektor.data.remote

import com.example.composektor.data.remote.dto.PostRequest
import com.example.composektor.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {
    suspend fun getPosts(): List<PostResponse>

    suspend fun createPosts(postRequest: PostRequest): PostResponse?

    companion object{
        fun create(): PostsService = PostServiceImpl(
            client = HttpClient(Android){
                install(Logging){
                    level = LogLevel.ALL
                }
                install(JsonFeature){
                    serializer = KotlinxSerializer()
                }
            }
        )
    }
}