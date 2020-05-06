package by.darya_zdzitavetskaya.bstu_canteen.api.response

import by.darya_zdzitavetskaya.bstu_canteen.adapter.ItemRecyclerModel

data class CategoryResponse(
    val category: Category,
    val products: List<Product>
)

data class Product(
    override val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
    val count: Int,
    val categoryId: String
) : ItemRecyclerModel