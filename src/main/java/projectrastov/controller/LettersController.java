package projectrastov.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projectrastov.model.LettersJsonRepresentation;
import projectrastov.services.LettersService;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LettersController {

    private final AtomicLong counter = new AtomicLong();
    private LettersService lettersService;

    @Value("${dev}")
    private Boolean dev;


    public LettersController() {
        lettersService = new LettersService();
    }

    @GetMapping("/")
    public String home() {
        return "HOMYAK";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    @GetMapping("/{letter}")
    public LettersJsonRepresentation greeting(@PathVariable String letter) {
        String lst = "";
        if (!dev) {
            lst = lettersService.getFileContentDev(letter);
        } else {
            lst = lettersService.getFileContent(letter);
        }
        return new LettersJsonRepresentation(counter.incrementAndGet(), lst);
    }
}
