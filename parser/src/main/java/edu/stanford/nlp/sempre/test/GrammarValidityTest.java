package edu.stanford.nlp.sempre.test;

import edu.stanford.nlp.sempre.*;

import org.testng.annotations.Test;

import edu.stanford.nlp.sempre.roboy.utils.LogController;

import java.util.*;
import java.nio.file.*;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Attempt to load all grammars to test for validity.
 *
 * @author Yushi Wang
 */

public class GrammarValidityTest {
  private String[] dataPaths = new String[] {"data/", "freebase/", "tables/", "regex/", "overnight/"};

  @Test(groups = {"grammar"})
  public void readGrammars() {
    try {
      List<String> successes = new ArrayList<>(), failures = new ArrayList<>();
      for (String dataPath : dataPaths) {
        Files.walk(Paths.get(dataPath)).forEach(filePath -> {
          try {
            if (filePath.toString().toLowerCase().endsWith(".grammar")) {
              Grammar test = new Grammar();
              LogController.logs("Reading grammar file: %s", filePath.toString());
              test.read(filePath.toString());
              LogController.logs("Finished reading", filePath.toString());
              successes.add(filePath.toString());
            }
          }
          catch (Exception ex) {
            failures.add(filePath.toString());
          }
        });
      }
      LogController.begin_track("Following grammar tests passed:");
      for (String path : successes)
        LogController.logs("%s", path);
      LogController.end_track();
      LogController.begin_track("Following grammar tests failed:");
      for (String path : failures)
        LogController.logs("%s", path);
      LogController.end_track();
      assertEquals(0, failures.size());
    }
    catch (Exception ex) {
      LogController.logs(ex.toString());
    }
  }
}
