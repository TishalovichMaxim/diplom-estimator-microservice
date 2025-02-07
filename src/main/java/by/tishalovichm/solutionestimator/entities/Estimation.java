package by.tishalovichm.solutionestimator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estimations")
public class Estimation {

    @Id
    @Column(name = "solution_id")
    private Long solutionId;

    private String message;

    @Column(insertable = false)
    private LocalTime time;

    @OneToOne(mappedBy = "estimation")
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "estimation_res_id")
    private EstimationResult estimationResult;

    @Override
    public String toString() {
        return "Estimation{" +
                "solutionId=" + solutionId +
                ", solution(problem id)=" + solutionId +
                ", estimationResult=" + estimationResult.getName() +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }

}
