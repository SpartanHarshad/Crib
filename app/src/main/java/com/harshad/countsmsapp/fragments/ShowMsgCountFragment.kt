package com.harshad.countsmsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.harshad.countsmsapp.R

class ShowMsgCountFragment : Fragment() {

    val args: ShowMsgCountFragmentArgs by navArgs()
    var mobileNo = "1234567890"
    var days = "0";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobileNo = args.userData!!.mobileNo
        days = args.userData!!.noOfDays
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_msg_count, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Mobile no is $mobileNo and days is $days", Toast.LENGTH_SHORT).show()
    }
}