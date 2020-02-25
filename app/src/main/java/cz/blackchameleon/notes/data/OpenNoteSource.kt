package cz.blackchameleon.notes.data

import cz.blackchameleon.notes.domain.Note

interface OpenNoteSource {
    suspend fun getOpenNote(): Note
    suspend fun setOpenNote(note: Note)
}