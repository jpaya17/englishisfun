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

package com.jpaya.englishisfun.home

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jpaya.englishisfun.R

/**
 * View model responsible for preparing and managing the data for [HomeFragment].
 *
 * @see ViewModel
 */
class HomeViewModel @ViewModelInject constructor() : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState>
        get() = _state

    private val navFragmentsIds = setOf(
        R.id.abbreviations_list_fragment
    )

    /**
     * Navigation controller add destination changed listener.
     *
     * @param navController Navigation manager.
     */
    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (navFragmentsIds.contains(destination.id)) {
                _state.postValue(HomeViewState.NavigationScreen)
            } else {
                _state.postValue(HomeViewState.FullScreen)
            }
        }
    }

    fun authenticate(activity: Activity) {
/*        firebaseAuth.signInAnonymously().addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI showing the different application menus
                _state.postValue(HomeViewState.NavigationScreen)
            } else {
                // If sign in fails, display a message to the user.
                // Log.w(TAG, "signInAnonymously:failure", task.exception)
                // Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                // updateUI(null)
            }
        }
    }
 */
    }
}
