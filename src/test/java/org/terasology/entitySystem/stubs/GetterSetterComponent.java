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
package org.terasology.entitySystem.stubs;

import javax.vecmath.Vector3f;

import org.terasology.entitySystem.Component;

/**
 * @author Immortius <immortius@gmail.com>
 */
public class GetterSetterComponent implements Component {
    private Vector3f value = new Vector3f(0,0,0);

    public transient boolean getterUsed = false;
    public transient boolean setterUsed = false;

    public Vector3f getValue() {
        getterUsed = true;
        return value;
    }

    public void setValue(Vector3f value) {
        this.value = value;
        setterUsed = true;
    }
}
