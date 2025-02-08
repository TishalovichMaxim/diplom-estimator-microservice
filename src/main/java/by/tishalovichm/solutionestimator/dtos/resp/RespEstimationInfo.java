package by.tishalovichm.solutionestimator.dtos.resp;

public record RespEstimationInfo (
    Long estimationResId,
    String message
) {
    public RespEstimationInfo() {
        this(null, null);
    }
}
