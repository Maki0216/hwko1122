package com.example.lab7

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 自動調整系統邊距
        applyEdgeToEdge()

        // 取得元件
        val spinner = findViewById<Spinner>(R.id.spinner)
        val listView = findViewById<ListView>(R.id.listView)
        val gridView = findViewById<GridView>(R.id.gridView)

        // 購買數量清單
        val countList = mutableListOf<String>()
        // 水果資訊清單
        val itemList = mutableListOf<Item>()

        // 設定價格範圍
        val priceRange = 10..100
        // 讀取圖檔陣列
        val imageArray = resources.obtainTypedArray(R.array.image_list)

        // 初始化水果資訊
        for (index in 0 until imageArray.length()) {
            val photoId = imageArray.getResourceId(index, 0) // 水果圖片 ID
            val name = "水果 ${index + 1}" // 水果名稱
            val price = priceRange.random() // 隨機價格
            countList.add("${index + 1} 個") // 可購買數量
            itemList.add(Item(photoId, name, price)) // 水果資訊
        }

        // 釋放資源
        imageArray.recycle()

        // 配置 Spinner 的適配器
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            countList
        )

        // 配置 GridView 的適配器
        gridView.numColumns = 3 // 設定列數
        gridView.adapter = MyAdapter(this, itemList, R.layout.adapter_vertical)

        // 配置 ListView 的適配器
        listView.adapter = MyAdapter(this, itemList, R.layout.adapter_horizontal)
    }

    // 邊距自動適配
    private fun applyEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
