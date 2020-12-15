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

package com.jpaya.englishisfun.statives.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.jpaya.englishisfun.databinding.StativesFragmentListBinding
import com.jpaya.englishisfun.statives.ui.adapter.StativesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StativesFragment : RainbowCakeFragment<StativesViewState, StativesViewModel>() {

    private val customViewModel: StativesViewModel by viewModels()
    private lateinit var binding: StativesFragmentListBinding

    override fun provideViewModel() = customViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = StativesFragmentListBinding.inflate(inflater, container, false)
        binding.viewModel = customViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = StativesAdapter()
        binding.list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun render(viewState: StativesViewState) {
        TransitionManager.beginDelayedTransition(binding.listFragmentRoot)
        binding.viewState = viewState
    }
}
