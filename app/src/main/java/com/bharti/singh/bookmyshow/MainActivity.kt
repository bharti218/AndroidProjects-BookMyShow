package com.bharti.singh.bookmyshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bharti.singh.bookmyshow.fragments.ViewerHomeFragment
import com.bharti.singh.bookmyshow.fragments.ViewerSettingFragment
import com.bharti.singh.bookmyshow.fragments.ViewerUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var selectorFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    selectorFragment = ViewerHomeFragment()

                }
                R.id.nav_setting -> {
                    selectorFragment = ViewerSettingFragment()
                }
                R.id.nav_user -> {
                    selectorFragment = ViewerUserFragment()
                }
                else -> {
                    selectorFragment = ViewerHomeFragment()
                }
            }
            if (selectorFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectorFragment).commit()
            }
            true
        }
    }
}