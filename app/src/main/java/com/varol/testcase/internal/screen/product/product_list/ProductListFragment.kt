package com.varol.testcase.internal.screen.product.product_list

import androidx.navigation.fragment.findNavController
import com.varol.testcase.R
import com.varol.testcase.base.BaseFragment
import com.varol.testcase.databinding.FragmentProductListBinding
import com.varol.testcase.internal.screen.product.ProductUiModel
import com.varol.testcase.internal.screen.product.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductListDirections {
    fun toDetailPage(product: ProductUiModel) = ProductListFragmentDirections
        .actionProductListFragmentToProductDetailFragment(product)
}

class ProductListFragment :
    BaseFragment<ProductsViewModel, FragmentProductListBinding>(ProductsViewModel::class) {
    override val getLayoutId: Int
        get() = R.layout.fragment_product_list

    override val viewModel: ProductsViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_graph_main)
    })


}