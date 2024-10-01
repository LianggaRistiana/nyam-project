package com.example.nyam_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nyam_project.databinding.ItemRowFoodBinding

class ListFoodAdapter(private val listFood: ArrayList<Food>, private val onItemClick: (Food) -> Unit): RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    inner class ListViewHolder(var binding: ItemRowFoodBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food){
            binding.tvTitle.text = food.title
            binding.tvdesc.text = food.desc
            binding.imgItem.setImageResource(food.photo)

            itemView.setOnClickListener {
                onItemClick(food)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (title, desc, photo) = listFood[position]
        holder.bind(listFood[position])
    }
}