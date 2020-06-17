/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.base.ui.base

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Base paged list adapter to standardize and simplify initialization for this component.
 *
 * @param itemsSame Function called to check whether two objects represent the same item.
 * @param contentsSame Function called to check whether two items have the same data.
 * @see PagedListAdapter
 */
abstract class BasePagedListAdapter<T>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean
) : PagedListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(old: T, new: T): Boolean = itemsSame(old, new)
    override fun areContentsTheSame(old: T, new: T): Boolean = contentsSame(old, new)
}) {

    @VisibleForTesting(otherwise = PRIVATE)
    internal var recyclerView: RecyclerView? = null

    init {
        // Avoid That RecyclerView’s Views are Blinking when notifyDataSetChanged.
        super.setHasStableIds(true)
    }

    /**
     * Called by RecyclerView when it starts observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter.
     * @see PagedListAdapter.onAttachedToRecyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see PagedListAdapter.onDetachedFromRecyclerView
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /**
     * Set the new list to be displayed.
     *
     * @param pagedList The new list to be displayed.
     * @see PagedListAdapter.submitList
     */
    override fun submitList(pagedList: PagedList<T>?) {
        super.submitList(pagedList)
        if (pagedList.isNullOrEmpty()) {
            // Fix recycle view not scrolling to the top after refresh the data source.
            recyclerView?.scrollToPosition(0)
        }
    }
}
