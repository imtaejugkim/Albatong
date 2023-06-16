package com.example.albatong.ee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.albatong.R
import com.example.albatong.data.Employee
import com.example.albatong.databinding.EeActivitySpecificMainBinding
import com.example.albatong.employee.EmployeeFragmentStoreList
import com.example.albatong.login.LoginActivity
import com.example.albatong.login.SignAcitivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.database.ktx.values

class EEActivitySpecificMain : AppCompatActivity() {
    val textarr = arrayListOf<String>("캘린더", "공지사항", "인수인계")
    val imgarr = arrayListOf<Int>(R.drawable.baseline_calendar_month_24, R.drawable.baseline_assignment_24,R.drawable.baseline_feed_24)

    private lateinit var binding : EeActivitySpecificMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EeActivitySpecificMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userID = intent.getStringExtra("user_id")

        binding.eeViewPager.adapter = EEAdapterViewPage(this)
        TabLayoutMediator(binding.eeTabLayout, binding.eeViewPager) {
                tab, pos ->
            tab.text = textarr[pos]
            tab.setIcon(imgarr[pos])
        }.attach()


        binding.employeeSettingButton.setOnClickListener {
            val intent = Intent(this@EEActivitySpecificMain,EEsettingActivity::class.java)
            startActivity(intent)
        }

        binding.employeeNotificationHistoryButton.setOnClickListener {
            val intent = Intent(this@EEActivitySpecificMain,SignAcitivity::class.java)
            intent.putExtra("user_id", userID)
            startActivity(intent)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
