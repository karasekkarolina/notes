package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.OpenNoteRepository
import cz.blackchameleon.notes.domain.Note

class SetOpenNote(
    private val openNoteRepository: OpenNoteRepository
) {
    suspend operator fun invoke(note: Note) = openNoteRepository.setOpenNote(note)
}