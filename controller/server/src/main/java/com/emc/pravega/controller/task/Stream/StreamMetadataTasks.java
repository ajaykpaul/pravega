/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.emc.pravega.controller.task.Stream;

import com.emc.pravega.controller.store.host.HostControllerStore;
import com.emc.pravega.controller.store.stream.StreamMetadataStore;
import com.emc.pravega.controller.task.Task;
import com.emc.pravega.controller.task.TaskBase;
import com.emc.pravega.controller.store.stream.Segment;
import com.emc.pravega.stream.StreamConfiguration;
import org.apache.commons.lang.NotImplementedException;
import org.apache.curator.framework.CuratorFramework;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Collection of metadata update batch operations on stream.
 */
public class StreamMetadataTasks extends TaskBase {

    public StreamMetadataTasks(StreamMetadataStore streamMetadataStore, HostControllerStore hostControllerStore, CuratorFramework client) {
        super(streamMetadataStore, hostControllerStore, client);
    }

    /**
     * Scales stream segments.
     *
     * Annotation takes care of locking and unlocking the stream and persisting operation details, in case the operation
     * needs to be retried on node/process failure
     *
     * Effectively, annotation does the following, before executing the method body
     *
     * 1. Lock the path /stream/{streamName}
     * 2. Add the data for this operation at /stream/{streamName}/operation
     *
     * And, it does the following after executing the method body, in a finally block
     * 1. Delete the data for this operation at /stream/{streamName}/operation
     * 2. Unlock the path /stream/{streamName}
     *
     * @param scope
     * @param stream
     * @param sealedSegments
     * @param newRanges
     * @param scaleTimestamp
     * @return
     */
    @Task(name = "scaleStream")
    public CompletableFuture<List<Segment>> scale(String scope, String stream, List<Integer> sealedSegments, List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        Object[] params = {scope, stream, sealedSegments, newRanges, scaleTimestamp};
        return this.wrapper(scope, stream, Arrays.asList(params), () -> scaleBody(scope, stream, sealedSegments, newRanges, scaleTimestamp));
    }

    @Task(name = "createStream")
    public CompletableFuture<Boolean> createStream(String scope, String stream, StreamConfiguration config) {
        Object[] params = {scope, stream, config};
        return this.wrapper(scope, stream, Arrays.asList(params), () -> createStreamBody(scope, stream, config));
    }

    @Task(name = "updateConfig")
    public CompletableFuture<Boolean> updateStreamConfig(String scope, String stream, StreamConfiguration config) {
        Object[] params = {scope, stream, config};
        return this.wrapper(scope, stream, Arrays.asList(params), () -> updateStreamConfigBody(scope, stream, config));
    }

    @Task(name = "createTransaction")
    public CompletableFuture<String> createTx(String scope, String stream) {
        throw new NotImplementedException();
    }

    @Task(name = "dropTransaction")
    public CompletableFuture<Boolean> dropTx(String scope, String stream, String txId) {
        throw new NotImplementedException();
    }

    @Task(name = "commitTransaction")
    public CompletableFuture<Boolean> commitTx(String scope, String stream, String txId) {
        throw new NotImplementedException();
    }

    private CompletableFuture<List<Segment>> scaleBody(String scope, String stream, List<Integer> sealedSegments, List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp) {
        throw new NotImplementedException();
    }

    private CompletableFuture<Boolean> createStreamBody(String scope, String stream, StreamConfiguration config) {
        throw new NotImplementedException();
    }

    public CompletableFuture<Boolean> updateStreamConfigBody(String scope, String stream, StreamConfiguration config) {
        throw new NotImplementedException();
    }
}
