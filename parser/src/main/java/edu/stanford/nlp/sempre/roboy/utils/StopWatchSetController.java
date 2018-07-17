package edu.stanford.nlp.sempre.roboy.utils;

import fig.basic.*;

public class StopWatchSetController extends StopWatchSet {
    public static synchronized void logStats() {
        if(LogController.isINFO()) StopWatchSet.logStats();
    }
}
