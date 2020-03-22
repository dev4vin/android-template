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

package co.app.messaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.app.domain.message.MessageRepository
import promise.commons.AndroidPromise
import javax.inject.Inject

@ChatScope
class MessageViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Inject lateinit var messageRepository: MessageRepository

    @Inject
    lateinit var promise: AndroidPromise

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(MessagingViewModel::class.java) -> MessagingViewModel(
            messageRepository,
            promise
        ) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}