package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.domain.Note

interface NotesSource {
    suspend fun getNotes(): List<Note>
    suspend fun getNote(id: Int): Note
    suspend fun editNote(id: Int, note: Note): Note
    suspend fun createNote(note: Note): Note
    suspend fun deleteNote(id: Int)
}