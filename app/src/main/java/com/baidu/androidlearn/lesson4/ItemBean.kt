package com.baidu.androidlearn.lesson4

/**
 * 列表每一项的数据
 *
 * @author: xiejikun
 * @since: 2023/7/7
 */
data class ItemBean(
    /** 图片地址 */
    val coverUrl: Int?,
    /** 标题 */
    val title: String?,
    /** 摘要 */
    val content: String?
)