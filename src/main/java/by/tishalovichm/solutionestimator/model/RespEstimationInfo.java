package by.tishalovichm.solutionestimator.model;

public record RespEstimationInfo (
    Long userId,
    Long problemId,
    EstimationStatus status,
    String message
) {
    public RespEstimationInfo() {
        this(null, null, null, null);
    }
}
