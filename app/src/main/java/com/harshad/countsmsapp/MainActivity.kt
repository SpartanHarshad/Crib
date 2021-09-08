package com.harshad.countsmsapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQ_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initNavGraph();
        checkPermission(Manifest.permission.READ_SMS, REQ_CODE)
    }

    private fun checkPermission(readSms: String, reqCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.READ_SMS), REQ_CODE)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size >= 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.READ_SMS), REQ_CODE)
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(Manifest.permission.READ_SMS), REQ_CODE)
                    ShowToast("Permission Denied")
                }
            }
        }
    }

    private fun ShowToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}