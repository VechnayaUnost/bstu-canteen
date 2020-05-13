package by.darya_zdzitavetskaya.bstu_canteen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.darya_zdzitavetskaya.bstu_canteen.BR
import by.darya_zdzitavetskaya.bstu_canteen.api.response.ShortProduct

class CategoryRecyclerListAdapter(
    @LayoutRes private val itemContainer: Int,
    private val onClickListener: ProductItemClickListener
) : ListAdapter<ShortProduct, CategoryViewHolder>(diffUtil<ShortProduct>()) {

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemContainer, parent, false)

        return CategoryViewHolder(binding, onClickListener)
    }
}

class CategoryViewHolder(
    private val binding: ViewDataBinding,
    private val onClickListener: ProductItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ShortProduct) {
        binding.setVariable(BR.item, model)
        binding.setVariable(BR.clickListener, onClickListener)
        binding.executePendingBindings()
    }
}

interface ProductItemClickListener {
    fun onProductItemClicked(product: ShortProduct)
    fun onEditClicked(product: ShortProduct)
    fun onDeleteClicked(product: ShortProduct)
}