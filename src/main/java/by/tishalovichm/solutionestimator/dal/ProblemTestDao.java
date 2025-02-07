package by.tishalovichm.solutionestimator.dal;

import by.tishalovichm.solutionestimator.entities.ProblemTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemTestDao extends CrudRepository<ProblemTest, Long> {

    @Query(value = "SELECT pt FROM ProblemTest pt WHERE pt.problem.id = :problem_id")
    List<ProblemTest> findByProblemId(@Param("problem_id") Long problemId);

}
