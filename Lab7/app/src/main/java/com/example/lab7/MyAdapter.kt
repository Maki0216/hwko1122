package com.example.lab7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(
    context: Context,
    private val data: List<Item>, // 使用 `private` 明確封裝
    private val layout: Int // 外部提供 Layout ID
) : ArrayAdapter<Item>(context, layout, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 使用 ViewHolder 優化，避免重複執行 findViewById
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            // 如果尚未建立 View，透過 LayoutInflater 填充布局
            view = LayoutInflater.from(context).inflate(layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder // 將 ViewHolder 綁定到 View
        } else {
            // 重用現有 View
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // 根據 position 獲取對應的 Item 資料
        val item = data[position]

        // 設定圖片與文字
        viewHolder.imgPhoto.setImageResource(item.photo)
        viewHolder.tvMsg.text = if (layout == R.layout.adapter_vertical) {
            // 垂直顯示僅顯示名稱
            item.name
        } else {
            // 水平顯示名稱與價格
            "${item.name}: ${item.price}元"
        }

        return view
    }

    // ViewHolder 用於保存布局中的元件引用，減少頻繁 findViewById
    private class ViewHolder(view: View) {
        val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto)
        val tvMsg: TextView = view.findViewById(R.id.tvMsg)
    }
}
