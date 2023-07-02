package com.matkija.eldenringapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.matkija.eldenringapi.listener.CardListener
import com.matkija.eldenringapi.fragment.ItemFragment
import com.matkija.eldenringapi.R
import com.matkija.eldenringapi.utils.Type
import com.matkija.eldenringapi.databinding.ActivityMainBinding
import com.matkija.eldenringapi.fragment.DetailedItemFragment
import com.matkija.eldenringapi.utils.MIME_TEXT

class MainActivity : AppCompatActivity(), CardListener {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        changeFragment(ItemFragment.newInstance(this, 1, Type.ARMOR))

        mBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.armor -> changeFragment(ItemFragment.newInstance(this, 1, Type.ARMOR))
                R.id.weapon -> changeFragment(ItemFragment.newInstance(this, 1, Type.WEAPON))
            }
            true
        }
    }

    override fun onCardClick(item: Any) {
        changeFragment(DetailedItemFragment(item))
    }

    override fun onShareClick(name: String, description: String, image: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = MIME_TEXT
            putExtra(Intent.EXTRA_TEXT, "$name\n\n$description\n\n$image")
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        startActivity(shareIntent)
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.replace(mBinding.fragmentContainerView.id, fragment)
        fragmentTransaction.commit()
    }
}