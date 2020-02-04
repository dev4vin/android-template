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

package com.app.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.domain.session.Account
import com.app.domain.session.Session
import promise.commons.Promise
import promise.commons.model.List

class DashboardViewModel(private val session: Session, private val promise: Promise) : ViewModel() {

    private val _accountsResult = MutableLiveData<List<Account>>()

    val accountsResult: LiveData<List<Account>> = _accountsResult

    fun fetchAccounts() {
        _accountsResult.value = List.generate(3) { Account("Peter", "v@gmail.com") }
    }

}
