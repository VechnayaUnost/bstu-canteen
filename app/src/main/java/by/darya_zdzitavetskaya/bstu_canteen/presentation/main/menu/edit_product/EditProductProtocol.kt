package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import io.reactivex.Single
import retrofit2.Response

interface EditProductProtocol {

    interface IEditProductRepository {
        fun getProduct(productId: String): Single<Response<ProductResponse>>

        fun updateProduct(product: Product, isNewProduct: Boolean): Single<Response<Product>>
    }
}