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

package com.jpaya.englishisfun.abbreviations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.jpaya.base.ui.recyclerview.SpaceGrid
import com.jpaya.base.ui.searchview.DebouncingQueryTextListener
import com.jpaya.englishisfun.R
import com.jpaya.englishisfun.abbreviations.ui.adapter.AbbreviationsAdapter
import com.jpaya.englishisfun.databinding.AbbreviationsFragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbbreviationsListFragment : RainbowCakeFragment<AbbreviationsListViewState, AbbreviationsListViewModel>() {

    companion object {
        private const val COLUMNS = 2
        private const val SPACING = 30
    }

    private val customViewModel: AbbreviationsListViewModel by viewModels()
    private lateinit var binding: AbbreviationsFragmentListBinding

    override fun provideViewModel() = customViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AbbreviationsFragmentListBinding.inflate(inflater, container, false)
        binding.viewModel = customViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.layoutManager = GridLayoutManager(context, COLUMNS)
        binding.list.adapter = AbbreviationsAdapter()
        binding.list.addItemDecoration(SpaceGrid(COLUMNS, SPACING, true))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)

        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            queryHint = getString(R.string.search)
            setOnQueryTextListener(
                DebouncingQueryTextListener(this@AbbreviationsListFragment) {
                    if (it.isNullOrEmpty()) viewModel.resetSearch() else viewModel.search(it)
                }
            )
            clearFocus()
        }
    }

    override fun render(viewState: AbbreviationsListViewState) {
        TransitionManager.beginDelayedTransition(binding.listFragmentRoot)
        binding.viewState = viewState
    }
}
