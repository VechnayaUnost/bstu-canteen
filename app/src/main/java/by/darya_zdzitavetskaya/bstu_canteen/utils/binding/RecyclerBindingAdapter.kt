package by.darya_zdzitavetskaya.bstu_canteen.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["app:enableItemDividers"], requireAll = true)
fun RecyclerView.setDividers(isEnableItemDivider: Boolean) {
    if (isEnableItemDivider) addItemDecoration(
        DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )
    )
}