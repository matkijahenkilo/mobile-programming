package com.matkija.eldenringapi.fragment.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.matkija.eldenringapi.R
import com.matkija.eldenringapi.databinding.FragmentItemBinding
import com.matkija.eldenringapi.listener.CardListener
import com.matkija.eldenringapi.model.Armor
import com.matkija.eldenringapi.utils.PHYSICAL

class ArmorRecyclerViewAdapter(
    private val armors: MutableList<Armor>,
    private val cardListener: CardListener
) : RecyclerView.Adapter<ArmorRecyclerViewAdapter.ViewHolder>() {

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
        setValues(armors[position], holder, position)
        setInteraction(armors[position], holder)
    }

    private fun setInteraction(item: Armor, holder: ViewHolder) {
        holder.binding.itemCardView.setOnClickListener {
            cardListener.onCardClick(item)
        }

        holder.binding.ivShare.setOnClickListener {
            cardListener.onShareClick(item.name, item.description, item.image)
        }
    }

    private fun setValues(item: Armor, holder: ViewHolder, position: Int) {
        holder.binding.tvId.text = String.format("%d", position+1)
        holder.binding.tvItemName.text = item.name
        holder.binding.tvItemValue.text = String.format("Physical negation: %s", item.dmgNegation[PHYSICAL].amount)
        holder.binding.tvItemWeight.text = String.format("Weight: %s", item.weight)

        Glide.with(holder.itemView)
            .load(item.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.tiItemImage)
    }

    override fun getItemCount(): Int = armors.size

    data class ViewHolder(var binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}