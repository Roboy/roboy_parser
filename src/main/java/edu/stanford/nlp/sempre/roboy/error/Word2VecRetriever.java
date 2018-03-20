package edu.stanford.nlp.sempre.roboy.error;

import edu.stanford.nlp.sempre.Derivation;
import edu.stanford.nlp.sempre.SimpleLexicon;
import edu.stanford.nlp.sempre.roboy.ErrorInfo;
import edu.stanford.nlp.sempre.roboy.utils.SparqlUtils;
import edu.stanford.nlp.sempre.roboy.lexicons.word2vec.Word2vec;

import java.util.*;

import com.google.gson.Gson;
import fig.basic.LogInfo;

/**
 * Word2Vec to resolve underspecified types in the lexicon
 *
 * @author emlozin
 */
public class Word2VecRetriever extends KnowledgeRetriever {
    public static Gson gson = new Gson();

    private SparqlUtils sparqlUtil = new SparqlUtils();

    private Map<String, String> results;
    public static double threshold;
    private Word2vec vec;

    public Word2VecRetriever(Word2vec vec){
        this.vec = vec;
        this.results = new HashMap<>();
    }

    public ErrorInfo analyze(Derivation dev) {
        ErrorInfo errorInfo = new ErrorInfo();
        Map<String,String> results = new HashMap();
        String unknown = new String();
        String formula = dev.getFormula().toString();
        while (formula.contains("Open")){
            int start = formula.indexOf("Open")+"Open".length();
            int end = formula.indexOf("''",start);
            if (start > formula.length() || start < 0 || end < 0 ||end > formula.length())
                return errorInfo;
            unknown = formula.substring(start,end);
            String entity = unknown.substring(unknown.indexOf("'")+1);
            List<String> known_words= new ArrayList<String>(SimpleLexicon.getSingleton().lookup_type(entity));
            List<String> candidate = this.vec.getBest(entity,known_words);
            for (String c: candidate){
                Map<String,String> record = new HashMap();
                record.put("Label",c);
                record.put("Refcount",Double.toString(this.vec.getSimilarity(entity,c)));
                List<SimpleLexicon.Entry> entries = SimpleLexicon.getSingleton().lookup(c);
                for (SimpleLexicon.Entry entry:entries) {
                    record.put("URI",entry.formula.toString());
                    if (errorInfo.getCandidates().containsKey(entity)){
                        errorInfo.getCandidates().get(entity).add(gson.toJson(record));
                        //LogInfo.logs("Word2Vec: %s",gson.toJson(record));
                    }
                    else{
                        errorInfo.getCandidates().put(entity, new ArrayList<>(Arrays.asList(gson.toJson(record))));
                        //LogInfo.logs("Word2Vec: %s",gson.toJson(record));
                    }
                }
            }
            formula = formula.substring(end);
        }
        return errorInfo;
    }

}
