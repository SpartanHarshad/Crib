package com.harshad.countsmsapp.fragments

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.harshad.countsmsapp.R
import kotlinx.android.synthetic.main.fragment_show_msg_count.*
import java.util.*

class ShowMsgCountFragment : Fragment() {

    val args: ShowMsgCountFragmentArgs by navArgs()
    var mobileNo = "1234567890"
    var days = "0";
    var smsCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataAndStartSmsCount()
    }

    private fun getDataAndStartSmsCount() {
        mobileNo = args.userData!!.mobileNo
        days = args.userData!!.noOfDays
        context?.let { smsCount = getMsgCount(mobileNo, days, it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_show_msg_count, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSmsCnt.text = "Your $days days message count is $smsCount"
        gotoInputScreen()
    }

    private fun gotoInputScreen() {
        btnBack.setOnClickListener {
            findNavController().navigate(ShowMsgCountFragmentDirections.actionShowMsgCountFragmentToUserInputFragment())
        }
    }

    private fun getMsgCount(mobileNo: String, days: String, context: Context): Int {
        val SMS_URI_INBOX = "content://sms/inbox"
        var totalSmsCnt = 0;
        try {
            val uri: Uri = Uri.parse(SMS_URI_INBOX)
            val day = days.toLong()
            val date = Date(System.currentTimeMillis() - day * 24 * 3600 * 1000).time.toString()
            val Projection = arrayOf("address", "date");
            val selectionQuery = "(address='$mobileNo') AND (date>='$date')";
            val Sort = "date desc";
            val cur: Cursor? =
                context.contentResolver.query(uri, Projection, selectionQuery, null, Sort,
                    null)
            if (cur != null && cur.count > 0) {
                totalSmsCnt = cur.count
                if (!cur.isClosed()) {
                    cur.close();
                }
                return totalSmsCnt
            } else {
                return totalSmsCnt
            }
        } catch (ex: SQLiteException) {
            Log.d("SQLiteException", "${ex.message}");
            return totalSmsCnt
        }
    }
}


