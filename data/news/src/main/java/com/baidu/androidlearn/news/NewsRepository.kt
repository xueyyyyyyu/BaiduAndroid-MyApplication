package com.baidu.androidlearn.news

import androidx.annotation.AnyThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NewsRepository {

    fun getNewsTopics(): Flow<List<NewsTopic>> = flow {
        emit(
            buildFakeNewsTopics()
        )
    }

    @AnyThread
    private fun buildFakeNewsTopics(): List<NewsTopic> {
        return listOf(
            NewsTopic(
                id = "1",
                title = "悟空集齐了七龙珠后，向神龙许了想要挑战宇宙最强强者的愿望",
                imageUrl = "https://f7.baidu.com/it/u=1283549389,914386864&fm=222&app=106&size=f360,240&n=0&f=JPEG?sec=1690304400&t=cb3d57373d9c56678e41453da6b9983c"
            ),
            NewsTopic(
                id = "2",
                title = "最新！台风“杜苏芮”将于7月28日在闽粤沿海登陆",
                imageUrl = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics3.baidu.com%2Ffeed%2Ff3d3572c11dfa9ec0c69ae7b3260710f908fc150.jpeg%40f_auto%3Ftoken%3D7a4bd9c14ca7303e45ee26c2242739dd&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1690304400&t=4efe3b0a55623ad2c5c677fd783a62d7"
            ),
            NewsTopic(
                id = "3",
                title = "塞尔达传说王国之泪：大猪睡得真香，哈哈哈！",
                imageUrl = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpic.rmb.bdstatic.com%2Fbjh%2Fvideo%2F9f8275366a97c29913e8389e920cf710.jpeg%3Fx-bce-process%3Dimage%2Fformat%2Cf_auto&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1690304400&t=c045670eb7ba0b985ba2cf63e7ca45af"
            ),
            NewsTopic(
                id = "4",
                title = "杨紫成毅新剧对打！双方热度居高不下，沉香夫妇谁能更胜一筹？",
                imageUrl = "https://mbd.baidu.com/newspage/data/videolanding?nid=sv_15468353537610930669&sourceFrom=pc_feedlist"
            ),
            NewsTopic(
                id = "5",
                title = "没几年脑血栓绝对想不出这剧情，《小李飞刀》名场面",
                imageUrl = "https://mbd.baidu.com/newspage/data/videolanding?nid=sv_14590206411049370629&sourceFrom=pc_feedlist"
            ),
            NewsTopic(
                id = "6",
                title = "齐齐哈尔搜救和信息后续更新都跟上了，但为何遭到家长质疑？",
                imageUrl = "https://mbd.baidu.com/newspage/data/videolanding?nid=sv_1817554699646423057&sourceFrom=pc_feedlist"
            ),
            NewsTopic(
                id = "7",
                title = "王国之泪，TGA年度摩托车",
                imageUrl = "https://mbd.baidu.com/newspage/data/videolanding?nid=sv_1078317289962607606&sourceFrom=pc_feedlist"
            ),
            NewsTopic(
                id = "8",
                title = "「塞尔达传说 王国之泪」不开宝箱拿海利亚盾！窃取任意宝箱装备~",
                imageUrl = "https://mbd.baidu.com/newspage/data/videolanding?nid=sv_9279673184561385857&sourceFrom=pc_feedlist"
            )
        )
    }
}