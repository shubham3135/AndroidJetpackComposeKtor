package com.example.composektor.data.remote

import com.example.composektor.data.remote.dto.PostRequest
import com.example.composektor.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(
    private val client: HttpClient
) : PostsService {
    override suspend fun getPosts(): List<PostResponse> = try {
        client.get {
            url(HttpRoutes.POSTS)
        }
    }catch (e: RedirectResponseException){
        // 3xx - responses
        println("Error: ${e.response.status.description}")
        emptyList()
    }catch (e: ClientRequestException){
        // 4xx - responses
        println("Error: ${e.response.status.description}")
        emptyList()
    }catch (e: ServerResponseException){
        // 5xx - responses
        println("Error: ${e.response.status.description}")
        emptyList()
    }catch (e: Exception){
        // 3xx - responses
        println("Error: ${e.message}")
        emptyList()
    }

    override suspend fun createPosts(postRequest: PostRequest): PostResponse? = try {
        client.post<PostResponse> {
            url(HttpRoutes.POSTS)
            contentType(ContentType.Application.Json)
            body = postRequest
        }
    }catch (e: RedirectResponseException){
        // 3xx - responses
        println("Error: ${e.response.status.description}")
        null
    }catch (e: ClientRequestException){
        // 4xx - responses
        println("Error: ${e.response.status.description}")
        null
    }catch (e: ServerResponseException){
        // 5xx - responses
        println("Error: ${e.response.status.description}")
        null
    }catch (e: Exception){
        println("Error: ${e.message}")
        null
    }
}