package cz.blackchameleon.notes.extensions

import android.content.Context
import android.widget.Toast
import android.widget.Toast.makeText

fun Toast.showDialog(context: Context, int: Int) {
    makeText(context, int, Toast.LENGTH_SHORT).show()
}