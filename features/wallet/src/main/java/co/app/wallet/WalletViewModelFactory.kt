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

package co.app.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.app.common.account.UserAccount
import co.app.wallet.domain.accounts.AccountsRepository
import promise.commons.AndroidPromise
import javax.inject.Inject

/**
 * ViewModel provider factory to instantiate RegisterViewModel.
 * Required given RegisterViewModel has a non-empty constructor
 */
class WalletViewModelFactory @Inject constructor(var userAccount: UserAccount?) : ViewModelProvider.Factory {

    @Inject
    lateinit var promise: AndroidPromise

    @Inject
    lateinit var accountsRegistrar: AccountsRepository

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            return WalletViewModel(
                userAccount = userAccount,
                promise = promise,
                accountsRepository = accountsRegistrar
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
