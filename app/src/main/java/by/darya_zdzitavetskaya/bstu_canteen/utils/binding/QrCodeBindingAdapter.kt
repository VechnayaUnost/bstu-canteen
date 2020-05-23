package by.darya_zdzitavetskaya.bstu_canteen.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Order
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

@BindingAdapter(value = ["qrCode"], requireAll = true)
fun ImageView.setQrCode(order: Order) {
    val content = Gson().toJson(order)
    val bitmap = BarcodeEncoder().encodeBitmap(content, BarcodeFormat.QR_CODE, 600, 600)
    setImageBitmap(bitmap)
}