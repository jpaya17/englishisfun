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

package com.jpaya.dynamicfeatures.abbreviations.ui

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.base.ui.base.BaseFragment
import com.jpaya.base.ui.extensions.observe
import com.jpaya.dynamicfeatures.abbreviations.R
import com.jpaya.dynamicfeatures.abbreviations.databinding.FragmentAbbreviationsListBinding
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapterState
import com.jpaya.dynamicfeatures.abbreviations.ui.di.AbbreviationsModule
import com.jpaya.dynamicfeatures.abbreviations.ui.di.DaggerAbbreviationsComponent
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem
import com.jpaya.englishisfun.di.dynamicfeatures.AbbreviationsModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/**
 * View listing all abbreviations.
 *
 * @see BaseFragment
 */
class AbbreviationsListFragment :
    BaseFragment<FragmentAbbreviationsListBinding, AbbreviationsListViewModel>(
        layoutId = R.layout.fragment_abbreviations_list
    ) {

    @Inject
    lateinit var viewAdapter: AbbreviationsListAdapter

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerAbbreviationsComponent
            .builder()
            .abbreviationsModuleDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext().applicationContext,
                    AbbreviationsModuleDependencies::class.java
                )
            )
            .abbreviationsModule(AbbreviationsModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        requireView().findViewById<RecyclerView>(R.id.abbreviations_list).apply {
            adapter = viewAdapter
        }
    }

    /**
     * Observer view data change on [AbbreviationsListViewModel].
     *
     * @param viewData Paged list of abbreviations.
     */
    private fun onViewDataChange(viewData: PagedList<AbbreviationItem>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observer view state change on [AbbreviationsListViewModel].
     *
     * @param viewState State of abbreviations list.
     */
    private fun onViewStateChange(viewState: AbbreviationsListViewState) {
        when (viewState) {
            is AbbreviationsListViewState.Loaded ->
                viewAdapter.submitState(AbbreviationsListAdapterState.Added)
            is AbbreviationsListViewState.AddLoading ->
                viewAdapter.submitState(AbbreviationsListAdapterState.AddLoading)
            is AbbreviationsListViewState.AddError ->
                viewAdapter.submitState(AbbreviationsListAdapterState.AddError)
            is AbbreviationsListViewState.NoMoreElements ->
                viewAdapter.submitState(AbbreviationsListAdapterState.NoMore)
        }
    }
}
