package by.tishalovichm.solutionestimator.dal;

import by.tishalovichm.solutionestimator.entities.EstimationResult;
import org.springframework.data.repository.CrudRepository;

public interface EstimationResultDao extends CrudRepository<EstimationResult, Long> {
}
