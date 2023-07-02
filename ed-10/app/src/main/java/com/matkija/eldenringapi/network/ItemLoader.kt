package com.matkija.eldenringapi.network

import android.content.Context
import android.view.View
import android.widget.Toast
import com.matkija.eldenringapi.databinding.FragmentItemListBinding
import com.matkija.eldenringapi.model.Armor
import com.matkija.eldenringapi.model.Response
import com.matkija.eldenringapi.model.Weapon
import retrofit2.Call
import retrofit2.Callback
import kotlin.reflect.KClass

class ItemLoader(
    private val context: Context?,
    private val limit: Int?,
    private val items: MutableList<Any>?,
    private val binding: FragmentItemListBinding
) {

    fun <T: Any> loadItemList(kClass: KClass<T>) {
        if (limit == null) {
            return
        }
        //estava com a ideia de passar kClass::class como parametro
        //no lugar do Armor, Weapon, etc para evitar repeticoes.
        //mas pelo jeito, eh melhor fazer de forma repetitiva pois eu nao sei como passar por isso
        //nao estou satisfeito com isso though
        when (kClass.simpleName) {
            "Armor"  -> {
                val call = NetworkManager.service.listArmors(limit)
                call.enqueue(object : Callback<Response<Armor>> {
                    override fun onResponse(
                        call: Call<Response<Armor>>,
                        response: retrofit2.Response<Response<Armor>>
                    ) {
                        onArmorResponseSuccess(response.body())
                    }

                    override fun onFailure(call: Call<Response<Armor>>, t: Throwable) {
                        if (context != null) {
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }

            "Weapon" -> {
                val call = NetworkManager.service.listWeapons(limit)
                call.enqueue(object : Callback<Response<Weapon>> {
                    override fun onResponse(
                        call: Call<Response<Weapon>>,
                        response: retrofit2.Response<Response<Weapon>>
                    ) {
                        onWeaponResponseSuccess(response.body())
                    }

                    override fun onFailure(call: Call<Response<Weapon>>, t: Throwable) {
                        if (context != null) {
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
    }

    private fun onWeaponResponseSuccess(body: Response<Weapon>?) {

        if (body == null) {
            return
        }

        if (items == null) {
            return
        }

        if (body.success) {
            val ini = items.size
            val qtd = items.size + body.data.size

            items.addAll(body.data)

            binding.recyclerList.adapter?.notifyItemRangeInserted(ini, qtd)

            binding.recyclerProgressBar.visibility = View.GONE
            binding.recyclerList.visibility = View.VISIBLE
        }
    }

    private fun onArmorResponseSuccess(body: Response<Armor>?) {
        if (body == null) {
            return
        }

        if (items == null) {
            return
        }
        if (body.success) {
            val ini = items.size
            val qtd = items.size + body.data.size

            items.addAll(body.data)

            binding.recyclerList.adapter?.notifyItemRangeInserted(ini, qtd)

            binding.recyclerProgressBar.visibility = View.GONE
            binding.recyclerList.visibility = View.VISIBLE
        }
    }
}