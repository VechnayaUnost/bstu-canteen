package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import by.darya_zdzitavetskaya.bstu_canteen.MainActivity
import by.darya_zdzitavetskaya.bstu_canteen.di.ActivityScope
import by.darya_zdzitavetskaya.bstu_canteen.di.FragmentScope
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.confirmation_code.ConfirmationCodeFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.login.LoginFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.password.PasswordFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.auth.registration.RegistrationFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.MainFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart.CartFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category.CategoryFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product.DetailProductFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product.EditProductFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.OrdersFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay.PayFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ScannerFragment
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.detail_order.DetailOrderFragment
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
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun mainFragment(): MainFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun profileFragment(): ProfileFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun menuFragment(): MenuFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun categoryFragment(): CategoryFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun editProductFragment(): EditProductFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun detailProductFragment(): DetailProductFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun cartFragment(): CartFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun detailOrderFragment(): DetailOrderFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun ordersFragment(): OrdersFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun scannerFragment(): ScannerFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [MainModule::class])
        abstract fun payFragment(): PayFragment
    }
}