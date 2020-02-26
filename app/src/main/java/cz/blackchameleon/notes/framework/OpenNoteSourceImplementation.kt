package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.data.OpenNoteSource
import cz.blackchameleon.notes.framework.model.Note

class OpenNoteSourceImplementation : OpenNoteSource {
    private var openNote: Note? = null

    override suspend fun getOpenNote(): Note? = openNote

    override suspend fun setOpenNote(note: Note?) {
        openNote = note
    }
}