package com.example.ducker.daos

import com.example.ducker.API.APIService
import com.example.ducker.API.LikesAPI
import com.example.ducker.data.Like
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.awaitResponse
import java.lang.Exception

class LikesDAO {
    companion object{
        private val retrofit : Retrofit = APIService.obtenerRetroFit()
        private val likesAPI = retrofit.create(LikesAPI::class.java)

        suspend fun crearLikes(authKey : String, like : Like) :Int {
            var respuesta = 0
            try {
                val call : Call<APIService.Mensaje> = likesAPI.crearLikes(authKey, like)
                val response = call.awaitResponse()
                respuesta = response.code()
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
            return respuesta
        }

        suspend fun obtenerCantidadLikesQuack(authKey: String, id : Int) : List<Like>{
            var listaLikes : List<Like> = listOf()
            try {
                val call : Call<List<Like>> = likesAPI.obtenerCantidadLikesQuack(authKey, id)
                listaLikes = call.await()
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
            return listaLikes
        }

        suspend fun eliminarLikes(authKey: String, idLike : Int) : Int {
            var respuesta = 0
            try {
                var call : Call<APIService.Mensaje> = likesAPI.eliminarLikes(authKey, idLike)
                val response = call.awaitResponse()
                respuesta = response.code()
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
            return respuesta
        }
    }
}