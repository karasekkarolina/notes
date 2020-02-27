package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.framework.model.Result

/**
 * Interface specifying which data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
interface NotesSource {
    suspend fun getNotes(): Result<List<Note>>
    suspend fun getNote(id: Int): Result<Note>
    suspend fun editNote(id: Int, note: Note): Result<Note>
    suspend fun createNote(note: Note): Result<Note>
    suspend fun deleteNote(id: Int): Result<Unit>
}