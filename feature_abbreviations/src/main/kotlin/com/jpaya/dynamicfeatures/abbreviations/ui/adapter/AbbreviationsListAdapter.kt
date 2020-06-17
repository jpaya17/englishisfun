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

package com.jpaya.dynamicfeatures.abbreviations.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.base.ui.base.BaseListAdapter
import com.jpaya.base.ui.base.BaseViewHolderListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.holders.AbbreviationViewHolder
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem
import javax.inject.Inject

/**
 * Class for presenting abbreviations List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BaseListAdapter
 */
class AbbreviationsListAdapter @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val viewModel: AbbreviationsListViewModel
) : BaseViewHolderListAdapter<AbbreviationItem, AbbreviationViewHolder>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: AbbreviationsListAdapterState = AbbreviationsListAdapterState.Added

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see BaseListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbbreviationViewHolder =
        AbbreviationViewHolder(LayoutInflater.from(parent.context))

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see BaseViewHolderListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: AbbreviationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(viewModel, it)
        }
    }

    /**
     * Return the stable ID for the item at position.
     *
     * @param position Adapter position to query.
     * @return The stable ID of the item at position.
     * @see BaseViewHolderListAdapter.getItem
     */
    override fun getItemId(position: Int) = getItem(position).id

    /**
     * Update current adapter state with the new one, applying visual changes.
     *
     * @param newState State of list adapter to update.
     */
    fun submitState(newState: AbbreviationsListAdapterState) {
        val oldState = state
        state = newState
        if (oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}
