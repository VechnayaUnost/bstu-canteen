package by.darya_zdzitavetskaya.bstu_canteen.navigation

import androidx.core.os.bundleOf
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code.ConfirmationCodeFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password.PasswordFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration.RegistrationFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

const val USER_ID_EXTRA = "userId"
const val EMAIL_EXTRA = "email"

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
}