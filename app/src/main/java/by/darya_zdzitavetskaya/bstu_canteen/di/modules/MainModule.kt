package by.darya_zdzitavetskaya.bstu_canteen.di.modules

import androidx.lifecycle.ViewModel
import by.darya_zdzitavetskaya.bstu_canteen.di.ViewModelKey
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart.CartProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart.CartRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.cart.CartViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.MenuViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category.CategoryProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category.CategoryRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.category.CategoryViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product.DetailProductProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product.DetailProductRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.detail_product.DetailProductViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product.EditProductProtocol
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product.EditProductRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.menu.edit_product.EditProductViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.OrdersRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.OrdersRepositoryImpl
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.OrdersViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay.PayRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay.PayRepositoryImpl
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.pay.PayViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileRepositoryImpl
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileViewModel
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order.DetailOrderRepository
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order.DetailOrderRepositoryImpl
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.orders.detail_order.DetailOrderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProductViewModel::class)
    abstract fun bindEditProductViewModel(viewModel: EditProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailProductViewModel::class)
    abstract fun bindDetailProductViewModel(viewModel: DetailProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailOrderViewModel::class)
    abstract fun bindDetailOrderViewModel(viewModel: DetailOrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun bindOrdersViewModel(viewModel: OrdersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel::class)
    abstract fun bindPayViewModel(viewModel: PayViewModel): ViewModel


    @Binds
    abstract fun bindMenuRep(rep: MenuRepository): MenuProtocol.IMenuRepository

    @Binds
    abstract fun bindCategoryRep(rep: CategoryRepository): CategoryProtocol.ICategoryRepository

    @Binds
    abstract fun bindEditProductRep(rep: EditProductRepository): EditProductProtocol.IEditProductRepository

    @Binds
    abstract fun bindDetailProductRep(rep: DetailProductRepository): DetailProductProtocol.IDetailProductRepository

    @Binds
    abstract fun bindCartRep(rep: CartRepository): CartProtocol.ICartRepository

    @Binds
    abstract fun bindPayRep(rep: PayRepositoryImpl): PayRepository

    @Binds
    abstract fun bindProfileRep(rep: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindOrdersRep(rep: OrdersRepositoryImpl): OrdersRepository

    @Binds
    abstract fun bindDetailOrderRep(rep: DetailOrderRepositoryImpl): DetailOrderRepository
}