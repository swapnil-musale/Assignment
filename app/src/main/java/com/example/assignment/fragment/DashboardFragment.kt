package com.example.assignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.assignment.R
import com.example.assignment.TabItem
import com.example.assignment.adapter.TabRecyclerViewAdapter
import com.example.assignment.databinding.FragmentDashboardBinding
import com.example.assignment.util.gone
import com.example.assignment.util.setSafeOnClickListener
import com.example.assignment.util.show
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var tabRecyclerViewAdapter: TabRecyclerViewAdapter
    private lateinit var viewBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.viewPager.adapter = GoalInfoViewPagerAdapter(childFragmentManager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> viewBinding.goBackLayout.gone()
                    3 -> viewBinding.goNextLayout.gone()
                    else -> {
                        viewBinding.goBackLayout.show()
                        viewBinding.goNextLayout.show()
                    }
                }

                tabRecyclerViewAdapter.selectedPosition = position
                viewBinding.tabRecyclerView.scrollToPosition(position)
                tabRecyclerViewAdapter.notifyDataSetChanged()
            }
        })

        setTabRecyclerView()
        setClickListeners()
    }

    private fun setTabRecyclerView() {
        val tabList = arrayListOf<TabItem>()
        tabList.add(
            TabItem(
                "Your \nInfo",
                R.drawable.ic_baseline_settings_24
            )
        )
        tabList.add(
            TabItem(
                "Know you \nBetter",
                R.drawable.ic_baseline_settings_24
            )
        )
        tabList.add(
            TabItem(
                "Know your \nRisk",
                R.drawable.ic_baseline_settings_24
            )
        )
        tabList.add(
            TabItem(
                "Your \nFamily",
                R.drawable.ic_baseline_settings_24
            )
        )
        tabRecyclerViewAdapter =
            TabRecyclerViewAdapter(this, tabList)
        viewBinding.tabRecyclerView.adapter = tabRecyclerViewAdapter
    }

    private fun setClickListeners() {
        goNextLayout.setSafeOnClickListener {
            scrollViewPager(viewBinding.viewPager.currentItem.plus(1))
        }

        goBackLayout.setSafeOnClickListener {
            scrollViewPager(viewBinding.viewPager.currentItem.minus(1))
        }
    }

    private fun scrollViewPager(position: Int){
        when (position) {
            0 -> viewBinding.goBackLayout.gone()
            3 -> viewBinding.goNextLayout.gone()
            else -> {
                viewBinding.goBackLayout.show()
                viewBinding.goNextLayout.show()
            }
        }

        viewBinding.viewPager.currentItem = position
        tabRecyclerViewAdapter.selectedPosition = position
        viewBinding.tabRecyclerView.scrollToPosition(position)
        tabRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun onTapOptionSelected(position: Int) {
        viewBinding.viewPager.currentItem = position
    }

    inner class GoalInfoViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            val fragment = GoalInfoFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            fragment.arguments = bundle
            return fragment
        }

        override fun getCount() = 4
    }
}