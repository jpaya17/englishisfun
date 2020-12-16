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

package com.jpaya.englishisfun.abbreviations.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.base.adapter.ListAdapterComparator
import com.jpaya.englishisfun.DataBindingAdapter
import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import com.jpaya.englishisfun.databinding.SimpleListItem2Binding

class AbbreviationsAdapter :
    ListAdapter<SimpleListItem2, AbbreviationsAdapter.ViewHolder>(ListAdapterComparator<SimpleListItem2>()),
    DataBindingAdapter<List<SimpleListItem2>> {

    override fun setData(data: List<SimpleListItem2>) = submitList(data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(SimpleListItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(val binding: SimpleListItem2Binding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SimpleListItem2) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
