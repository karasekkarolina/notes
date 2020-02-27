package cz.blackchameleon.notes.framework.model

/**
 * Representation of note entity
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
data class Note(
    var id: Int = 0,
    var title: String = ""
)