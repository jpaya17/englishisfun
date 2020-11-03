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

package com.jpaya.englishisfun.settings.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance
import com.jpaya.englishisfun.R
import com.jpaya.englishisfun.data.preferences.Preferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)

        findPreference<ListPreference>(getString(R.string.setting_appearance_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                viewModel.setAppearance(Appearance.valueOf(newValue as String))
                Preferences.putAppearance(newValue, requireContext().applicationContext)
                true
            }

        findPreference<Preference>(getString(R.string.setting_about_key))?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_settings_fragment_to_aboutFragment)
            true
        }
    }
}
