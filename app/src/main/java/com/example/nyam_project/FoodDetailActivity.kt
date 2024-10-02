package com.example.nyam_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nyam_project.databinding.ActivityFoodDetailBinding

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding
    private lateinit var shareMessage: String

    companion object {
        const val EXTRA_FOOD = "extra_food"
        const val SHARE_MESSAGE = "Bagikan Artikel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var openLinkIntent = Intent(Intent.ACTION_VIEW)

        val food =
            if (android.os.Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(EXTRA_FOOD,Food::class.java)
            } else {
                @Suppress("DEPRECATION") intent.getParcelableExtra<Food>(EXTRA_FOOD)
            }

        binding.fabRecipe.setOnClickListener {
//            Toast.makeText(this, "Sesuaikan Selera anda!", Toast.LENGTH_SHORT).show()
            openLinkIntent.data?.let { startActivity(openLinkIntent) }
        }

        binding.fabShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareMessage)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, SHARE_MESSAGE))
        }

        food?.let {
            binding.imgItem.setImageResource(it.photo)
            binding.tvTitle.text = it.title
            binding.tvDesc.text = it.desc
            binding.tvAdditionalDesc.text = it.additionalInfo
            openLinkIntent.setData(Uri.parse(it.recipeLink))
            shareMessage =
                """
                    ${it.title}
                    ${it.desc}
                    
                    ${it.additionalInfo}
                    here for the recepe : ${it.recipeLink}
                    
                    Tertanda,
                    NewTaste - Nyam Project Team
                """.trimIndent()
        }
    }
}