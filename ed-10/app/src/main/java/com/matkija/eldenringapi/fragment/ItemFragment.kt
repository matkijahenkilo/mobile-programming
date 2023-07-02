package com.matkija.eldenringapi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matkija.eldenringapi.databinding.FragmentItemListBinding
import com.matkija.eldenringapi.fragment.adapter.ArmorRecyclerViewAdapter
import com.matkija.eldenringapi.fragment.adapter.WeaponRecyclerViewAdapter
import com.matkija.eldenringapi.listener.CardListener
import com.matkija.eldenringapi.model.Armor
import com.matkija.eldenringapi.model.Weapon
import com.matkija.eldenringapi.network.ItemLoader
import com.matkija.eldenringapi.network.NetworkManager
import com.matkija.eldenringapi.utils.Type

class ItemFragment(private val cardListener: CardListener) : Fragment() {

    private lateinit var mBinding: FragmentItemListBinding
    private lateinit var mItemLoader: ItemLoader

    private val mItems: MutableList<Any> = mutableListOf()

    // A API nao tem "sistema" de paginas como no rick and morty,
    // apenas limite de items para mostrar em cada requisicao http.
    // Nao imagino que tenha um jeito de tipo, chamar os 20 primeiros items da lista do json e
    // depois carregar os proximos 20, excluindo os 20 anteriores e repetindo.
    private var mLimit = 90

    private var mColumnCount = 1
    private var mItemType: String? = Type.ARMOR.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mColumnCount = it.getInt(ARG_COLUMN_COUNT)
            mItemType = it.getString(ARG_ITEM_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentItemListBinding.inflate(inflater, container, false)
        mItemLoader = ItemLoader(this.context, mLimit, mItems, mBinding)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemType = getItemType()

        mBinding.recyclerList.layoutManager = LinearLayoutManager(activity)

        mBinding.recyclerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    mItemLoader.loadItemList(itemType)
                }
            }
        })

        mItemLoader.loadItemList(itemType)
    }

    override fun onStop() {
        NetworkManager.stop()
        super.onStop()
    }

    private fun getItemType() = when (mItemType) {
        Type.ARMOR.name -> {
            mBinding.recyclerList.adapter = ArmorRecyclerViewAdapter(mItems as MutableList<Armor>, cardListener)
            Armor::class
        }

        Type.WEAPON.name -> {
            mBinding.recyclerList.adapter = WeaponRecyclerViewAdapter(mItems as MutableList<Weapon>, cardListener)
            Weapon::class
        }

        else -> {
            mBinding.recyclerList.adapter = ArmorRecyclerViewAdapter(mItems as MutableList<Armor>, cardListener)
            Armor::class
        }
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_ITEM_TYPE = "item-type"

        @JvmStatic
        fun newInstance(cardListener: CardListener, columnCount: Int, type: Type) =
            ItemFragment(cardListener).apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putString(ARG_ITEM_TYPE, type.name)
                }
            }
    }
}