package by.tishalovichm.solutionestimator.dal;

import by.tishalovichm.solutionestimator.entities.Solution;
import org.springframework.data.repository.CrudRepository;

public interface SolutionDao extends CrudRepository<Solution, Long> {
}
