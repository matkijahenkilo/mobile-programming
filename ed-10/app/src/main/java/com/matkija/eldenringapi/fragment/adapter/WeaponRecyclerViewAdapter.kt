package com.matkija.eldenringapi.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matkija.eldenringapi.R
import com.matkija.eldenringapi.databinding.FragmentItemBinding
import com.matkija.eldenringapi.listener.CardListener
import com.matkija.eldenringapi.model.Weapon

class WeaponRecyclerViewAdapter (
    private val weapons: MutableList<Weapon>,
    private val cardListener: CardListener
) : RecyclerView.Adapter<WeaponRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setValues(weapons[position], holder, position)
        setInteraction(weapons[position], holder)
    }

    private fun setInteraction(item: Weapon, holder: ViewHolder) {

        holder.binding.itemCardView.setOnClickListener {
            cardListener.onCardClick(item)
        }

        holder.binding.ivShare.setOnClickListener {
            cardListener.onShareClick(item.name, item.description, item.image)
        }
    }

    private fun setValues(item: Weapon, holder: ViewHolder, position: Int) {
        var totalAtk = 0

        for (type in item.attack) {
            for (amount in type.amount) {
                totalAtk += amount.code
            }
        }

        holder.binding.tvId.text = String.format("%d", position+1)
        holder.binding.tvItemName.text = item.name
        holder.binding.tvItemValue.text = String.format("Total atk: %s", totalAtk.toString())
        holder.binding.tvItemWeight.text = String.format("Weight: %s", item.weight)

        Glide.with(holder.itemView)
            .load(item.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.tiItemImage)
    }

    override fun getItemCount(): Int = weapons.size

    data class ViewHolder(var binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}