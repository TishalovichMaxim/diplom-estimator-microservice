package by.tishalovichm.solutionestimator.model;

import java.util.List;

public record ReqEstimationInfo(
    Long userId,
    Long problemId,
    String code,
    List<ProblemTest> tests
) {
    public ReqEstimationInfo() {
        this(null, null, null, null);
    }
}
