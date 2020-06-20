package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu

import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.adapter.BaseRecyclerListAdapter
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Category
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val router: Router,
    menuRepository: MenuProtocol.IMenuRepository
) : BaseViewModel() {

    init {
        menuRepository.getCategories()
            .applySchedulers()
            .subscribe({
                categoriesAdapter.submitList(it.categories)
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    val categoriesAdapter = BaseRecyclerListAdapter<Category>(R.layout.item_category) {
        router.navigateTo(Screens.CategoryScreen(this))
    }
}