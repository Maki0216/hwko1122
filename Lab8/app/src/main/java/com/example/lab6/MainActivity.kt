package com.example.lab6

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    inner class Data(val photo: Int, val name: String)

    inner class MyAdapter(private val data: Array<Data>, private val layoutRes: Int) : BaseAdapter() {
        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Data = data[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: layoutInflater.inflate(layoutRes, parent, false)
            val name = view.findViewById<TextView>(R.id.name)
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            name.text = data[position].name
            imageView.setImageResource(data[position].photo)

            return view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // 讓 Spinner 的下拉選單能正確顯示
            return getView(position, convertView, parent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士")
        val transPhotoIdArray = intArrayOf(
            R.drawable.trans1, R.drawable.trans2,
            R.drawable.trans3, R.drawable.trans4
        )

        val cubeeNameArray = arrayOf(
            "哭哭", "發抖", "再見", "生氣",
            "昏倒", "竊笑", "很棒", "你好",
            "驚嚇", "大笑"
        )
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.cubee1, R.drawable.cubee2,
            R.drawable.cubee3, R.drawable.cubee4,
            R.drawable.cubee5, R.drawable.cubee6,
            R.drawable.cubee7, R.drawable.cubee8,
            R.drawable.cubee9, R.drawable.cubee10
        )

        val messageArray = arrayOf(
            "訊息１", "訊息２", "訊息３",
            "訊息４", "訊息５", "訊息６"
        )

        // 使用非空陣列創建資料陣列
        val transData = Array(transNameArray.size) { i ->
            Data(photo = transPhotoIdArray[i], name = transNameArray[i])
        }

        val cubeeData = Array(cubeeNameArray.size) { i ->
            Data(photo = cubeePhotoIdArray[i], name = cubeeNameArray[i])
        }

        // 配置適配器
        val transAdapter = MyAdapter(transData, R.layout.trans_list)
        val cubeeAdapter = MyAdapter(cubeeData, R.layout.cubee_list)
        val messageAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messageArray)

        // 設定 Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = transAdapter

        // 設定 GridView
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = cubeeAdapter

        // 設定 ListView
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = messageAdapter
    }
}
