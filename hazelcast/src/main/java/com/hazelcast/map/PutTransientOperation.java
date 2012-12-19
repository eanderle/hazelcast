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

import com.hazelcast.impl.DefaultRecord;
import com.hazelcast.nio.Data;

public class PutTransientOperation extends BasePutOperation {

    public PutTransientOperation(String name, Data dataKey, Data value, String txnId, long ttl) {
        super(name, dataKey, value, txnId, ttl);
    }

    public PutTransientOperation() {
    }

    public void doOp() {
        if (prepareTransaction()) {
            return;
        }
        record = mapPartition.records.get(dataKey);
        if (record == null) {
            record = new DefaultRecord(getPartitionId(), dataKey, dataValue, -1, -1, mapService.nextId());
            mapPartition.records.put(dataKey, record);
        } else {
            record.setValueData(dataValue);
        }
        record.setActive();
        record.setDirty(true);
    }

    @Override
    public boolean returnsResponse() {
        return false;
    }

    @Override
    public String toString() {
        return "PutTransientOperation{" + name + "}";
    }
}