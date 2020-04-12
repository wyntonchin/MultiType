package com.drakeet.multitype.sample.qin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R


class MyBannerView : ItemViewBinder<MyBannerView.Bean0, MyBannerView.ViewHolder0>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder0 {

        val view0: View = inflater.inflate(R.layout.item_banner0, parent, false)
        return ViewHolder0(view0)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder0, @NonNull bean0: Bean0) {
        holder.icon0.setImageResource(bean0.ResId) //图片
        holder.textView0.text = bean0.names //下面的APP名字
    }

     class ViewHolder0(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon0: ImageView
        var textView0: TextView

        init {
            icon0 = itemView.findViewById(R.id.icon0)
            textView0 = itemView.findViewById(R.id.tv0)
        }
    }

    //数据源Bean
    class Bean0(var ResId: Int, var names: String)



}