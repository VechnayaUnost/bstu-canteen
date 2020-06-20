package by.darya_zdzitavetskaya.bstu_canteen.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun View.makeKeyboardVisible(isVisible: Boolean) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    if (isVisible) {
        requestFocus()
        imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    } else {
        imm?.hideSoftInputFromWindow(this.windowToken, 0)
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}