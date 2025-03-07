// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.scheduler.job;

import org.apache.doris.scheduler.disruptor.TimerTaskDisruptor;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.Getter;

import java.util.UUID;

/**
 * This class represents a timer task that can be scheduled by a Netty timer.
 * When the timer task is triggered, it produces an event task using the Disruptor.
 * The event task contains the ID of the event and the ID of the task itself.
 */
@Getter
public class DorisTimerTask implements TimerTask {

    private final Long jobId;

    // more fields should be added here and record in feature
    private final Long taskId = UUID.randomUUID().getMostSignificantBits();

    private final Long startTimestamp;

    private final TimerTaskDisruptor timerTaskDisruptor;

    public DorisTimerTask(Long jobId, Long startTimestamp, TimerTaskDisruptor timerTaskDisruptor) {
        this.jobId = jobId;
        this.startTimestamp = startTimestamp;
        this.timerTaskDisruptor = timerTaskDisruptor;
    }

    @Override
    public void run(Timeout timeout) {
        if (timeout.isCancelled()) {
            return;
        }
        timerTaskDisruptor.tryPublish(jobId);
    }
}
