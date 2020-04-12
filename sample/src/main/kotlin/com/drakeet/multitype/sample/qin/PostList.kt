package com.drakeet.multitype.sample.qin

import com.drakeet.multitype.sample.bilibili.Post

class PostList constructor (internal val posts: List<Post>){
    var currentPosition = 0

}