package by.tishalovichm.solutionestimator;

import by.tishalovichm.solutionestimator.model.ProblemTest;
import by.tishalovichm.solutionestimator.model.ReqEstimationInfo;
import by.tishalovichm.solutionestimator.service.SolutionEstimationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SolutionEstimatorApplication implements CommandLineRunner {

	private final SolutionEstimationService solutionEstimationService;

	public static void main(String[] args) {
		SpringApplication.run(SolutionEstimatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ProblemTest> tests = List.of(
				new ProblemTest("1 2", "3"),
				new ProblemTest("3 4", "7"),
				new ProblemTest("-100 1234", "1134")
		);

		String code = """
			a, b = list(map(int, input().split()))
			print(a + b)
			""";

		var reqInfo = new ReqEstimationInfo(
				1L,
				33L,
				code,
				tests
		);
		var res = solutionEstimationService.estimate(reqInfo);

		System.out.println(res);
	}

}
