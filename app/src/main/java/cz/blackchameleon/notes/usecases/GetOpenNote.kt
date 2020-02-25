package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.OpenNoteRepository

class GetOpenNote(
    private val openNoteRepository: OpenNoteRepository
) {
    suspend operator fun invoke() = openNoteRepository.getOpenNote()
}