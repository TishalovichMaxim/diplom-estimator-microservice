package by.tishalovichm.solutionestimator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "sending_time", insertable = false)
    private LocalTime sendingTime;

    @Column(name = "prog_lang_id")
    private Long progLangId;

    private String code;

    @OneToOne
    @PrimaryKeyJoinColumn(referencedColumnName = "solution_id")
    private Estimation estimation;

}
