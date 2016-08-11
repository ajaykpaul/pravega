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

package com.emc.pravega.service.contracts;

/**
 * An Exception that is related to a particular Container.
 */
public abstract class StreamSegmentException extends StreamingException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String streamSegmentName;

    /**
     * Creates a new instance of the StreamSegmentException class.
     * @param streamSegmentName The name of the StreamSegment.
     * @param message The message for this exception.
     */
    public StreamSegmentException(String streamSegmentName, String message) {
        this(streamSegmentName, message, null);
    }

    /**
     * Creates a new instance of the StreamSegmentException class.
     * @param streamSegmentName The name of the StreamSegment.
     * @param message The message for this exception.
     * @param cause The causing exception.
     */
    public StreamSegmentException(String streamSegmentName, String message, Throwable cause) {
        super(String.format("%s (%s).", message, streamSegmentName), cause);
        this.streamSegmentName = streamSegmentName;
    }

    /**
     * Gets a value indicating the StreamSegment Name.
     * @return
     */
    public String getStreamSegmentName() {
        return this.streamSegmentName;
    }
}