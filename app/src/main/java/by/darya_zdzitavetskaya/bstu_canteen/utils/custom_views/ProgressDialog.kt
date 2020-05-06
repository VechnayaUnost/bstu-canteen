package by.darya_zdzitavetskaya.bstu_canteen.utils.custom_views

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import by.darya_zdzitavetskaya.bstu_canteen.R

class ProgressDialog(
    context: Context,
    private val onBackPressedListener: (() -> Unit)? = null
) :
    Dialog(context, R.style.FullScreenDialog) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        setContentView(view)
    }

    override fun onBackPressed() {
        /*super.onBackPressed()*/
        onBackPressedListener?.invoke()
    }
}