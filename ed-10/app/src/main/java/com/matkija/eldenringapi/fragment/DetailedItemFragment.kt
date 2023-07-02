package com.matkija.eldenringapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.matkija.eldenringapi.R
import com.matkija.eldenringapi.databinding.FragmentDetailedItemBinding
import com.matkija.eldenringapi.model.Armor
import com.matkija.eldenringapi.model.Attribute
import com.matkija.eldenringapi.model.Weapon
import kotlin.reflect.KClass

class DetailedItemFragment<T>(
    private val item: T
) : Fragment() {
    private lateinit var mBinding: FragmentDetailedItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailedItemBinding.inflate(inflater, container, false)

        setContents(item!!::class)

        return mBinding.root
    }

    private fun <T: Any> setContents(clazz: KClass<T>) {
        when(clazz) {
            Armor::class  -> {
                val item = item as Armor
                val attributes = getAttributes(item.dmgNegation)

                mBinding.tvDetailedItemName.text = item.name
                mBinding.tvDetailedItemDescription.text = item.description
                mBinding.tvDetailedItemAttributeName.text = "Damage Negation:"
                mBinding.tvDetailedItemAttributeList.text = attributes
                setImage(item.image)
            }

            Weapon::class -> {
                val item = item as Weapon
                val attributes = getAttributes(item.attack)
                val requirements = getAttributes(item.requiredAttributes)
                val scaleWith = getAttributes(item.scalesWith)

                mBinding.tvDetailedItemName.text = item.name
                mBinding.tvDetailedItemDescription.text = item.description
                mBinding.tvDetailedItemAttributeName.text = "Damage :"
                mBinding.tvDetailedItemAttributeList.text = attributes
                mBinding.tvDetailedItemRequirementsName.text = "Requirements :"
                mBinding.tvDetailedItemRequirementsList.text = requirements
                mBinding.tvDetailedItemScaleName.text = "Scales with:"
                mBinding.tvDetailedItemScale.text = scaleWith
                setImage(item.image)
            }
        }
    }

    private fun getAttributes(attributes: List<Attribute>): String {
        var list = ""
        attributes.forEach { attribute ->
            list += String.format("%s: %s\n", attribute.name, attribute.amount)
        }
        return list
    }

    private fun setImage(image: String) {
        Glide.with(mBinding.root)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(mBinding.ivDetailedItemImage)
    }

}