package cz.blackchameleon.notes.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

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
