package by.darya_zdzitavetskaya.bstu_canteen.api.response

import by.darya_zdzitavetskaya.bstu_canteen.adapter.ItemRecyclerModel
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    val categories: List<Category>
)

data class Category(
    @SerializedName("_id")
    override val id: String,
    val name: String,
    val imageUrl: String
) : ItemRecyclerModel