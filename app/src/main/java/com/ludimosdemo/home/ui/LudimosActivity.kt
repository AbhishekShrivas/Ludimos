package com.ludimosdemo.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ludimosdemo.R
import com.ludimosdemo.databinding.ActivityLudimosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LudimosActivity : AppCompatActivity() {
    private var _binding : ActivityLudimosBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLudimosBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}