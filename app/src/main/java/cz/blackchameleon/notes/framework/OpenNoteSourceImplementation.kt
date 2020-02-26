package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.data.OpenNoteSource

class OpenNoteSourceImplementation : OpenNoteSource {
    private var openNote: Note? = null

    override suspend fun getOpenNote(): Note =
        openNote ?: throw IllegalStateException("No open note found")

    override suspend fun setOpenNote(note: Note) {
        openNote = note
    }
}