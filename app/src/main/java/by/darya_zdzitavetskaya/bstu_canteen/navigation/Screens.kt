package by.darya_zdzitavetskaya.bstu_canteen.navigation

import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class LoginScreen : SupportAppScreen() {
        override fun getFragment() = LoginFragment()
    }
}