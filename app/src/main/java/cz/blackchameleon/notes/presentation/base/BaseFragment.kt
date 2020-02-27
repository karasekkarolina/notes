package cz.blackchameleon.notes.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Base class for fragments in app
 * Provides logically separated UI setting functions
 *
 * @see Fragment
 * @see OnBackPressedListener
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
abstract class BaseFragment(layout: Int) : Fragment(layout), OnBackPressedListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
        setupObservers()
    }

    override fun onBackPressed(): Boolean = false

    // For setup view properties, adapters etc.
    open fun setupView() {}

    // For setup view listeners.
    open fun setupListeners() {}

    // For setup data observers, typically form view-model.
    open fun setupObservers() {}
}
