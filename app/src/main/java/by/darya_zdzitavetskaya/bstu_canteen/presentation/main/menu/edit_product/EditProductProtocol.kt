package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ProductResponse
import by.darya_zdzitavetskaya.bstu_canteen.api.response.UploadImageResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response

interface EditProductProtocol {

    interface IEditProductRepository {
        fun getProduct(productId: String): Single<ProductResponse>

        fun updateProduct(product: Product, isNewProduct: Boolean): Single<Response<Product>>

        fun uploadProductImage(file: MultipartBody.Part): Single<UploadImageResponse>
    }
}