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

package com.jpaya.englishisfun.irregulars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.jpaya.englishisfun.R
import com.jpaya.base.ui.searchview.DebouncingQueryTextListener
import com.jpaya.englishisfun.databinding.IrregularsFragmentListBinding
import com.jpaya.englishisfun.irregulars.ui.adapter.IrregularsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IrregularsListFragment : RainbowCakeFragment<IrregularsListViewState, IrregularsListViewModel>() {

    private val customViewModel: IrregularsListViewModel by viewModels()
    private lateinit var binding: IrregularsFragmentListBinding

    override fun provideViewModel() = customViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = IrregularsFragmentListBinding.inflate(inflater, container, false)
        binding.viewModel = customViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = IrregularsAdapter()
        binding.list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)

        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            queryHint = getString(R.string.search)
            setOnQueryTextListener(
                DebouncingQueryTextListener(this@IrregularsListFragment) {
                    if (it.isNullOrEmpty()) viewModel.resetSearch() else viewModel.search(it)
                }
            )
            clearFocus()
        }
    }

    override fun render(viewState: IrregularsListViewState) {
        TransitionManager.beginDelayedTransition(binding.listFragmentRoot)
        binding.viewState = viewState
    }
}
