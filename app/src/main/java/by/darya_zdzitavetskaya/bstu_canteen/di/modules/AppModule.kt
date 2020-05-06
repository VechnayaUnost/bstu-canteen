package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import by.darya_zdzitavetskaya.bstu_canteen.MainActivity
import by.darya_zdzitavetskaya.bstu_canteen.di.ActivityScope
import by.darya_zdzitavetskaya.bstu_canteen.di.FragmentScope
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code.ConfirmationCodeFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password.PasswordFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration.RegistrationFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.MainFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileFragment
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

        @FragmentScope
        @ContributesAndroidInjector(modules = [AuthModule::class])
        abstract fun registrationFragment(): RegistrationFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [AuthModule::class])
        abstract fun confirmationCodeFragment(): ConfirmationCodeFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [AuthModule::class])
        abstract fun passwordFragment(): PasswordFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [AuthModule::class])
        abstract fun mainFragment(): MainFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun profileFragment(): ProfileFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun menuFragment(): MenuFragment
    }
}