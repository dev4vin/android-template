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

package co.app.app

import co.app.App
import co.app.common.account.AccountManager
import co.app.domain.Settings
import co.base.AppDatabase
import co.base.AppScope
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.disposables.CompositeDisposable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import promise.commons.AndroidPromise
import promise.commons.pref.Preferences

/**
 * Application component
 */
@Component(
    dependencies = [
        BaseComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        UIBuildersModule::class
    ]
)
@ApplicationScope
interface AppComponent : AndroidInjector<App> {

    fun promise(): AndroidPromise

    fun provideAccountManager(): AccountManager

    fun compositeDisposable(): CompositeDisposable

    fun gson(): Gson

    fun settings(): Settings

    fun okHttpClient(): OkHttpClient

    fun apiUrl(): HttpUrl

    fun preferences(): Preferences

    fun appDatabase(): AppDatabase


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App,
            baseComponent: BaseComponent
        ): AppComponent
    }
}