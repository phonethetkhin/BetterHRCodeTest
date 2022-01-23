package com.example.betterhrcodetest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.betterhrcodetest.R
import com.example.betterhrcodetest.application.App
import com.example.betterhrcodetest.dagger.ViewModelFactory
import com.example.betterhrcodetest.databinding.LaunchDetailsFragmentBinding
import com.example.betterhrcodetest.viewmodel.HomeViewModel
import fragmentViewBinding
import javax.inject.Inject

class LaunchDetailsFragment : Fragment(R.layout.launch_details_fragment) {
    private val binding by fragmentViewBinding(LaunchDetailsFragmentBinding::bind)
    private val args: LaunchDetailsFragmentArgs by navArgs()

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

        lifecycleScope.launchWhenResumed {
            binding.pgbLoading.visibility = View.VISIBLE
            binding.txtError.visibility = View.GONE


            if (args.missionId != null) {
                val detail = viewModel.getDetail(args.missionId!!)
                if (detail != null) {
                    val launch = detail.data!!.mission
                    if (launch == null || detail.hasErrors()) {
                        binding.pgbLoading.visibility = View.GONE
                        binding.txtError.text = detail.errors?.get(0)?.message
                        binding.txtError.visibility = View.VISIBLE
                        return@launchWhenResumed
                    }
                    binding.pgbLoading.visibility = View.GONE
                    binding.txtTwitter.text = launch.twitter
                    binding.txtWiki.text = launch.wikipedia

                    binding.imgMissionPatch.load(args.missionPatch) {
                        placeholder(R.drawable.ic_placeholder)
                    }
                    binding.txtMissionName.text = args.missionName
                    binding.txtDate.text = args.launchDate
                }
            } else {
                binding.pgbLoading.visibility = View.GONE
                binding.imgMissionPatch.load(args.missionPatch) {
                    placeholder(R.drawable.ic_placeholder)
                }
                binding.txtMissionName.text = args.missionName
                binding.txtDate.text = args.launchDate
                binding.txtTwitter.text = getString(R.string.no_mission_links)
                binding.txtWiki.text = getString(R.string.no_mission_links)
            }
        }
    }
}
