package by.tishalovichm.solutionestimator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estimation_results")
public class EstimationResult {

    public static final Long ACCEPTED = 0L;

    public static final Long TEST_ERROR = 1L;

    public static final Long SERVER_ERROR = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
