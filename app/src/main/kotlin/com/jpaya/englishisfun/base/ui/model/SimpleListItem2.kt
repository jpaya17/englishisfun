package com.jpaya.englishisfun.base.ui.model

import com.jpaya.base.adapter.GenericAdapterComparator

data class SimpleListItem2(
    val id: Long,
    val text1: String,
    val text2: String
) : GenericAdapterComparator<SimpleListItem2> {

    override fun isSameItemAs(item: SimpleListItem2) = id == item.id

    override fun hasSameContentsAs(item: SimpleListItem2) = this == item
}
