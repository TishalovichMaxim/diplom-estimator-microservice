package by.tishalovichm.solutionestimator.dto.req;

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
