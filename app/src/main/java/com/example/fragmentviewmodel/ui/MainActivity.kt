package com.example.fragmentviewmodelupdate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentviewmodel.R
import com.example.fragmentviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.linear_container, MainFragment.newInstance())
                .add(R.id.linear_container, SubFragment.newInstance())
                .commitNow()
        }
    }
}