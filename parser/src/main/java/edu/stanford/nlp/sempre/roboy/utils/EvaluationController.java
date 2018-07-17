package edu.stanford.nlp.sempre.roboy.utils;

import fig.basic.*;
import fig.exec.Execution;

import java.util.*;

public class EvaluationController extends Evaluation{
    public void logStats(String prefix) {
        if(LogController.isINFO())super.logStats(prefix);
    }

}
