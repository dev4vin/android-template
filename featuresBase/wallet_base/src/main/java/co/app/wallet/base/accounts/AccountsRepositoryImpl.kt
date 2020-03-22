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

package co.app.wallet.base.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.app.wallet.base.data.DataScope
import co.app.wallet.domain.accounts.AccountsRepository
import co.app.wallet.domain.accounts.WalletAccount
import promise.commons.AndroidPromise
import promise.commons.data.log.LogUtil
import javax.inject.Inject
import promise.commons.model.List as PromiseList

@DataScope
class AccountsRepositoryImpl @Inject constructor(private val promise: AndroidPromise) :
    AccountsRepository {
    override fun getAllAccounts(): LiveData<List<WalletAccount>> {
        val mutableLiveData = MutableLiveData<List<WalletAccount>>()
        promise.execute({
            mutableLiveData.postValue(PromiseList.generate(2) {
                object : WalletAccount {
                    override fun name(): String = "Other account"
                    override fun amount(): Double = 50000.0
                }
            }
            )
            LogUtil.e(TAG, "accounts loaded: ")
        }, 1000)
        return mutableLiveData
    }

    companion object {
        val TAG: String = LogUtil.makeTag(AccountsRepositoryImpl::class.java)
    }
}