package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category

import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoryResponse
import io.reactivex.Completable
import io.reactivex.Single

interface CategoryProtocol {

    interface ICategoryRepository {
        fun getCategory(categoryId: String): Single<CategoryResponse>

        fun deleteProduct(productId: String): Completable
    }
}