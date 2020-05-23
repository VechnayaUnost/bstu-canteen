package by.darya_zdzitavetskaya.bstu_canteen.api.request

import com.google.gson.annotations.SerializedName

data class PayRequest(
    @SerializedName("currency")
    val currency:String,
    val items: List<PayItem>
)

data class PayItem(
    val id: Int
)