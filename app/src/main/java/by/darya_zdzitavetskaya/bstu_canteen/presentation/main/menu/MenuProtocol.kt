package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu

import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoriesResponse
import io.reactivex.Single
import retrofit2.Response

interface MenuProtocol {

    interface IMenuRepository {
        fun getCategories(): Single<Response<CategoriesResponse>>
    }
}