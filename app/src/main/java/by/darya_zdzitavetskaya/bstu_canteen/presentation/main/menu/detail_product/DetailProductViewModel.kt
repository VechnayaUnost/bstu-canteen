package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product

import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartCache
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartItem
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DetailProductViewModel @Inject constructor(
    private val router: Router,
    private val detailProductRepository: DetailProductProtocol.IDetailProductRepository,
    private val cartCache: CartCache
) : BaseViewModel() {

    val productLiveData = MutableLiveData<Product>()
    var selectedQuantity = 1

    fun getProduct(productId: String) {
        detailProductRepository.getProduct(productId)
            .applySchedulers()
            .subscribe({ resp ->
                if (resp.isSuccessful) {
                    resp.body()?.let {
                        productLiveData.postValue(it.product)
                    }
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun addToCart() {
        cartCache.addProduct(CartItem(productLiveData.value!!, selectedQuantity))
        router.exit()
    }
}