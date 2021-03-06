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
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import dagger.android.DaggerService

@SuppressLint("Registered")
abstract class BindService<T : Service> : DaggerService() {

    inner class LocalBinder : Binder() {
        val service: T
            get() = getService()
    }

    abstract fun getService(): T

    lateinit var app: App

    override fun onCreate() {
        super.onCreate()
        app = application as App
    }

    final override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    final override fun onUnbind(intent: Intent?): Boolean = super.onUnbind(intent)

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    override fun onBind(intent: Intent): IBinder = LocalBinder()
}