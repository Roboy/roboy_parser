package edu.stanford.nlp.sempre.roboy.score;

import com.google.gson.Gson;
import edu.stanford.nlp.sempre.ContextValue;
import edu.stanford.nlp.sempre.roboy.ErrorInfo;
import edu.stanford.nlp.sempre.roboy.lexicons.word2vec.Word2vec;

import java.util.*;

/**
 * ResultScore creates a score based on result of the query
 *
 * @author emlozin
 */
public class ResultScore extends ScoringFunction {
    public static Properties prop = new Properties();   /**< Read properties */
    public static Gson gson = new Gson();               /**< Gson object */

    private final Word2vec vec;                         /**< Word2Vec handler */

    public Map<String, Map<String, Double>> current_score;   /**< Current calculated scores */

    /**
     * A constructor.
     * Initializes Word2Vec needed to calculate scores
     */
    public ResultScore(Word2vec vec){
        current_score = new HashMap<>();
        this.vec = vec;
    }

    /**
     * Scoring function.
     * Takes ErrorInfo as well as ContextValue objects and calculates score of each
     * candidate for unknown terms.
     */
    public ErrorInfo score(ErrorInfo errorInfo, ContextValue context){
        current_score.clear();
        for (String key: errorInfo.getCandidates().keySet()){
            Map<String, Double> key_scores = new HashMap<>();
            List<String> candidates = errorInfo.getCandidates().get(key);
            for (String candidate: candidates){
                double score = this.vec.getSimilarity(key,candidate);
                if (!Double.isNaN(score))
                    key_scores.put(candidate,score);
                else
                    key_scores.put(candidate,(double)-1);
            }
            current_score.put(key,key_scores);
        }
        return errorInfo;
    }

}
