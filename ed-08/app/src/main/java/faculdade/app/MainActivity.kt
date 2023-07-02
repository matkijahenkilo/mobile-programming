package faculdade.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import faculdade.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mOrderFragment: OrderFragment
    private lateinit var mNutritionFragment: NutritionFragment
    private lateinit var mConsultationFragment: ConsultationFragment
    private lateinit var mProfileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mOrderFragment = OrderFragment.newInstance()
        mNutritionFragment = NutritionFragment.newInstance()
        mConsultationFragment = ConsultationFragment.newInstance()
        mProfileFragment = ProfileFragment.newInstance()

        replaceFragment(mOrderFragment)
        mBinding.bottomNav.setOnItemSelectedListener(::onSelectedBottomNavigationItem)
    }

    private fun onSelectedBottomNavigationItem(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_order -> replaceFragment(mOrderFragment)
            R.id.item_nutri -> replaceFragment(mNutritionFragment)
            R.id.item_consultation -> replaceFragment(mConsultationFragment)
            R.id.item_profile -> replaceFragment(mProfileFragment)
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}