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

package co.app.request.product

import android.annotation.SuppressLint
import android.os.Bundle
import co.app.BaseActivity
import co.app.FeatureActivity
import co.app.FeatureFragment
import co.app.request.R
import kotlinx.android.synthetic.main.activity_product_details.*

@SuppressLint("Registered")
class ProductDetailsActivity : FeatureActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        addBackButton()
        if (intent.hasExtra(SKU_ID)) {

        } else throw RuntimeException("SKU ID not present in report")

    }

    companion object {
        const val SKU_ID = "sku_id"
    }
}
