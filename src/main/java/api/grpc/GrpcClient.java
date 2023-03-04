package api.grpc;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                                    .usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub
                 = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request
                 = GreetingServiceOuterClass.HelloRequest.newBuilder()
                    .setName("John").build();

        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request);
        System.out.println(response);


        GreetingServiceOuterClass.HelloRequest streamRequest
                = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Mary").build();

        Iterator<GreetingServiceOuterClass.HelloResponse> streamResponse = stub.streamGreeting(streamRequest);

        while(streamResponse.hasNext()){
            System.out.println(streamResponse.next());
        }

        channel.shutdownNow();
    }
}
