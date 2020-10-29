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

package com.jpaya.englishisfun.splash.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jpaya.englishisfun.NavHostActivity
import com.jpaya.base.extensions.launchActivity
import com.jpaya.englishisfun.R

/**
 * This is the first Activity of the application.
 * This Activity is shown while the system is loading the resources needed to start the application.
 * Once all the resources are loaded, the application navigates to the Home of the application.
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({ launchActivity<NavHostActivity> {} }, SPLASH_DELAY)
    }
}
