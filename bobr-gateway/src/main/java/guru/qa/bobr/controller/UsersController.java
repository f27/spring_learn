package guru.qa.bobr.controller;

import guru.qa.bobr.dto.UserData;
import guru.qa.bobr.service.GrpcUserDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final GrpcUserDataClient grpcUserDataClient;

    @Autowired
    public UsersController(GrpcUserDataClient grpcUserDataClient) {
        this.grpcUserDataClient = grpcUserDataClient;
    }

    @GetMapping("/me")
    public UserData getUserInfo(@AuthenticationPrincipal Jwt principal) {
        String username = principal.getClaim("sub");
        System.out.println("1");
        return grpcUserDataClient.getUser(username);
    }
}
