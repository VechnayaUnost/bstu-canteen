package by.darya_zdzitavetskaya.bstu_canteen.api.response

import android.os.Parcelable
import by.darya_zdzitavetskaya.bstu_canteen.adapter.ItemRecyclerModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CategoryResponse(
    val category: Category,
    val products: List<ShortProduct>
)

@Parcelize
data class ShortProduct(
    @SerializedName("_id")
    override val id: String,
    val name: String,
    val imageUrl: String
) : ItemRecyclerModel, Parcelable