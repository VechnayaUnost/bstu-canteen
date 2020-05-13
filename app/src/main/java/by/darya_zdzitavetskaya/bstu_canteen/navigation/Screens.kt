package by.darya_zdzitavetskaya.bstu_canteen.navigation

import androidx.core.os.bundleOf
import by.darya_zdzitavetskaya.bstu_canteen.api.response.Category
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code.ConfirmationCodeFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password.PasswordFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration.RegistrationFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.MainFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category.CategoryFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product.DetailProductFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product.EditProductFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

const val USER_ID_EXTRA = "userId"
const val EMAIL_EXTRA = "email"
const val CATEGORY_ID_EXTRA = "categoryId"

class Screens {

    class LoginScreen : SupportAppScreen() {
        override fun getFragment() = LoginFragment()
    }

    class RegistrationScreen : SupportAppScreen() {
        override fun getFragment() = RegistrationFragment()
    }

    class ConfirmationCodeScreen(private val userId: String, private val email: String) :
        SupportAppScreen() {
        override fun getFragment() = ConfirmationCodeFragment().apply {
            arguments = bundleOf(USER_ID_EXTRA to userId, EMAIL_EXTRA to email)
        }
    }

    class PasswordScreen : SupportAppScreen() {
        override fun getFragment() = PasswordFragment()
    }

    class MainScreen : SupportAppScreen() {
        override fun getFragment() = MainFragment()
    }

    class CategoryScreen(private val category: Category) : SupportAppScreen() {
        override fun getFragment() = CategoryFragment().apply {
            arguments = bundleOf(Category::class.java.name to category)
        }
    }

    class EditProductScreen(
        private val product: ShortProduct? = null,
        private val categoryId: String
    ) : SupportAppScreen() {
        override fun getFragment() = EditProductFragment().apply {
            arguments = bundleOf(
                ShortProduct::class.java.name to product,
                CATEGORY_ID_EXTRA to categoryId
            )
        }
    }

    class DetailProductScreen(
        private val product: ShortProduct
    ) : SupportAppScreen() {
        override fun getFragment() = DetailProductFragment().apply {
            arguments = bundleOf(ShortProduct::class.java.name to product)
        }
    }
}