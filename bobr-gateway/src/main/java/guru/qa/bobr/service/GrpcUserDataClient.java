package guru.qa.bobr.service;

import guru.qa.bobr.dto.UserData;
import guru.qa.grpc.bobr.grpc.BobrUserDataServiceGrpc;
import guru.qa.grpc.bobr.grpc.UserRequest;
import guru.qa.grpc.bobr.grpc.UserResponse;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.Nonnull;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class GrpcUserDataClient {

    @GrpcClient("grpcUserDataClient")
    private BobrUserDataServiceGrpc.BobrUserDataServiceBlockingStub bobrUserDataServiceBlockingStub;

    public @Nonnull UserData getUser(String username) {
        UserRequest request = UserRequest.newBuilder()
                .setUsername(username)
                .build();
        try {
            UserResponse response = bobrUserDataServiceBlockingStub.getUser(request);
            UserData userData = new UserData();
            userData.setId(UUID.fromString(response.getId().toStringUtf8()));
            userData.setUsername(response.getUsername());
            userData.setEmail(response.getEmail());
            userData.setFirstname(response.getFirstname());
            userData.setLastname(response.getLastname());
            return userData;
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запрашиваемый пользователь с username " + username + " не найден", e);
            } else {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation was cancelled", e);
            }
        }
    }
}
