package com.example.assignment.fragment

import android.app.DatePickerDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.assignment.R
import com.example.assignment.databinding.FragmentGoalInfoBinding
import com.example.assignment.util.ResourceUtils
import com.example.assignment.util.setSafeOnClickListener
import com.example.assignment.util.show
import kotlinx.android.synthetic.main.fragment_goal_info.*
import java.util.*

class GoalInfoFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewBinding: FragmentGoalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGoalInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (arguments?.getInt("position", 1)) {
            0 -> {
                viewBinding.addGoalLayout.show()
                investmentTypeSwitch.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewBinding.variableTextView.setTextColor(
                            ResourceUtils.getColor(
                                requireContext(),
                                R.color.black
                            )
                        )
                        viewBinding.fixedTextView.setTextColor(
                            ResourceUtils.getColor(
                                requireContext(),
                                R.color.dark_black
                            )
                        )

                        viewBinding.variableTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
                        viewBinding.fixedTextView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
                    } else {
                        viewBinding.fixedTextView.setTextColor(
                            ResourceUtils.getColor(
                                requireContext(),
                                R.color.black
                            )
                        )
                        viewBinding.variableTextView.setTextColor(
                            ResourceUtils.getColor(
                                requireContext(),
                                R.color.dark_black
                            )
                        )

                        viewBinding.fixedTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
                        viewBinding.variableTextView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
                    }
                }
            }
            1 -> viewBinding.knowYouLayout.show()
            2 -> viewBinding.riskLayout.show()
            3 -> viewBinding.knowYouLayout.show()
        }

        investmentIsForTextView.setSafeOnClickListener {
            showPopUpDialog()
        }

        selectPurchaseDateTextView.setSafeOnClickListener {
            showDateSelectionDialog()
        }

        selectDOB.setSafeOnClickListener {
            showDateSelectionDialog()
        }

        addMoreButton.setSafeOnClickListener {
            Toast.makeText(requireContext(), "Add More Clicked", Toast.LENGTH_LONG).show()
        }
    }

    private fun showPopUpDialog() {
        val popupMenu = PopupMenu(requireContext(), investmentIsForTextView)
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.self -> viewBinding.investmentIsForTextView.text =
                    "Who is this investment for - SELF"
                R.id.wife -> viewBinding.investmentIsForTextView.text =
                    "Who is this investment for - WIFE"
                R.id.family -> viewBinding.investmentIsForTextView.text =
                    "Who is this investment for - FAMILY"
            }
            false
        }
        popupMenu.show()
    }

    private fun showDateSelectionDialog() {
        val calender = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(), this,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.setOnShowListener {
            val buttonColor = ResourceUtils.getColor(
                requireContext(),
                R.color.black
            )
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(buttonColor)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(buttonColor)
        }

        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedMonth = month.plus(1)
        val selectedDate = "$dayOfMonth/$selectedMonth/$year"

        if (viewBinding.knowYouLayout.visibility == View.VISIBLE)
            viewBinding.selectDOB.text = selectedDate
        else
            viewBinding.selectPurchaseDateTextView.text = selectedDate
    }
}