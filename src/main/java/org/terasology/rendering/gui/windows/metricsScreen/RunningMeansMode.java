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

package org.terasology.rendering.gui.windows.metricsScreen;

import org.terasology.monitoring.PerformanceMonitor;
import org.terasology.rendering.gui.widgets.UILabel;

import java.util.List;

/**
 * @author Immortius
 */
final class RunningMeansMode extends MetricsMode {

    public RunningMeansMode() {
        super("Running means", true, true);
    }

    @Override
    public void updateLines(List<UILabel> lines) {
        displayMetrics(PerformanceMonitor.getRunningMean(), lines);
    }
}
