package by.tishalovichm.solutionestimator.services.estimators;

import by.tishalovichm.solutionestimator.dal.EstimationDao;
import by.tishalovichm.solutionestimator.dal.SolutionDao;
import by.tishalovichm.solutionestimator.entities.EstimationResult;
import by.tishalovichm.solutionestimator.dtos.req.ReqEstimationInfo;
import by.tishalovichm.solutionestimator.dtos.resp.RespEstimationInfo;
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
public class TestsRunnerService {

    private static final Logger logger = LoggerFactory.getLogger(TestsRunnerService.class);

    private final LanguageManager languageManager;

    private final SolutionDao solutionDao;

    private final EstimationDao estimationDao;

    private RespEstimationInfo getResp(
            Long estimationResId
    ) {

        return new RespEstimationInfo(
                estimationResId,
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
            return getResp(EstimationResult.SERVER_ERROR);
        }

        logger.debug("Path of code deploying: {}", path);

        for (int i = 0; i < info.tests().size(); i++) {
            var test = info.tests().get(i);
            String testOut;

            try {
                testOut = languageManager.execute(path, test.getInput());
            } catch (Exception e) {
                logger.debug("Error in code execution on test: {}", i);
                return getResp(EstimationResult.SERVER_ERROR);
            }

            testOut = testOut.replace("\\s", "");
            if (!test.getOutput().equals(testOut)) {
                logger.debug("Wrong result on test #{}", i);
                return getResp(EstimationResult.SERVER_ERROR);
            }
        }

        logger.debug("All tests succeeded");
        return getResp(EstimationResult.ACCEPTED);
    }

}
