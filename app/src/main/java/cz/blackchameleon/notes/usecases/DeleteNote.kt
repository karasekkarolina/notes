package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository
import cz.blackchameleon.notes.domain.Note

class DeleteNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.deleteNote(note.id)
}