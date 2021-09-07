package com.harshad.countsmsapp.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.harshad.countsmsapp.R
import com.harshad.countsmsapp.model.UserData
import kotlinx.android.synthetic.main.fragment_user_input.*

class UserInputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_user_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData(view)
    }

    private fun getUserData(view: View) {
        btnSmsCnt.setOnClickListener {
            if (isDataValidate()) {
                var mobileNum = etvMobileNumber.text.toString()
                var day = etvNumbersOfDays.text.toString()
                val uData = UserData("+91" + mobileNum, day)
                val action =
                    UserInputFragmentDirections.actionUserInputFragmentToShowMsgCountFragment(uData)
                view.findNavController().navigate(action)
            }
        }
    }

    private fun isDataValidate(): Boolean {
        var mobileNo = etvMobileNumber.text.toString()
        var days = etvNumbersOfDays.text.toString()
        if (TextUtils.isEmpty(mobileNo) || mobileNo.length != 10) {
            etvMobileNumber.setError("Mobile No Must Be 10 Digit & Mobile is Empty")
            return false
        } else if (TextUtils.isEmpty(days)) {
            etvNumbersOfDays.setError("Please Enter no of days")
            return false
        } else {
            return true
        }
    }
}