package by.tishalovichm.solutionestimator.controller;

import by.tishalovichm.solutionestimator.model.ReqEstimationInfo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaController {

    @KafkaListener(id = "1", topics = "raw-solutions")
    public void listen(ReqEstimationInfo info) {
        System.out.println(info);
    }

}
