package com.varol.testcase.internal.screen.product.product_detail

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.varol.testcase.R
import com.varol.testcase.base.BaseFragment
import com.varol.testcase.databinding.FragmentProductDetailBinding
import com.varol.testcase.internal.extension.notifyDataChange
import com.varol.testcase.internal.screen.product.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailFragment :
    BaseFragment<ProductsViewModel, FragmentProductDetailBinding>(ProductsViewModel::class) {
    override val getLayoutId: Int
        get() = R.layout.fragment_product_detail

    private val args: ProductDetailFragmentArgs by navArgs()

    override val viewModel: ProductsViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_graph_main)
    })

    override fun initialize() {
        args.selectedProduct.run {
            binding.model = this
            setFavoriteText(isFavorite)
        }

        binding.btnFavorite.setOnClickListener {
            viewModel.selectedProducts.value?.find { product ->
                product.id == args.selectedProduct.id
            }
                ?.apply {
                    binding.model?.let { model ->
                        this.let {
                            model.isFavorite = it.isFavorite.not()
                            setFavoriteText(model.isFavorite)
                            viewModel.selectedProducts.notifyDataChange()
                        }
                    }
                }
        }
    }

    private fun setFavoriteText(isFavorite: Boolean) {
        context?.let { ctx ->
            if (!isFavorite) {
                binding.btnFavorite.text = ctx.getString(R.string.favorite)
                binding.tvName.setTextColor(ContextCompat.getColor(ctx, R.color.dark_grey))
            } else {
                binding.btnFavorite.text = ctx.getString(R.string.un_favorite)
                binding.tvName.setTextColor(ContextCompat.getColor(ctx, R.color.blue))
            }
        }

    }


}