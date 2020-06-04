package by.darya_zdzitavetskaya.bstu_canteen.api.response

import android.os.Parcelable
import by.darya_zdzitavetskaya.bstu_canteen.adapter.ItemRecyclerModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class OrdersResponse(
    val orders: List<Order>
)

@Parcelize
data class Order(
    @SerializedName("_id")
    override val id: String,
    val url: String,
    val paidAt: String,
    val issued: Boolean
) : Parcelable, ItemRecyclerModel