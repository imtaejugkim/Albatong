package com.example.albatong.er

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.core.widget.ImageViewCompat
import com.example.albatong.R
import com.example.albatong.databinding.ErActivitySpecificMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class ERActivitySpecificMain : AppCompatActivity() {
    val textarr = arrayListOf<String>("일정 관리", "직원 관리", "인수인계" , "공지사항")
    val imgarr = arrayListOf<Int>(R.drawable.baseline_calendar_month_24, R.drawable.baseline_people_alt_24,R.drawable.baseline_content_paste_go_24,R.drawable.baseline_access_alarm_24)
    lateinit var binding: ErActivitySpecificMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ErActivitySpecificMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val employerSettingButton: ImageButton = findViewById(R.id.employerSettingButton)
        val employerNotificationHistoryButton: ImageButton = findViewById(R.id.employerNotificationHistoryButton)
        employerSettingButton.background = null
        employerNotificationHistoryButton.background = null
        ImageViewCompat.setImageTintList(employerSettingButton, ColorStateList.valueOf(Color.WHITE))
        ImageViewCompat.setImageTintList(employerNotificationHistoryButton, ColorStateList.valueOf(Color.WHITE))

        initLayout()
        init()
    }
    private fun init() {
        binding.employerSettingButton.setOnClickListener {
            val i = Intent(this@ERActivitySpecificMain, ERActivitySetting::class.java)
            startActivity(i)
        }
        binding.employerNotificationHistoryButton.setOnClickListener {
            val i = Intent(this@ERActivitySpecificMain, ERActivityNotificationList::class.java)
            startActivity(i)
        }
    }

    private fun initLayout(){
        binding.viewpager.adapter = ERAdapterMyViewPager(this)
        TabLayoutMediator(binding.tablayout, binding.viewpager){
                tab, pos ->
            tab.text = textarr[pos]
            tab.setIcon(imgarr[pos])
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}