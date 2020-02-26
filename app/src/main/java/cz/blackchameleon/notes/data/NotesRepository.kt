package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.Note
import cz.blackchameleon.notes.framework.Result

/**
 * Receives notes from framework
 */
class NotesRepository(
    private val source: NotesSource
) {
    suspend fun getNotes(): Result<List<Note>> = source.getNotes()
    suspend fun getNote(id: Int): Note = source.getNote(id)
    suspend fun editNote(id: Int, note: Note) = source.editNote(id, note)
    suspend fun createNote(note: Note) = source.createNote(note)
    suspend fun deleteNote(id: Int) = source.deleteNote(id)
}