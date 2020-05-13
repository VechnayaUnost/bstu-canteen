package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import by.darya_zdzitavetskaya.bstu_canteen.api.Api
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class EditProductRepository @Inject constructor(private val api: Api) :
    EditProductProtocol.IEditProductRepository {

    override fun getProduct(productId: String): Single<Response<ProductResponse>> {
        return api.getProduct(productId).subscribeOn(Schedulers.io())
    }

    override fun updateProduct(product: Product, isNewProduct: Boolean): Single<Response<Product>> {
        return if (isNewProduct) {
            api.createProduct(product).subscribeOn(Schedulers.io())
        } else {
            api.updateProduct(product).subscribeOn(Schedulers.io())
        }
    }
}