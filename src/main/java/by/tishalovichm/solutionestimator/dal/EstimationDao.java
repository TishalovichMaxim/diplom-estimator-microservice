package by.tishalovichm.solutionestimator.dal;

import by.tishalovichm.solutionestimator.entities.Estimation;
import org.springframework.data.repository.CrudRepository;

public interface EstimationDao extends CrudRepository<Estimation, Long> {
}
