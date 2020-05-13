package by.darya_zdzitavetskaya.bstu_canteen.api.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val product: Product
)

data class Product(
    @SerializedName("_id")
    val id: String? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val count: Int? = null,
    val categoryId: String? = null
)