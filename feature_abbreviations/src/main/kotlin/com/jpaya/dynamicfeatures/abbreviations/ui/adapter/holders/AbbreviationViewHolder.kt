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

package com.jpaya.dynamicfeatures.abbreviations.ui.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.base.ui.base.BaseViewHolder
import com.jpaya.dynamicfeatures.abbreviations.databinding.ListItemAbbreviationBinding
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem

/**
 * Class describes abbreviation view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class AbbreviationViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemAbbreviationBinding>(
    binding = ListItemAbbreviationBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel Abbreviation list view model.
     * @param item Abbreviation list item.
     */
    fun bind(viewModel: AbbreviationsListViewModel, item: AbbreviationItem) {
        binding.viewModel = viewModel
        binding.abbreviation = item
        binding.executePendingBindings()
    }
}
