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
import by.darya_zdzitavetskaya.bstu_canteen.presentation.main.profile.ProfileViewModel
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
    abstract fun bindMenuRep(rep: MenuRepository): MenuProtocol.IMenuRepository

    @Binds
    abstract fun bindCategoryRep(rep: CategoryRepository): CategoryProtocol.ICategoryRepository

    @Binds
    abstract fun bindEditProductRep(rep: EditProductRepository): EditProductProtocol.IEditProductRepository

    @Binds
    abstract fun bindDetailProductRep(rep: DetailProductRepository): DetailProductProtocol.IDetailProductRepository

    @Binds
    abstract fun bindCartRep(rep: CartRepository): CartProtocol.ICartRepository
}