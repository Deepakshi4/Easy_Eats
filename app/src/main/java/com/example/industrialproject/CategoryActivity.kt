package com.example.industrialproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.industrialproject.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title=intent.getStringExtra("TITTLE")
        setUpRecyclerView()
        binding.goBackHome.setOnClickListener{
            finish()
        }
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rvCategory.layoutManager=
            LinearLayoutManager(this)
        var db = Room.databaseBuilder(this@CategoryActivity,AppDataBase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.equals(intent.getStringExtra("CATEGORY")!!)){
                dataList.add(recipes[i]!!)
            }
            rvAdapter= CategoryAdapter(dataList,this)
            binding.rvCategory.adapter=rvAdapter
        }

    }
}