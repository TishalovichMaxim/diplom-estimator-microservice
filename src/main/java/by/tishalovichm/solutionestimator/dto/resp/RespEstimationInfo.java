package by.tishalovichm.solutionestimator.dto.resp;

public record RespEstimationInfo (
    Long estimationResId,
    String message
) {
    public RespEstimationInfo() {
        this(null, null);
    }
}
