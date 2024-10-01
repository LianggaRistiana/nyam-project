package com.example.nyam_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.nyam_project.databinding.ActivityFoodDetailBinding

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding

    companion object {
        const val EXTRA_FOOD = "extra_food"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var openLinkIntent = Intent(Intent.ACTION_VIEW)

        val food =
            if (android.os.Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<Food>(EXTRA_FOOD)
            } else {
                @Suppress("DEPRECATION") intent.getParcelableExtra<Food>(EXTRA_FOOD)
            }

        binding.fabRecipe.setOnClickListener {
            Toast.makeText(this, "Recipe button clicked", Toast.LENGTH_SHORT).show()
            openLinkIntent.data?.let { startActivity(openLinkIntent) }
        }

        food?.let {
            binding.imgItem.setImageResource(it.photo)
            binding.tvTitle.text = it.title
            binding.tvDesc.text = it.desc
            binding.tvAdditionalDesc.text = it.additionalInfo
            openLinkIntent.setData(Uri.parse(it.recipeLink))
        }
    }
}