/*
 * Copyright 2017, Nesst
 * Licensed under the Apache License, Version 2.0, "Nesst Inc".
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nesst

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import promise.commons.Promise
import javax.inject.Inject

/**
 * the base activity for all activities
 *
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    /**
     * adds the back button
     *
     */
    fun addBackButton() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * the main application for providing app component
     */
    lateinit var app: App
    /**
     * promise for execution of back ground threads and results on the ui thread
     */
    @Inject
    lateinit var promise: Promise

    /**
     * initializes the app and injects promise instance
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as App
        DaggerUiComponent.builder()
            .appComponent(app.appComponent)
            .build().inject(this)
    }

    /**
     * removes the default elevation of app bar
     *
     * @param savedInstanceState
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
    }

    /**
     * handle  click of back button if its present
     *
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    /**
     * execute runnable before the after runnable on UI
     *
     * @param before first action to execute
     * @param after last action to execute
     * @param wait interval before executing after
     */
    fun executeBeforeAfterOnUi(before: () -> Unit, after: () -> Unit, wait: Long? = null) {
        promise.executeOnUi(before)
        promise.executeOnUi(after, wait ?: 500)
    }

    /**
     * execute runnable before the after runnable on background thread
     *
     * @param before first action to execute
     * @param after last action to execute
     * @param wait interval before executing after
     */
    fun executeBeforeAfter(before: () -> Unit, after: () -> Unit, wait: Long? = null) {
        promise.execute(before)
        promise.execute(after, wait ?: 500)
    }
}