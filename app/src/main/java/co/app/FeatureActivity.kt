/*
 * Copyright 2020, {{App}}
 * Licensed under the Apache License, Version 2.0, "{{App}} Inc".
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.app

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.app.attachment.AttachmentModel
import co.app.attachment.AttachmentPicker
import co.app.common.Attachment
import dagger.android.support.DaggerAppCompatActivity
import promise.commons.AndroidPromise
import promise.commons.model.List
import promise.location.PromiseLocation

/**
 * the base activity for all activities
 *
 */
@SuppressLint("Registered")
open class FeatureActivity : AppCompatActivity() {
    /**
     * adds the back button
     *
     */
    private var pickerDialog: AttachmentPicker? = null

    private lateinit var promiseLocation: PromiseLocation

    fun addBackButton() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun requestFlatAppBar() {
        val actionBar = supportActionBar
        actionBar?.elevation = 0f
    }

    open fun onLocationAcquired(location: Location) {}

    fun requestAttachment(requestType: Int) {
        when (requestType) {
            ATTACHMENT_TYPE_PHOTO -> {
                pickerDialog = AttachmentPicker.Builder()
                    .setTitle("Pick a photo")
                    .setListType(AttachmentPicker.TYPE_GRID, 3)
                    .setItems(
                        List.fromArray(
                            AttachmentModel.camera(), AttachmentModel.gallery()
                        )
                    )
                    .create()
            }
            ATTACHMENT_TYPE_DOCUMENT -> {
                pickerDialog = AttachmentPicker.Builder()
                    .setTitle("Pick a photo")
                    .setListType(AttachmentPicker.TYPE_GRID, 3)
                    .setItems(
                        List.fromArray(
                            AttachmentModel.files()
                        )
                    )
                    .create()
            }
            else -> throw IllegalArgumentException("arg not allowed $requestType")
        }
        pickerDialog?.setPickerCloseListener {
            onAttachmentAcquired(it)
        }
        pickerDialog?.show(supportFragmentManager, app.appComponent.settings(), "picker")

    }

    open fun onAttachmentAcquired(attachment: Attachment) {}

    /**
     * the main application for providing app component
     */
    lateinit var app: App

    /**
     * promise for execution of back ground threads and results on the ui thread
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as App
    }

    fun requestLocation() {
        promiseLocation = PromiseLocation.with(this)
        promiseLocation.location().once().start {
            onLocationAcquired(location = it)
        }
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
    fun executeBeforeAfterOnUi(
        promise: AndroidPromise,
        before: () -> Unit,
        after: () -> Unit,
        wait: Long? = null
    ) {
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
    fun executeBeforeAfter(
        promise: AndroidPromise,
        before: () -> Unit,
        after: () -> Unit,
        wait: Long? = null
    ) {
        promise.execute(before)
        promise.execute(after, wait ?: 500)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        pickerDialog?.onPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val ATTACHMENT_TYPE_PHOTO = 0x101
        const val ATTACHMENT_TYPE_DOCUMENT = 0x102
    }
}