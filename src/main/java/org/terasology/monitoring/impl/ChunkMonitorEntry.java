/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.monitoring.impl;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.math.Vector3i;
import org.terasology.world.chunks.Chunk;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;

public class ChunkMonitorEntry {

    private static final Logger logger = LoggerFactory.getLogger(ChunkMonitorEntry.class);

    private final Vector3i pos;
    private LinkedList<WeakReference<Chunk>> chunks = new LinkedList<>();
    private LinkedList<ChunkMonitorEvent.BasicChunkEvent> events = new LinkedList<>();

    private void purge() {
        if (chunks.size() == 0) {
            return;
        }
        final Iterator<WeakReference<Chunk>> it = chunks.iterator();
        while (it.hasNext()) {
            final WeakReference<Chunk> w = it.next();
            if (w.get() == null) {
                it.remove();
            }
        }
    }

    public ChunkMonitorEntry(Vector3i pos) {
        this.pos = Preconditions.checkNotNull(pos, "The parameter 'pos' must not be null");
    }

    public Vector3i getPosition() {
        return new Vector3i(pos);
    }

    public Chunk getLatestChunk() {
        final WeakReference<Chunk> chunk = chunks.peekLast();
        if (chunk != null) {
            return chunk.get();
        }
        return null;
    }

    public void addChunk(Chunk value) {
        Preconditions.checkNotNull(value, "The parameter 'value' must not be null");
        Preconditions.checkArgument(pos.equals(value.getPos()), "Expected chunk for position {} but got position {} instead", pos, value.getPos());
        purge();
        chunks.add(new WeakReference<Chunk>(value));
        if (chunks.size() > 1) {
            logger.warn("Multiple chunks for position {} are registered ({})", pos, chunks.size());
        }
    }

    public void addEvent(ChunkMonitorEvent.BasicChunkEvent event) {
        Preconditions.checkNotNull(event, "The parameter 'event' must not be null");
        Preconditions.checkArgument(pos.equals(event.position), "Expected event for position {} but got position {} instead", pos, event.position);
        events.add(event);
    }
}
