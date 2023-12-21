package guru.qa.bobr.controller;

import guru.qa.bobr.dto.PongDto;
import guru.qa.bobr.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
public class PingController {

    private final PingService pingService;

    @Autowired
    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/")
    public PongDto ping() {
        return pingService.ping();
    }

}
