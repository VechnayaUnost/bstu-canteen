package by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.R
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct
import by.darya_zdzitavetskaya.bstu_canteen.databinding.FragmentEditProductBinding
import by.darya_zdzitavetskaya.bstu_canteen.navigation.CATEGORY_ID_EXTRA
import by.darya_zdzitavetskaya.bstu_canteen.presentation.base.BaseFragment
import by.darya_zdzitavetskaya.bstu_canteen.utils.getFileRealPath
import com.jakewharton.rxbinding3.widget.textChanges
import com.sembozdemir.permissionskt.askPermissions
import javax.inject.Inject

const val SELECT_PICTURE = 1
const val REQUEST_IMAGE_CAPTURE = 2

class EditProductFragment : BaseFragment<EditProductViewModel, FragmentEditProductBinding>() {
    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModelClass: Class<EditProductViewModel> = EditProductViewModel::class.java

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    override val layoutId = R.layout.fragment_edit_product

    private var photoUri: Uri? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val product = arguments?.getParcelable<ShortProduct>(ShortProduct::class.java.name)
        viewModel.categoryId = arguments?.getString(CATEGORY_ID_EXTRA)
        if (product != null) {
            viewModel.getProduct(product.id)
            toolbar?.title = product.name
            viewModel.isNewProduct.postValue(false)
        } else {
            toolbar?.title = getString(R.string.edit_product_new_product_title)
            viewModel.isNewProduct.postValue(true)
        }

        viewModel.listenFields(
            viewBinding.etName.textChanges(),
            viewBinding.etDescription.textChanges(),
            viewBinding.etPrice.textChanges(),
            viewBinding.etQuantity.textChanges()
        )

        val dialogItems = arrayOf(
            getString(R.string.edit_product_run_camera_text),
            getString(R.string.edit_product_gallery_text)
        )
        val dialog = AlertDialog.Builder(activity!!)
            .setTitle(R.string.edit_product_photo_dialog_title)
            .setItems(dialogItems) { _, which ->
                when (which) {
                    0 -> dispatchTakePhotoIntent()
                    1 -> loadPictureFromGallery()
                }
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

        viewBinding.ivPicture.setOnClickListener {
            activity?.askPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ) {
                onGranted {
                    dialog.show()
                }
                onShowRationale {
                    it.retry()
                }
            }
        }
    }

    private fun loadPictureFromGallery() {
        with(Intent(Intent.ACTION_PICK)) {
            type = "image/*"
            startActivityForResult(this, SELECT_PICTURE)
        }
    }

    private fun dispatchTakePhotoIntent() {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        photoUri =
            activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        with(Intent(MediaStore.ACTION_IMAGE_CAPTURE)) {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(this, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SELECT_PICTURE -> {
                    data?.data?.let { uploadPhoto(it) }
                }
                REQUEST_IMAGE_CAPTURE -> {
                    photoUri?.let { uploadPhoto(it) }
                }
            }
        }
    }

    private fun uploadPhoto(uri: Uri) {
        activity?.getFileRealPath(uri)?.let {
            viewModel.uploadAvatar(it)
        }
    }
}