package cz.blackchameleon.notes.usecases

import cz.blackchameleon.notes.data.OpenNoteRepository

/**
 * Use case that returns currently opened note, null otherwise
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class GetOpenNote(
    private val openNoteRepository: OpenNoteRepository
) {
    suspend operator fun invoke() = openNoteRepository.getOpenNote()
}