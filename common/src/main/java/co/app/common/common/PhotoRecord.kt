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

package co.app.common.common

import android.annotation.SuppressLint
import co.app.common.photo.Photo
import promise.database.Entity
import promise.database.Index
import promise.db.ActiveRecord

@SuppressLint("ParcelCreator")
@Entity(tableName = "app_photos")
class PhotoRecord @SuppressWarnings("unused") constructor() : ActiveRecord<PhotoRecord>() {

    @Index
    var refId: String? = null

    @Index
    var refName: String? = null

    var url: String? = null
    var type = "OFFLINE"

    fun toPhoto(): Photo = Photo()
        .url(url).type(type)

    override fun getEntity(): PhotoRecord = this
}