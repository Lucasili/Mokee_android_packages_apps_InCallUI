/*
 * Copyright (C) 2013 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.incallui;

import android.os.RemoteException;

import com.android.services.telephony.common.AudioMode;
import com.android.services.telephony.common.ICallCommandService;

/**
 * Main interface for phone related commands.
 */
public class CallCommandClient {

    private static CallCommandClient sInstance;

    public static synchronized CallCommandClient getInstance() {
        if (sInstance == null) {
            sInstance = new CallCommandClient();
        }
        return sInstance;
    }

    private ICallCommandService mCommandService;

    private CallCommandClient() {
    }

    public void setService(ICallCommandService service) {
        mCommandService = service;
    }

    public void answerCall(int callId) {
        Log.i(this, "answerCall: " + callId);
        if (mCommandService == null) {
            Log.e(this, "Cannot answer call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.answerCall(callId);
        } catch (RemoteException e) {
            Log.e(this, "Error answering call.", e);
        }
    }

    public void rejectCall(int callId, boolean rejectWithMessage, String message) {
        Log.i(this, "rejectCall: " + callId + ", with rejectMessage? " + rejectWithMessage);
        if (mCommandService == null) {
            Log.e(this, "Cannot reject call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.rejectCall(callId, rejectWithMessage, message);
        } catch (RemoteException e) {
            Log.e(this, "Error rejecting call.", e);
        }
    }

    public void disconnectCall(int callId) {
        Log.i(this, "disconnect Call: " + callId);
        if (mCommandService == null) {
            Log.e(this, "Cannot disconnect call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.disconnectCall(callId);
        } catch (RemoteException e) {
            Log.e(this, "Error answering call.", e);
        }
    }

    public void mute(boolean onOff) {
        Log.i(this, "mute: " + onOff);
        if (mCommandService == null) {
            Log.e(this, "Cannot mute call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.mute(onOff);
        } catch (RemoteException e) {
            Log.e(this, "Error muting phone.", e);
        }
    }

    public void hold(int callId, boolean onOff) {
        Log.i(this, "hold call(" + onOff + "): " + callId);
        if (mCommandService == null) {
            Log.e(this, "Cannot hold call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.hold(callId, onOff);
        } catch (RemoteException e) {
            Log.e(this, "Error holding call.", e);
        }
    }

    public void merge() {
        Log.i(this, "merge calls");
        if (mCommandService == null) {
            Log.e(this, "Cannot merge call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.merge();
        } catch (RemoteException e) {
            Log.e(this, "Error merging calls.", e);
        }
    }

    public void swap() {
        Log.i(this, "swap active/hold calls");
        if (mCommandService == null) {
            Log.e(this, "Cannot swap call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.swap();
        } catch (RemoteException e) {
            Log.e(this, "Error merging calls.", e);
        }
    }

    public void addCall() {
        Log.i(this, "add a new call");
        if (mCommandService == null) {
            Log.e(this, "Cannot add call; CallCommandService == null");
            return;
        }
        try {
            mCommandService.addCall();
        } catch (RemoteException e) {
            Log.e(this, "Error merging calls.", e);
        }
    }

    public void setAudioMode(int mode) {
        Log.i(this, "Set Audio Mode: " + AudioMode.toString(mode));
        if (mCommandService == null) {
            Log.e(this, "Cannot set audio mode; CallCommandService == null");
            return;
        }
        try {
            mCommandService.setAudioMode(mode);
        } catch (RemoteException e) {
            Log.e(this, "Error setting speaker.", e);
        }
    }

    public void playDtmfTone(char digit, boolean timedShortTone) {
        if (mCommandService == null) {
            Log.e(this, "Cannot start dtmf tone; CallCommandService == null");
            return;
        }
        try {
            Log.v(this, "Sending dtmf tone " + digit);
            mCommandService.playDtmfTone(digit, timedShortTone);
        } catch (RemoteException e) {
            Log.e(this, "Error setting speaker.", e);
        }

    }

    public void stopDtmfTone() {
        if (mCommandService == null) {
            Log.e(this, "Cannot stop dtmf tone; CallCommandService == null");
            return;
        }
        try {
            Log.v(this, "Stop dtmf tone ");
            mCommandService.stopDtmfTone();
        } catch (RemoteException e) {
            Log.e(this, "Error setting speaker.", e);
        }
    }
}