package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.framework.model.Result

/**
 * Receives notes from framework
 */
class NotesRepository(
    private val source: NotesSource
) {
    suspend fun getNotes(): Result<List<Note>> = source.getNotes()
    suspend fun getNote(id: Int): Result<Note> = source.getNote(id)
    suspend fun editNote(id: Int, note: Note): Result<Note>  = source.editNote(id, note)
    suspend fun createNote(note: Note): Result<Note>  = source.createNote(note)
    suspend fun deleteNote(id: Int): Result<Unit>  = source.deleteNote(id)
}