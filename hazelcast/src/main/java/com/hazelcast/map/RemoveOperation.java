/*
 * Copyright (c) 2008-2012, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.map;

import com.hazelcast.nio.Data;

public class RemoveOperation extends BaseRemoveOperation {

    public RemoveOperation(String name, Data dataKey, String txnId) {
        super(name, dataKey, txnId);
    }

    public RemoveOperation() {
    }

    public void beforeRun() {
        init();
    }

    public void doOp() {
        if (prepareTransaction()) {
            return;
        }
        prepareValue();
        if (record != null) {
            remove();
            store();
        }
    }

    @Override
    public String toString() {
        return "RemoveOperation{" + name + "}";
    }
}