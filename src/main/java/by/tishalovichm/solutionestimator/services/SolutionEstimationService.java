package by.tishalovichm.solutionestimator.services;

import by.tishalovichm.solutionestimator.dal.EstimationDao;
import by.tishalovichm.solutionestimator.dal.EstimationResultDao;
import by.tishalovichm.solutionestimator.dal.ProblemTestDao;
import by.tishalovichm.solutionestimator.dal.SolutionDao;
import by.tishalovichm.solutionestimator.entities.ProblemTest;
import by.tishalovichm.solutionestimator.dtos.req.ReqEstimationInfo;
import by.tishalovichm.solutionestimator.dtos.resp.RespEstimationInfo;
import by.tishalovichm.solutionestimator.entities.Estimation;
import by.tishalovichm.solutionestimator.entities.EstimationResult;
import by.tishalovichm.solutionestimator.entities.Solution;
import by.tishalovichm.solutionestimator.services.estimators.TestsRunnerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionEstimationService {

    private final SolutionDao solutionDao;

    private final EstimationDao estimationDao;

    private final EstimationResultDao estimationResultDao;

    private final TestsRunnerService estimationService;

    private final ProblemTestDao problemTestDao;

    private static final Logger logger = LoggerFactory.getLogger(SolutionEstimationService.class);

    private Solution saveSolution(
            Solution solution
    ) throws Exception {

        Solution resSolution;

        try {
            resSolution = solutionDao.save(solution);
        } catch (Exception e) {
            logger.debug("Error in saving solution: {}", e.getMessage());
            throw e;
        }

        logger.debug("Solution saved successfully with id = {} ", resSolution.getId());

        return resSolution;
    }

    private List<ProblemTest> getProblemTests(Long problemId) throws Exception {
        return problemTestDao.findByProblemId(problemId);
    }

    private RespEstimationInfo runTests(
            String code,
            List<ProblemTest> tests
    ) throws Exception {

        return estimationService.estimate(new ReqEstimationInfo(code, tests));
    }

    private void saveEstimation(
            Long solutionId,
            Long estimationResultId,
            String message
    ) throws Exception {
        try {
            Solution solution = solutionDao
                    .findById(solutionId)
                    .orElseThrow();

            EstimationResult estimationResult = estimationResultDao
                    .findById(estimationResultId)
                    .orElseThrow();

            Estimation estimation = Estimation.builder()
                    .solutionId(solution.getId())
                    .solution(solution)
                    .message(message)
                    .estimationResult(estimationResult)
                    .build();

            estimationDao.save(estimation);
        } catch (Exception e) {
            logger.debug("Error in saving estimation: {}", e.getMessage());
            throw e;
        }

        logger.debug("Estimation saved successfully");
    }

    @SneakyThrows
    public void processSubmission(
            Solution solution
    ) {
        Solution savedSolution = saveSolution(solution);

        List<ProblemTest> tests = getProblemTests(solution.getProblemId());

        RespEstimationInfo estimationInfo = runTests(solution.getCode(), tests);

        saveEstimation(savedSolution.getId(), estimationInfo.estimationResId(), estimationInfo.message());
    }

}
