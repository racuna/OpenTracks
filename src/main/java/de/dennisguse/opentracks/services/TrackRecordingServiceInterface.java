/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.dennisguse.opentracks.services;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.time.Duration;

import de.dennisguse.opentracks.content.data.Marker;
import de.dennisguse.opentracks.content.data.Track;
import de.dennisguse.opentracks.content.data.TrackPoint;
import de.dennisguse.opentracks.content.sensor.SensorDataSet;
import de.dennisguse.opentracks.services.handlers.GpsStatusValue;
import de.dennisguse.opentracks.services.sensors.BluetoothRemoteSensorManager;

/**
 * App's service.
 * This service is the process that actually records and manages tracks.
 */
public interface TrackRecordingServiceInterface {

    void startGps();

    void stopGps();

    Track.Id startNewTrack();

    void pauseCurrentTrack();

    void resumeCurrentTrack();

    void resumeTrack(Track.Id trackId);

    /**
     * End current track.
     *
     * @return the Track.Id of the track that is ended.
     */
    Track.Id endCurrentTrack();

    boolean isRecording();

    /**
     * Returns true if the current recording track is paused. Returns true if not recording.
     */
    boolean isPaused();

    /**
     * Gets the total time for the current recording track. Returns 0 if not recording.
     */
    Duration getTotalTime();

    Marker.Id insertMarker(String name, String category, String description, String photoUrl);

    /**
     * Gets the current sensor data.
     *
     * @return SensorDataSet object or null.
     */
    SensorDataSet getSensorData();

    /**
     * Gets the current altitude gain.
     *
     * @return altitude gain in m or null.
     */
    Float getAltitudeGain_m();

    /**
     * Gets the current altitude loss.
     *
     * @return altitude loss in m or null.
     */
    Float getAltitudeLoss_m();

    @VisibleForTesting
    void setRemoteSensorManager(BluetoothRemoteSensorManager remoteSensorManager);

    /**
     * Inserts a track point in the current recording track.
     * This is used for inserting special track points or for testing.
     *
     * @param trackPoint           the track point object to be inserted.
     * @param recordingGpsAccuracy recording GPS accuracy.
     */
    @Deprecated //REMOVE
    @VisibleForTesting
    void newTrackPoint(TrackPoint trackPoint, int recordingGpsAccuracy);

    void addListener(@NonNull TrackRecordingServiceStatus.Listener listener);

    GpsStatusValue getGpsStatus();
}
