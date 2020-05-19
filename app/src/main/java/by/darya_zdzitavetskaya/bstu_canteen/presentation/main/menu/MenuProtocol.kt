package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu

import by.darya_zdzitavetskaya.bstu_canteen.api.response.CategoriesResponse
import io.reactivex.Single

interface MenuProtocol {

    interface IMenuRepository {
        fun getCategories(): Single<CategoriesResponse>
    }
}