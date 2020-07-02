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

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Base list adapter to standardize and simplify initialization for this component.
 *
 * @param itemsSame Function called to check whether two objects represent the same item.
 * @param contentsSame Function called to check whether two items have the same data.
 * @see ListAdapter
 */
abstract class BaseListAdapter<T>(itemsSame: (T, T) -> Boolean, contentsSame: (T, T) -> Boolean) :
    BaseViewHolderListAdapter<T, RecyclerView.ViewHolder>(itemsSame, contentsSame)
