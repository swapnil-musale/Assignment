package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.model.TabItem
import com.example.assignment.fragment.DashboardFragment
import kotlinx.android.synthetic.main.tab_recyclerview_item_layout.view.*

class TabRecyclerViewAdapter(
    private val fragment: DashboardFragment,
    private val tabList: ArrayList<TabItem>
) : RecyclerView.Adapter<TabRecyclerViewAdapter.ViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragment.requireContext())
            .inflate(R.layout.tab_recyclerview_item_layout, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = tabList[position]

        holder.itemView.displayTitle.text = data.displayTitle
        holder.itemView.displayImage.setImageResource(data.displayImage)

        if (selectedPosition == position) {
            holder.itemView.rootLayout.setBackgroundResource(R.drawable.all_square_orange_background)
        } else holder.itemView.rootLayout.setBackgroundResource(R.drawable.all_square_light_orange_background)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            selectedPosition = holder.adapterPosition
            fragment.onTapOptionSelected(selectedPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = tabList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}