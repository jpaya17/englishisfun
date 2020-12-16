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

package com.jpaya.englishisfun.about.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.jpaya.englishisfun.R
import com.jpaya.englishisfun.databinding.AboutFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import de.psdev.licensesdialog.LicensesDialog

@AndroidEntryPoint
class AboutFragment : RainbowCakeFragment<AboutViewState, AboutViewModel>() {

    private val customViewModel: AboutViewModel by viewModels()
    private lateinit var binding: AboutFragmentBinding

    override fun provideViewModel() = customViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AboutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.about_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_libraries -> {
            showLicensesDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showLicensesDialog() = LicensesDialog.Builder(requireContext())
        .setTitle(getString(R.string.third_party_libraries))
        .setNotices(R.raw.notices)
        .setIncludeOwnLicense(true)
        .build()
        .show()

    override fun render(viewState: AboutViewState) {
        binding.viewState = viewState
    }
}
