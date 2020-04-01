package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import by.darya_zdzitavetskaya.bstu_canteen.MainActivity
import by.darya_zdzitavetskaya.bstu_canteen.di.ActivityScope
import by.darya_zdzitavetskaya.bstu_canteen.di.FragmentScope
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @Module
    abstract class MainActivityModule {

        @FragmentScope
        @ContributesAndroidInjector(modules = [AuthModule::class])
        abstract fun loginFragment(): LoginFragment
    }
}