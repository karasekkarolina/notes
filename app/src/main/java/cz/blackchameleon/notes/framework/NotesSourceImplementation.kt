package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.data.NotesSource
import cz.blackchameleon.notes.framework.model.Note
import cz.blackchameleon.notes.framework.model.Result
import cz.blackchameleon.notes.framework.model.withErrorHandling

class NotesSourceImplementation(
    private val notesRequestService: NotesRequestService
) : NotesSource {
    override suspend fun getNotes(): Result<List<Note>> =
        withErrorHandling { notesRequestService.getNotes() }

    override suspend fun getNote(id: Int): Result<Note> =
        withErrorHandling { notesRequestService.getNote(id) }

    override suspend fun editNote(id: Int, note: Note): Result<Note> =
        withErrorHandling { notesRequestService.editNote(id, note) }

    override suspend fun createNote(note: Note): Result<Note> =
        withErrorHandling { notesRequestService.createNote(note) }

    override suspend fun deleteNote(id: Int): Result<Unit> =
        withErrorHandling { notesRequestService.deleteNote(id) }
}