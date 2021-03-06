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

package co.app.auth.register

import androidx.lifecycle.MutableLiveData
import co.app.BaseViewModel
import co.app.UIResult
import co.app.auth.domain.Session
import promise.commons.AndroidPromise
import promise.commons.data.log.LogUtil

class RegistrationViewModel(private val session: Session, private val promise: AndroidPromise) : BaseViewModel() {

    val TAG = LogUtil.makeTag(RegistrationViewModel::class.java)
    private val _result = MutableLiveData<UIResult<*>>()

    /*fun createAccountClicked(v: View) {
        if (registrationForm.validate(null)) registrationForm.executeNext(this)
        else notifyChanges()
    }*/

}
