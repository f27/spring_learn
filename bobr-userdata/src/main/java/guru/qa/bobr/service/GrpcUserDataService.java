package guru.qa.bobr.service;

import guru.qa.bobr.dto.UserData;
import guru.qa.bobr.repository.UserRepository;
import guru.qa.grpc.bobr.grpc.BobrUserDataServiceGrpc;
import guru.qa.grpc.bobr.grpc.UserRequest;
import guru.qa.grpc.bobr.grpc.UserResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.protobuf.ByteString.copyFromUtf8;

@GrpcService
public class GrpcUserDataService extends BobrUserDataServiceGrpc.BobrUserDataServiceImplBase {

    private final UserRepository userRepository;

    @Autowired
    public GrpcUserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        String username = request.getUsername();

        UserData userData = userRepository.findByUsername(username);

        UserResponse userResponse = UserResponse.newBuilder()
                .setId(copyFromUtf8(userData.getId().toString()))
                .setUsername(userData.getUsername())
                .setEmail(userData.getEmail())
                .setFirstname(userData.getFirstname())
                .setLastname(userData.getLastname())
                .build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
        super.getUser(request, responseObserver);
    }
}
