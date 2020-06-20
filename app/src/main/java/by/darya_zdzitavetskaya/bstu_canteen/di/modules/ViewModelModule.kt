package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import androidx.lifecycle.ViewModelProvider
import by.darya_zdzitavetskaya.bstu_canteen.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}