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

package co.app.request.base

import co.app.request.base.product.ProductImpl
import co.app.request.domain.product.ProductsDatabase
import co.app.request.domain.service.ServicesDatabase
import promise.database.DatabaseEntity
import promise.db.FastDatabase
import promise.db.PromiseDatabase

@DatabaseEntity(
    persistableEntities = [
    ProductImpl::class
    ]
)
abstract class RequestDatabase(fastDatabase: FastDatabase): PromiseDatabase(fastDatabase), ProductsDatabase, ServicesDatabase {

}