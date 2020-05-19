package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.webkit.MimeTypeMap
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Product
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseViewModel
import by.darya_zdzitavetskaya.bstu_canteen.utils.rotateImageIfRequired
import by.darya_zdzitavetskaya.bstu_canteen.utils.toEvent
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import ru.terrakok.cicerone.Router
import java.io.File
import java.io.FileOutputStream
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
    val imageUrl = ObservableField<String>()

    fun getProduct(productId: String) {
        editProductRepository.getProduct(productId)
            .applySchedulers()
            .subscribe({ resp ->
                resp.product.let {
                    product = it
                    name.set(it.name)
                    description.set(it.description)
                    price.set(it.price.toString())
                    quantity.set(it.count.toString())
                    imageUrl.set(it.imageUrl)
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
                categoryId = categoryId,
                imageUrl = imageUrl.get()
            ), isNewProduct.value ?: false
        )
            .applySchedulers()
            .subscribe({
                if (it.isSuccessful) {
                    router.exit()
                } else {
                    errorMessage.postValue(it.message().toEvent())
                }
            }, {
                it.printStackTrace()
            }).addDisposable()
    }

    fun uploadAvatar(pathToFile: String) {
        val file = File(pathToFile)
        val resBitmapImage =
            rotateImageIfRequired(pathToFile, BitmapFactory.decodeFile(pathToFile))

        val output = FileOutputStream(file)
        resBitmapImage?.compress(Bitmap.CompressFormat.JPEG, 100, output)
        output.flush()
        output.close()

        MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension)?.let { mimeType ->
            val requestFile: RequestBody = file.asRequestBody(mimeType.toMediaTypeOrNull())
            val fileBody = MultipartBody.Part.createFormData("file", file.name, requestFile)

            editProductRepository.uploadProductImage(fileBody)
                .applySchedulers()
                .subscribe({
                    imageUrl.set(it.url)
                }, {
                    it.printStackTrace()
                })
        }
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