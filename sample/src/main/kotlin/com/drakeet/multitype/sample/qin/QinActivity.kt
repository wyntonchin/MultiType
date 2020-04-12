package com.drakeet.multitype.sample.qin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.drakeet.multitype.sample.R
import com.drakeet.multitype.sample.bilibili.BilibiliActivity
import com.drakeet.multitype.sample.bilibili.Post


class QinActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    internal lateinit var  multiTypeAdapter: MultiTypeAdapter
    internal lateinit var items: MutableList<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        multiTypeAdapter = MultiTypeAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        //每一行最多可以显示12个Item
        val gridLayoutManager = GridLayoutManager(this, 12)
        //根据实际情况，每一种类型的Item占据多少权重。比如第一行这种类型的最多有6个，每个占据的权重就是2
        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(i: Int): Int {
                if (items.get(i) is MyBannerView.Bean0) {
                    return 12 / 6;   //每个item占据2个权重
                } else if (items.get(i) is MyBannerView1.Bean1) {
                    return 12 / 1;   //每个item占据12权重

                }else if (items.get(i) is PostList) {
                    return 12

                } /*else if (items.get(i) is MyBannerView2.Bean2) {
                    return 12 / 1;
                } else if (items.get(i) is MyBannerView3.Bean3) {
                    return 12 / 1;
                }*/
                return 1
            }
        }
        gridLayoutManager.spanSizeLookup = spanSizeLookup
        //设置RecycleView
        recyclerView.layoutManager = gridLayoutManager

        //类似于ArrayList<Object>
        items = ArrayList()
        //将每种类型的Item布局，注册到multiTypeAdapter 里面去
        multiTypeAdapter = MultiTypeAdapter(items)
        multiTypeAdapter.register(MyBannerView())
        multiTypeAdapter.register(MyBannerView1())
        val data = BilibiliActivity.JsonData()
        var hlist: MutableList<Post> = java.util.ArrayList()
        for (i in 0..99) {
            /* You also could use Category as your CategoryItemContent directly */
            hlist.add(data.postArray[0])
            hlist.add(data.postArray[1])
            hlist.add(data.postArray[2])
            hlist.add(data.postArray[3])
            hlist.add(data.postArray[0])
            hlist.add(data.postArray[1])
            //list.add(PostList(data.postList))
            //items.add(hlist)
        }
        items.add(PostList(hlist))
        multiTypeAdapter.register(HorizontalItemViewBinder(PostList(hlist)))

/*        multiTypeAdapter.register(ArrayList.class, new MyBannerView2());
        multiTypeAdapter.register(MyBannerView3.Bean3.class, new MyBannerView3());
        multiTypeAdapter.register(MyEmptyBannerView.EmptyBean.class, new MyEmptyBannerView());*/
        recyclerView.adapter = multiTypeAdapter
        //请求数据，刷新数据。此时Adapter会根据item中的数据类型找到对应的布局并显示。
        requestData()
    }

     fun requestData() {
        //伪代码，模拟请求。
        //items.clear()
        for (i in 0..5) {
            items.add( MyBannerView.Bean0(R.mipmap.ic_launcher, "测试"))
        }
        items.add( MyBannerView1.Bean1("https://www.baidu.com/"))

        //items.add( MyEmptyBannerView.EmptyBean(null))
        //items.add( MyEmptyBannerView.EmptyBean("热门应用"));
/*        ArrayList<MyBannerView2.Bean2> list = new ArrayList<>()
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "饿了么", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "腾讯视频", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "美团", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "腾讯QQ", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "微信", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "抖音", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "高德地图", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "百度外卖", ""));
        list.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "大众点评", ""));
        items.add(list);
        items.add(new MyEmptyBannerView.EmptyBean(null));
        items.add(new MyEmptyBannerView.EmptyBean("热门游戏"));
        ArrayList<MyBannerView2.Bean2> list1 = new ArrayList<>();
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "王者荣耀", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "皇室战争", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "炫舞", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "一道传世", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "三国群英传", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "火影忍者", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "穿越火线", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "英雄杀送", ""));
        list1.add(new MyBannerView2.Bean2(R.mipmap.ic_launcher, "使命召唤", ""));
        items.add(list1);
        items.add(new MyEmptyBannerView.EmptyBean(null));
        items.add(new MyEmptyBannerView.EmptyBean("长路漫漫，旅途相伴"));
        for (int i = 0; i < 15; i++) {
            items.add(new MyBannerView3.Bean3(R.mipmap.ic_launcher, "穿越火线" + i, "特别好玩的游戏" + i, ""));
        }*/
        multiTypeAdapter.notifyDataSetChanged()

    }
}
