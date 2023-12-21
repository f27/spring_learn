package guru.qa.bobr.service;

import guru.qa.bobr.dto.PongDto;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    public @Nonnull PongDto ping() {
        PongDto pongDto = new PongDto();
        pongDto.setAnswer("pong");
        return pongDto;
    }

}
