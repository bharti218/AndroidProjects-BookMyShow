package com.bharti.singh.bookmyshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bharti.singh.bookmyshow.fragments.HomeFragment
import com.bharti.singh.bookmyshow.fragments.SettingFragment
import com.bharti.singh.bookmyshow.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var selectorFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_home -> {
                        selectorFragment = HomeFragment()

                    }
                    R.id.nav_setting -> {
                        selectorFragment = SettingFragment()
                    }
                    R.id.nav_user -> {
                        selectorFragment = UserFragment()
                    }
                    else -> {
                        selectorFragment = HomeFragment()
                    }
                }
                if(selectorFragment!=null){
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectorFragment).commit()
                }
                return true
            }


        })
    }
}