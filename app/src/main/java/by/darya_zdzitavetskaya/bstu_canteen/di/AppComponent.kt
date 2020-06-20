package by.darya_zdzitavetskaya.bstu_canteen.di

import by.darya_zdzitavetskaya.bstu_canteen.App
import by.darya_zdzitavetskaya.bstu_canteen.di.modules.AppModule
import by.darya_zdzitavetskaya.bstu_canteen.di.modules.GlobalModule
import by.darya_zdzitavetskaya.bstu_canteen.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, GlobalModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun globalModule(context: GlobalModule): Builder
    }
}