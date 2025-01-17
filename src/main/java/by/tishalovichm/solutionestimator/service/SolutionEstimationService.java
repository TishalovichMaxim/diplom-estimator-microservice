package by.tishalovichm.solutionestimator.service;

import by.tishalovichm.solutionestimator.model.EstimationStatus;
import by.tishalovichm.solutionestimator.model.ReqEstimationInfo;
import by.tishalovichm.solutionestimator.model.RespEstimationInfo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;

@Setter
@Service
@RequiredArgsConstructor
public class SolutionEstimationService {

    private static final Logger logger = LoggerFactory.getLogger(SolutionEstimationService.class);

    private final LanguageManager languageManager;

    private RespEstimationInfo getResp(
            ReqEstimationInfo info,
            EstimationStatus status
    ) {
        return new RespEstimationInfo(
                info.userId(),
                info.problemId(),
                status,
                ""
        );
    }

    public RespEstimationInfo estimate(
            ReqEstimationInfo info
    ) {
        Path path;
        try {
            path = languageManager.deployCode(info.code());
        } catch (IOException e) {
            logger.debug("Error in code deploying: {}", e.getMessage());
            return getResp(info, EstimationStatus.INTERNAL_ERROR);
        }

        logger.debug("Path of code deploying: {}", path);

        for (int i = 0; i < info.tests().size(); i++) {
            var test = info.tests().get(i);
            String testOut;

            try {
                testOut = languageManager.execute(path, test.getInput());
            } catch (Exception e) {
                logger.debug("Error in code execution on test: {}", i);
                return getResp(info, EstimationStatus.INTERNAL_ERROR);
            }

            testOut = testOut.replace("\\s", "");
            if (!test.getOutput().equals(testOut)) {
                logger.debug("Wrong result on test #{}", i);
                return getResp(info, EstimationStatus.WRONG_RESULT);
            }
        }

        logger.debug("All tests succeeded");
        return getResp(info, EstimationStatus.SUCCESS);
    }

}
