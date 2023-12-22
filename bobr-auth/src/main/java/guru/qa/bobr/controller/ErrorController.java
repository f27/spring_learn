package guru.qa.bobr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final String frontUri;

    public ErrorController(@Value("${bobr-front.base-uri}") String frontUri) {
        this.frontUri = frontUri;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "redirect:" + frontUri;
    }
}
