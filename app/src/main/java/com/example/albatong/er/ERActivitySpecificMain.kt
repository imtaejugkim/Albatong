package com.example.albatong.er

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import com.example.albatong.R
import com.example.albatong.databinding.ErActivitySpecificMainBinding
import com.example.albatong.login.SignAcitivity
import com.google.android.material.tabs.TabLayoutMediator

class ERActivitySpecificMain : AppCompatActivity() {
    val textarr = arrayListOf<String>("일정 관리", "직원 관리", "공지사항" , "인수인계")
    val imgarr = arrayListOf<Int>(R.drawable.baseline_calendar_month_24, R.drawable.baseline_people_alt_24,R.drawable.baseline_access_alarm_24,R.drawable.baseline_content_paste_go_24)
    lateinit var binding: ErActivitySpecificMainBinding
    val userid:String="null"
    var store_name:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ErActivitySpecificMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        store_name = intent.getStringExtra("store_name")

        setSupportActionBar(binding.toolbarErSpecific)
        if (store_name != null) {
            supportActionBar?.title = store_name
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val employerSettingButton: ImageButton = findViewById(R.id.employerSettingButton)
        val employerNotificationHistoryButton: ImageButton = findViewById(R.id.employerNotificationHistoryButton)
        employerSettingButton.background = null
        employerNotificationHistoryButton.background = null

        initLayout()
        init()
    }
    private fun init() {
        val userID = intent.getStringExtra("user_id")
        binding.employerSettingButton.setOnClickListener {
            val i = Intent(this@ERActivitySpecificMain, ERsettingActivity::class.java)
            i.putExtra("user_id", userID)
            startActivity(i)
        }

        binding.employerNotificationHistoryButton.setOnClickListener {
            val i = Intent(this@ERActivitySpecificMain, SignAcitivity::class.java)
            i.putExtra("user_type", "employer")
            i.putExtra("user_id", userID)
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