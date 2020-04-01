package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import androidx.lifecycle.ViewModel
import by.darya_zdzitavetskaya.bstu_canteen.di.ViewModelKey
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}