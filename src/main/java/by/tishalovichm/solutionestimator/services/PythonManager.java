package by.tishalovichm.solutionestimator.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class PythonManager implements LanguageManager {

    @Value("${python-interpreter}")
    private String pythonInterpreter;

    @Value("${file-name}")
    private String fileName;

    @Value("${file-extension}")
    private String fileExtension;

    private Path createFileUnderTest(
            String code
    ) throws IOException {
        Path tempFile = Files.createTempFile(fileName, "." + fileExtension);
        Files.writeString(tempFile, code);
        return tempFile;
    }

    private String executePython(
            Path scriptPath,
            String input
    ) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                pythonInterpreter,
                scriptPath.toAbsolutePath().toString()
        );
        Process process = builder.start();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(input);
            writer.flush();
        }

        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.readLine();
        }

        process.waitFor();
        return output;
    }

    @Override
    public Path deployCode(String code) throws IOException {
        return createFileUnderTest(code);
    }

    @Override
    public String execute(Path path, String input) throws InterruptedException, IOException {
        return executePython(path, input);
    }

}
