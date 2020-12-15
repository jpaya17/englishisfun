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

package com.jpaya.englishisfun.suggestions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.jpaya.englishisfun.R
import com.jpaya.englishisfun.databinding.SuggestionsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionsFragment : RainbowCakeFragment<SuggestionsViewState, SuggestionsViewModel>() {

    private val customViewModel: SuggestionsViewModel by viewModels()
    private lateinit var binding: SuggestionsFragmentBinding

    override fun provideViewModel() = customViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SuggestionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.send.setOnClickListener {
            viewModel.sendSuggestion(
                binding.title.text.toString(),
                resources.getStringArray(R.array.suggestions_sections_values)[binding.section.selectedItemId.toInt()],
                binding.description.text.toString()
            )
        }
    }

    override fun render(viewState: SuggestionsViewState) {
        // TODO
    }
}
