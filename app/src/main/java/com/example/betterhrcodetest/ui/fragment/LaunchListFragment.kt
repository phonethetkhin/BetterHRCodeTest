package com.example.betterhrcodetest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.betterhrcodetest.R
import com.example.betterhrcodetest.adapter.LaunchListPagingAdapter
import com.example.betterhrcodetest.adapter.LoadingAdapter
import com.example.betterhrcodetest.application.App
import com.example.betterhrcodetest.dagger.ViewModelFactory
import com.example.betterhrcodetest.databinding.LaunchListFragmentBinding
import com.example.betterhrcodetest.util.changeDateFormat
import com.example.betterhrcodetest.viewmodel.HomeViewModel
import fragmentViewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class LaunchListFragment : Fragment(R.layout.launch_list_fragment) {
    private val binding by fragmentViewBinding(LaunchListFragmentBinding::bind)
    private lateinit var adapter: LaunchListPagingAdapter

    @Inject
    lateinit var listViewModelFactory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, listViewModelFactory)[HomeViewModel::class.java]
        adapter = LaunchListPagingAdapter()
        binding.rcvLaunches.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvLaunches.adapter =
            adapter.withLoadStateFooter(footer = LoadingAdapter { adapter.retry() })
        observeFeatured()

        adapter.onItemClicked = { launch ->
            val missionId: String? =
                if (launch.mission_id != null && launch.mission_id.isNotEmpty()) {
                    launch.mission_id[0]
                } else {
                    null
                }

            findNavController().navigate(
                LaunchListFragmentDirections.openLaunchDetails(
                    missionId,
                    launch.mission_name!!,
                    changeDateFormat(launch.launch_date_utc.toString()),
                    launch.links?.mission_patch!!
                )
            )
        }
    }

    private fun observeFeatured() {
        lifecycleScope.launch {
            viewModel.launchListLiveData.collectLatest {
                adapter.submitData(it)
            }
        }

    }

}
