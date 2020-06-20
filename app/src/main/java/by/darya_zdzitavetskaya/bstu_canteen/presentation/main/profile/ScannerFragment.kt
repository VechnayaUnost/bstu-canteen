package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.zxing.Result
import dagger.android.support.DaggerFragment
import me.dm7.barcodescanner.zxing.ZXingScannerView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ScannerFragment : DaggerFragment(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mScannerView = ZXingScannerView(activity)
        return mScannerView
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
    }

    override fun handleResult(rawResult: Result) {
        Log.v("!!!", rawResult.text)
        try {
            val order = Gson().fromJson(rawResult.text, Order::class.java)
            router.navigateTo(Screens.DetailOrderScreen(order))
        } catch (e: JsonSyntaxException) {
            Toast.makeText(activity, getString(R.string.scanner_error_message), Toast.LENGTH_SHORT)
                .show()

            Handler().postDelayed(
                Runnable { mScannerView?.resumeCameraPreview(this) },
                2000
            )
        }
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }
}