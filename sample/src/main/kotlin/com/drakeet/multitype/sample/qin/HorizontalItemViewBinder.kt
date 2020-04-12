package com.drakeet.multitype.sample.qin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.bilibili.Post
import com.drakeet.multitype.sample.bilibili.PostsAdapter

class HorizontalItemViewBinder(postList: PostList) : ItemViewBinder<PostList, HorizontalItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        /* item_horizontal_list 就是一个只有 RecyclerView 的布局 */
        val view = inflater.inflate(R.layout.item_horizontal_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, postList: PostList) {
        holder.setPosts(postList.posts)
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById<View>(R.id.post_list) as RecyclerView
        private val adapter: PostsAdapter
        fun setPosts(posts: List<Post>) {
            adapter.setPosts(posts)
            adapter.notifyDataSetChanged()
        }

        init {
            val layoutManager = LinearLayoutManager(itemView.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = layoutManager
            /* adapter 只负责灌输、适配数据，布局交给 LayoutManager，可复用 */
            adapter = PostsAdapter()
            recyclerView.adapter = adapter
            /* 在此设置横向滑动监听器，用于记录和恢复当前滑动到的位置，略 */ //...
        }
    }
}