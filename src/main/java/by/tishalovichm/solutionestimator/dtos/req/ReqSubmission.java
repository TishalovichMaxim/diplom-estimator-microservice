package by.tishalovichm.solutionestimator.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSubmission {

    private Long userId;

    private Long problemId;

    private String code;

}
