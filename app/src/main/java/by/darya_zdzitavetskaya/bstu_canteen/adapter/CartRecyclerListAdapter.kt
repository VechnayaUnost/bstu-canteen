package by.darya_zdzitavetskaya.bstu_canteen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.darya_zdzitavetskaya.bstu_canteen.BR
import by.darya_zdzitavetskaya.bstu_canteen.shared.CartItem

class CartRecyclerListAdapter(
    @LayoutRes private val itemContainer: Int,
    private val onClickListener: CartItemClickListener
) : ListAdapter<CartItem, CartViewHolder>(diffUtil<CartItem>()) {

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemContainer, parent, false)

        return CartViewHolder(binding, onClickListener)
    }
}

class CartViewHolder(
    private val binding: ViewDataBinding,
    private val onClickListener: CartItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: CartItem) {
        binding.setVariable(BR.item, model)
        binding.setVariable(BR.clickListener, onClickListener)
        binding.executePendingBindings()
    }
}

interface CartItemClickListener {
    fun onDeleteClicked(cartItem: CartItem)
}