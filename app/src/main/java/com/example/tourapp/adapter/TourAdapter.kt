package com.example.tourapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tourapp.databinding.TourCardNewBinding
import com.example.tourapp.model.TourModel

class TourAdapter(val actionCallback: (TourModel,String) -> Unit) :
    ListAdapter<TourModel, TourAdapter.TourViewHolder>(TourDiffUtilCallback()) {

    // Inflate the layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val binding = TourCardNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourViewHolder(binding, actionCallback)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Define the ViewHolder that extends RecyclerView.ViewHolder
    class TourViewHolder(private val binding: TourCardNewBinding, val actionCallback: (TourModel,String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tourModel: TourModel?) {
            // Bind your data here, e.g.,
            binding.tourTitle.text = tourModel?.title
            binding.isCompleted.isSelected = tourModel?.isCompleted ?: false
            binding.tourRowItemCard.setOnClickListener {
                if (tourModel != null) {
                    println("id" + "clicked")
                    tourModel.id?.let { it1 -> actionCallback(tourModel, it1) }
                }
            }
        }

    }
}



class TourDiffUtilCallback : DiffUtil.ItemCallback<TourModel>() {
    override fun areItemsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
        return oldItem == newItem
    }
}
