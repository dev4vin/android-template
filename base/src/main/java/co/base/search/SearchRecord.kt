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

package co.base.search

import android.os.Parcel
import android.os.Parcelable
import co.app.domain.search.Search
import promise.commons.model.Identifiable

class SearchRecord() : Parcelable, Identifiable<Int> {

    var query: String = ""

    fun toSearch(): Search = Search()

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<SearchRecord> {

        fun from(search: Search): SearchRecord = SearchRecord()
            .apply {
                query = search.query
            }

        override fun createFromParcel(parcel: Parcel): SearchRecord = SearchRecord(parcel)

        override fun newArray(size: Int): Array<SearchRecord?> = arrayOfNulls(size)
    }

    var uId = 0

    override fun getId(): Int = uId

    override fun setId(t: Int) {
        this.uId = t
    }
}