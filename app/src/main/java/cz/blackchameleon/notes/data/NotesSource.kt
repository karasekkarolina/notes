package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.framework.Note
import cz.blackchameleon.notes.framework.Result

interface NotesSource {
    suspend fun getNotes(): Result<List<Note>>
    suspend fun getNote(id: Int): Result<Note>
    suspend fun editNote(id: Int, note: Note): Result<Note>
    suspend fun createNote(note: Note): Result<Note>
    suspend fun deleteNote(id: Int): Result<Unit>
}