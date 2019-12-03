package projectrastov.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LettersService {

    private final Charset DEFAULT_CHARSET = UTF_8;

    public LettersService() {
    }

    public String getFileContent(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(
                Paths.get(
                        this.getClass().getResource("/" + fileName + ".js").toURI()), DEFAULT_CHARSET)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException | URISyntaxException |NullPointerException e) {
            contentBuilder.append(e.getMessage()).append("\n");
        }
        return contentBuilder.toString();
    }

    public String getFileContentDev(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(
                Paths.get(
                        this.getClass().getResource("/" + fileName + ".min.js").toURI()), DEFAULT_CHARSET)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException | URISyntaxException |NullPointerException e) {
            try (Stream<String> stream = Files.lines(
                    Paths.get(
                            this.getClass().getResource("/" + fileName + ".js").toURI()), DEFAULT_CHARSET)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            } catch (IOException | URISyntaxException |NullPointerException ex) {
                contentBuilder.append(e.getMessage()).append("\n");
            }
        }
        return contentBuilder.toString();
    }
}

