package com.baidu.androidlearn.video

class VideoRepository {

    fun load(callback: ((videos: List<VideoModel>) -> Unit)) {
        callback.invoke(
            listOf(
                VideoModel(
                    id = "1",
                    title = "悟空集齐了七龙珠后，向神龙许了想要挑战宇宙最强强者的愿望",
                    thumbnail = "https://f7.baidu.com/it/u=1283549389,914386864&fm=222&app=106&size=f360,240&n=0&f=JPEG?sec=1690304400&t=cb3d57373d9c56678e41453da6b9983c",
                    updateTime = "2023-07-20"
                ),
                VideoModel(
                    id = "2",
                    title = "最新！台风“杜苏芮”将于7月28日在闽粤沿海登陆",
                    thumbnail = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics3.baidu.com%2Ffeed%2Ff3d3572c11dfa9ec0c69ae7b3260710f908fc150.jpeg%40f_auto%3Ftoken%3D7a4bd9c14ca7303e45ee26c2242739dd&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1690304400&t=4efe3b0a55623ad2c5c677fd783a62d7",
                    updateTime = "2023-07-21"
                ),
                VideoModel(
                    id = "3",
                    title = "塞尔达传说王国之泪：大猪睡得真香，哈哈哈！",
                    thumbnail = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpic.rmb.bdstatic.com%2Fbjh%2Fvideo%2F9f8275366a97c29913e8389e920cf710.jpeg%3Fx-bce-process%3Dimage%2Fformat%2Cf_auto&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1690304400&t=c045670eb7ba0b985ba2cf63e7ca45af",
                    updateTime = "2023-07-22"
                ),
            )
        )
    }
}