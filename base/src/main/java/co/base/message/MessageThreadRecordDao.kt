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

import promise.db.PromiseDatabase


interface MessageThreadRecordDao {
//    @Query("SELECT * from chat_users")
//    @Transaction
    fun getMessageThreads(): List<ChatUserRecordToChatMessageRecordsOneToManyRelationship>?
}

class MessageThreadRecordDaoImpl(private val promiseDatabase: PromiseDatabase): MessageThreadRecordDao {
    override fun getMessageThreads(): List<ChatUserRecordToChatMessageRecordsOneToManyRelationship>? =
        promiseDatabase.tableOf(ChatUserRecord::class.java).findAll().map {
            ChatUserRecordToChatMessageRecordsOneToManyRelationship().apply {
                chatUser = it
                chatMessages = promiseDatabase.tableOf(ChatMessageRecord::class.java)
                    .findAll(ChatMessageRecordsTable.senderIdColumn.with(it.userId!!.id))
            }
        }
}