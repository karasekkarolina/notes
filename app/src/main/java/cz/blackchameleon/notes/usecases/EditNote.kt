package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.NotesRepository
import cz.blackchameleon.notes.framework.Note

class EditNote(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.editNote(note.id, note)
}