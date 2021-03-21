package com.example.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.fragment.DashboardFragment
import com.example.assignment.fragment.ProfileFragment
import com.example.assignment.fragment.SettingsFragment
import com.example.assignment.util.CommonToolBarView
import com.example.assignment.util.CommonViewPagerAdapter
import com.example.assignment.util.ViewPagerItem
import com.example.assignment.util.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        CommonToolBarView().setup(this, "Goal")

        val fragmentList = arrayListOf<ViewPagerItem>()
        fragmentList.add(
            ViewPagerItem(
                DashboardFragment(),
                ""
            )
        )
        fragmentList.add(
            ViewPagerItem(
                SettingsFragment(),
                ""
            )
        )
        fragmentList.add(
            ViewPagerItem(
                ProfileFragment(),
                ""
            )
        )

        val viewPagerAdapter =
            CommonViewPagerAdapter(
                supportFragmentManager,
                fragmentList
            )
        viewBinding.viewPager.adapter = viewPagerAdapter

        setClickListeners()
    }

    private fun setClickListeners() {
        dashboardLayout.setSafeOnClickListener {
            viewBinding.viewPager.currentItem = 0
        }

        homeSettingImageView.setSafeOnClickListener {
            viewBinding.viewPager.currentItem = 1
        }

        profileLayout.setSafeOnClickListener {
            viewBinding.viewPager.currentItem = 2
        }

        chatFab.setSafeOnClickListener {
            Toast.makeText(this, "Clicked on Chat", Toast.LENGTH_LONG).show()
        }
    }
}