package cz.blackchameleon.notes.presentation.base

/**
 * Interface for backpress handling
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
interface OnBackPressedListener {
  fun onBackPressed(): Boolean = false
}