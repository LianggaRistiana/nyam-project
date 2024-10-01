package com.example.nyam_project

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyam_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listFood = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { false }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFoods.setHasFixedSize(true)
        listFood.addAll(getListOfFood())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFoods.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFoods.layoutManager = LinearLayoutManager(this)
        }
        val foodAdapter = ListFoodAdapter(listFood) {
            Toast.makeText(this, "Kamu memilih " + it.title, Toast.LENGTH_SHORT).show()

        }
        binding.rvFoods.adapter = foodAdapter
    }

    private fun getListOfFood(): ArrayList<Food> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFood = ArrayList<Food>()

        for (i in dataTitle.indices){
            val food = Food(dataTitle[i], dataDesc[i], dataPhoto.getResourceId(i, -1))
            listFood.add(food)
        }
//        dataTitle.indices.forEach {
//            val food = Food(dataTitle[it], dataDesc[it], dataPhoto.getResourceId(it, -1))
//            listFood.add(food)
//        }
        return listFood
    }
}