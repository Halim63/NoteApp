package com.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.noteapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initNavigation()


    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.addNoteFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initView() {
        initToolbar()
    }

    private fun initToolbar() = setSupportActionBar(toolbar)


}
