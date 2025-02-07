package by.tishalovichm.solutionestimator.dto.req;

import by.tishalovichm.solutionestimator.entities.ProblemTest;

import java.util.List;

public record ReqEstimationInfo(
    String code,
    List<ProblemTest> tests
) {

    public ReqEstimationInfo() {
        this(null, null);
    }

}
