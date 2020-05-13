package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EditProductViewModel @Inject constructor(
    private val router: Router,
    private val editProductRepository: EditProductProtocol.IEditProductRepository
) : BaseViewModel() {

    val isNewProduct = MutableLiveData<Boolean>()
    val isFieldsValid = ObservableBoolean()
    lateinit var product: Product
    var categoryId: String? = null

    val name = ObservableField<String>()
    val description = ObservableField<String>()
    val price = ObservableField<String>()
    val quantity = ObservableField<String>()

    fun getProduct(productId: String) {
        editProductRepository.getProduct(productId)
            .applySchedulers()
            .subscribe({ resp ->
                if (resp.isSuccessful) {
                    resp.body()?.product?.let {
                        product = it
                        name.set(it.name)
                        description.set(it.description)
                        price.set(it.price.toString())
                        quantity.set(it.count.toString())
                    }
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun updateProduct() {
        editProductRepository.updateProduct(
            Product(
                id = if (isNewProduct.value == true) null else product.id,
                name = name.get(),
                description = description.get(),
                price = price.get()?.toDouble(),
                count = quantity.get()?.toInt(),
                categoryId = categoryId
            ), isNewProduct.value ?: false
        )
            .applySchedulers()
            .subscribe({
                if (it.isSuccessful) {
                    router.exit()
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun listenFields(
        nameObservable: InitialValueObservable<CharSequence>,
        descriptionObservable: InitialValueObservable<CharSequence>,
        priceObservable: InitialValueObservable<CharSequence>,
        quantityObservable: InitialValueObservable<CharSequence>
    ) {
        Observable.combineLatest(
            nameObservable.skipInitialValue(),
            descriptionObservable.skipInitialValue(),
            priceObservable.skipInitialValue(),
            quantityObservable.skipInitialValue(),
            Function4<CharSequence, CharSequence, CharSequence, CharSequence, Boolean> { name, description, price, quantity ->
                return@Function4 name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty() && quantity.isNotEmpty()
            })
            .subscribeOn(Schedulers.io())
            .subscribe {
                isFieldsValid.set(it)
            }
            .addDisposable()
    }
}