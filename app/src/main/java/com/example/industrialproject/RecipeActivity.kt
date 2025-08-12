package com.example.industrialproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.industrialproject.databinding.ActivityRecipeBinding


class RecipeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecipeBinding
    var imgCrop=true
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.tittle.text=intent.getStringExtra("tittle")
        binding.stepData.text=intent.getStringExtra("des")


        //this will get the time for make any recipe
        var ing = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text=ing?.get(0)

        for (i in 1 until ing!!.size)
        {
            binding.ingData.text=
                """${binding.ingData.text}🟢${ing[i]}
                    
                """.trimIndent()
        }


        binding.step.background=null
        binding.step.setTextColor(getColor(R.color.black))

        binding.step.setOnClickListener {
            binding.step.setBackgroundResource(R.drawable.btn_ing)
            binding.step.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.setBackgroundResource(0)
            binding.stepScroll.visibility=View.VISIBLE
            binding.ingScroll.visibility=View.GONE

        }


        binding.ing.setOnClickListener {
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(getColor(R.color.white))
            binding.step.setTextColor(getColor(R.color.black))
            binding.step.setBackgroundResource(0)
            binding.ingScroll.visibility=View.VISIBLE
            binding.stepScroll.visibility=View.GONE
        }


        binding.fullScreen.setOnClickListener {
            if (imgCrop)
            {
                binding.itemImage.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility=View.GONE
                imgCrop=!imgCrop
            }
            else
            {
                binding.itemImage.scaleType= ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility=View.GONE
                imgCrop=!imgCrop
            }

        }

        binding.backBtn.setOnClickListener {
            finish()
        }



    }
}