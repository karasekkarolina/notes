package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository
import cz.blackchameleon.notes.domain.Note

class CreateNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.createNote(note)
}