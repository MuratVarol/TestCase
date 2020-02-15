package com.varol.testcase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.varol.testcase.BR
import com.varol.testcase.R
import com.varol.testcase.internal.extension.EMPTY
import com.varol.testcase.internal.extension.observeNonNull
import com.varol.testcase.internal.navigation.NavigationCommand
import com.varol.testcase.internal.util.Failure
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


abstract class BaseFragment<out VM : BaseAndroidViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) :
    Fragment() {

    //no need for ViewModelProviders
    open val viewModel: VM by viewModelByClass(viewModelClass)

    lateinit var binding: DB

    abstract val getLayoutId: Int

    open fun initialize() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        initialize()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeNavigation()
        observeFailure()
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        })
    }

    protected open fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> {
                with(command) {
                    directions.let {
                        findNavController().navigate(it, getExtras())
                    }
                }
            }
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    private fun observeFailure() {
        viewModel.failure.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { failure -> handleFailure(failure) }
        }
    }

    protected fun handleFailure(failure: Failure) {
        val (title, message) = when (failure) {
            is Failure.NoConnectivityError ->
                Pair(String.EMPTY, getString(R.string.error_message_network_connection))
            is Failure.UnknownHostError ->
                Pair(String.EMPTY, getString(R.string.error_message_unknown_host))
            is Failure.ServerError ->
                Pair(String.EMPTY, failure.message)
            is Failure.ParsingDataError,
            is Failure.EmptyResponse ->
                Pair(String.EMPTY, getString(R.string.error_message_invalid_response))
            is Failure.UnknownError ->
                Pair(String.EMPTY, failure.message ?: getString(R.string.error_unknown))
            is Failure.HttpError ->
                Pair(String.EMPTY, getString(R.string.error_message_http, failure.code.toString()))
            is Failure.TimeOutError ->
                Pair(String.EMPTY, getString(R.string.error_message_timeout))
            else ->
                Pair(String.EMPTY, failure.message ?: failure.toString())
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

}