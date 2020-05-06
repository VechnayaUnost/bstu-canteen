package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import androidx.lifecycle.ViewModel
import by.darya_zdzitavetskaya.bstu_canteen.di.ViewModelKey
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.AuthRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code.ConfirmationCodeViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password.PasswordViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationCodeViewModel::class)
    abstract fun bindConfirmationCodeViewModel(viewModel: ConfirmationCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PasswordViewModel::class)
    abstract fun bindPasswordViewModel(viewModel: PasswordViewModel): ViewModel



    @Binds
    abstract fun bindAuthRep(rep: AuthRepository): AuthProtocol.IAuthRepository
}