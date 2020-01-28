/*
 * Copyright 2017, Nesst
 * Licensed under the Apache License, Version 2.0, "Nesst Inc".
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nesstbase.session

import com.nesst.appdomain.session.LoginRequest
import com.nesst.appdomain.session.RegistrationRequest
import retrofit2.Call
import retrofit2.http.*

/**
 * authentication api
 *
 */

interface AuthApi {

    @POST("login")
    @Headers("HOST: AUTH")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<String>

    /**
     * reset password api
     *
     * @param identifier phone or email
     * @return
     */
    @FormUrlEncoded
    @POST("reset")
    @Headers("HOST: AUTH")
    fun resetPassword(@Field("identifier") identifier: String): Call<String>

    /**
     * registration api
     *
     * @param params email, names, phone, password
     * @return
     */
    @FormUrlEncoded
    @POST("register")
    @Headers("HOST: AUTH")
    fun register(@Body registrationRequest: RegistrationRequest): Call<String>

}