package cz.blackchameleon.notes.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Context.closeSoftKeyboard(view: View) {
    val inputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showDialog(int: Int) {
    Toast.makeText(this, int, Toast.LENGTH_SHORT).show()
}