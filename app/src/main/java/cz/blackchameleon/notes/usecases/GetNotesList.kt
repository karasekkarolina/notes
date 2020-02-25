package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository

class GetNotesList(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke() = notesRepository.getNotes()
}