package com.example.vtl_helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        // Toolbar 초기화 및 액션 바로 설정
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        settingButtons()
        supportActionBar?.title = null

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                // 로그아웃 항목 선택 시
                // 여기에 로그아웃 로직을 추가하세요
            }
            R.id.account -> {
                // 계정 항목 선택 시
                // 여기에 계정 관련 로직을 추가하세요
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //    override fun onCreateOptionmenu(menu: Menu?) Boolean{
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//
//        val account = menu?.findItem(R.id.account)
//        return super.onCreateOptionsMenu(menu)
//    }
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        setCustomToolbar(R.id.toolbar)
//    }
//
//    fun setCustomToolbar(layout: Int){
//        val toolbar = findViewById<Toolbar>(layout)
//        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
//        actionBar?.setDisplayShowTitleEnabled(true)
//    }

    fun settingButtons() {
        val button_emer = findViewById<Button>(R.id.emergency)
        button_emer.setOnClickListener {
            val intent = Intent(this, Emergency::class.java)
            startActivity(intent)
        }

        val button_use = findViewById<Button>(R.id.use)
        button_use.setOnClickListener {
            val intent = Intent(this, Use::class.java)
            startActivity(intent)
        }

        val button_map = findViewById<Button>(R.id.map)
        button_map.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        val button_takeoff = findViewById<Button>(R.id.takeoff)
        button_takeoff.setOnClickListener {
            val intent = Intent(this, TakeOff::class.java)
            startActivity(intent)
        }

        val button_landing = findViewById<Button>(R.id.landing)
        button_landing.setOnClickListener {
            val intent = Intent(this, landing::class.java)
            startActivity(intent)
        }

        val button_bookmark = findViewById<Button>(R.id.bookmark)
        button_bookmark.setOnClickListener {
            val intent = Intent(this, bookmark::class.java)
            startActivity(intent)
        }

        val button_faq = findViewById<Button>(R.id.faq)
        button_faq.setOnClickListener {
            val intent = Intent(this, FAQ::class.java)
            startActivity(intent)
        }
    }
}