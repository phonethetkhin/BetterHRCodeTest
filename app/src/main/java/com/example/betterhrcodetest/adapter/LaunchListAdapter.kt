package com.example.betterhrcodetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.betterhrcodetest.LaunchListQuery
import com.example.betterhrcodetest.R
import com.example.betterhrcodetest.databinding.ListItemLaunchBinding
import com.example.betterhrcodetest.ui.fragment.LaunchListFragment
import com.example.betterhrcodetest.util.changeDateFormat


class LaunchListAdapter :
    ListAdapter<LaunchListQuery.Launch, LaunchListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<LaunchListQuery.Launch>() {
            override fun areItemsTheSame(
                oldItem: LaunchListQuery.Launch,
                newItem: LaunchListQuery.Launch
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LaunchListQuery.Launch,
                newItem: LaunchListQuery.Launch
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(val binding: ListItemLaunchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    var onEndOfListReached: (() -> Unit)? = null
    var onItemClicked: ((LaunchListQuery.Launch) -> Unit)? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = getItem(position)
        holder.binding.txtMissionName.text = launch.mission_name
        holder.binding.txtDate.text = changeDateFormat(launch.launch_date_utc.toString())
        holder.binding.imgMissionPatch.load(launch.links?.mission_patch_small) {
            placeholder(R.drawable.ic_placeholder)
        }

        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(launch)
        }
    }

}
