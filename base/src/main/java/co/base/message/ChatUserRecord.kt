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

package co.base.message

import co.app.common.account.AppUser
import co.app.common.ID
import co.app.common.photo.PhotoDatabase
import co.app.common.common.PhotoRecord
import promise.commons.model.Identifiable
import promise.database.Entity
import promise.database.HasOne
import promise.database.PrimaryKeyAutoIncrement

@Entity(tableName = "chat_users")
class ChatUserRecord @SuppressWarnings("unused") constructor(): Identifiable<Int> {

    var userId: ID? = null

    var userName: String? = null

    @HasOne
    var photoRecord: PhotoRecord? = null

    @PrimaryKeyAutoIncrement
    var uid: Int = 0

    fun toChatUser(photoRecordDao: PhotoDatabase): AppUser =
        AppUser(
            userId!!,
            userName,
            photo = photoRecordDao.getPhotoByRef("chat_user",userId!!)
        )

    override fun getId(): Int = this.uid

    override fun setId(t: Int) {
        this.uid = t;
    }

}