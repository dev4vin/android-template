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

import co.app.common.search.Search
import co.app.common.search.SearchDatabase
import promise.commons.model.List
import promise.model.AbstractAsyncIDataStore
import promise.model.AbstractSyncIDataStore

const val SEARCH = "SEARCH"

class SyncSearchRepo(private val searchDatabase: SearchDatabase) : AbstractSyncIDataStore<Search>() {
    override fun save(t: List<out Search>, args: Map<String, Any?>?): Any? {
        return searchDatabase.save(t)
    }

    override fun save(t: Search, args: Map<String, Any?>?): Search {
        searchDatabase.save(t)
        return t
    }

}

class AsyncSearchRepo(private val searchDatabase: SearchDatabase): AbstractAsyncIDataStore<Search>() {
    override fun findAll(
        res: (List<out Search>?) -> Unit,
        err: ((Exception) -> Unit)?,
        args: Map<String, Any?>?
    ) {
        if (args != null && args.containsKey(SEARCH)) {
            res(List(searchDatabase.recentSearches(args[SEARCH] as Search)))
        }
        else super.findAll(res, err, args)
    }
}

