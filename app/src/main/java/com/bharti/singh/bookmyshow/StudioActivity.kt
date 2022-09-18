package com.bharti.singh.bookmyshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bharti.singh.bookmyshow.fragments.StudioHomeFragment
import com.bharti.singh.bookmyshow.fragments.StudioAddShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudioActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio)

        bottomNavigationView = findViewById(R.id.studio_bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener{item->
            when(item.itemId){
                R.id.studio_nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.studio_fragment_container, StudioHomeFragment()).commit()
                }
                R.id.studio_nav_add -> {
                    supportFragmentManager.beginTransaction().replace(R.id.studio_fragment_container, StudioAddShowFragment()).commit()
                }
            }
            true
        }

    }
}