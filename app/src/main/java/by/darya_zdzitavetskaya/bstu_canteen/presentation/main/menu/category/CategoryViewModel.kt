package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category

import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.adapter.CategoryRecyclerListAdapter
import by.darya_zdzitavetskaya.bstu_canteen.adapter.ProductItemClickListener
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Category
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct
import by.darya_zdzitavetskaya.bstu_canteen.navigation.Screens
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val router: Router,
    private val categoryRepository: CategoryProtocol.ICategoryRepository
) : BaseViewModel(), ProductItemClickListener {

    val productsAdapter = CategoryRecyclerListAdapter(R.layout.item_product, this)

    var category: Category? = null

    override fun onProductItemClicked(product: ShortProduct) {
        router.navigateTo(Screens.DetailProductScreen(product))
    }

    override fun onEditClicked(product: ShortProduct) {
        router.navigateTo(Screens.EditProductScreen(product, category?.id ?: ""))
    }

    override fun onDeleteClicked(product: ShortProduct) {
        categoryRepository.deleteProduct(product.id)
            .applySchedulers()
            .subscribe({
                val tempList = mutableListOf<ShortProduct>()
                tempList.addAll(productsAdapter.currentList)
                tempList.remove(product)
                productsAdapter.submitList(tempList)
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun getCategory(categoryId: String) {
        categoryRepository.getCategory(categoryId)
            .applySchedulers()
            .subscribe({
                productsAdapter.submitList(it.products)
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun createNewProduct() {
        router.navigateTo(Screens.EditProductScreen(categoryId = category?.id ?: ""))
    }
}