package by.tishalovichm.solutionestimator.services.estimators;

import java.io.IOException;
import java.nio.file.Path;

public interface LanguageManager {

    Path deployCode(String code) throws IOException;

    String execute(Path path, String input) throws InterruptedException, IOException;

}
