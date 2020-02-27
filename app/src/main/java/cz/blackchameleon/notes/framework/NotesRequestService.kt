package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.framework.model.Note
import retrofit2.http.*

/**
 * Interface for API calls connected with notes
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
interface NotesRequestService {

    // Returns notes list
    @GET("notes")
    suspend fun getNotes(): List<Note>

    // Returns requested note with given id
    @GET("notes/{id}")
    suspend fun getNote(@Path("id") id: Int): Note

    // Request for creation of given note on server
    @POST("notes")
    suspend fun createNote(@Body note: Note): Note

    // Request for edit of given note on server
    @PUT("notes/{id}")
    suspend fun editNote(@Path("id") id: Int, @Body note: Note): Note

    // Request for note deletion from server
    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: Int)
}