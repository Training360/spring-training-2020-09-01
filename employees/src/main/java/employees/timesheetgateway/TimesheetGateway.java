package employees.timesheetgateway;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import training.timesheet.gateway.CreateEmployeeRequest;
import training.timesheet.gateway.CreateEmployeeResponse;
import training.timesheet.gateway.TimesheetServiceGrpc;

@Service
public class TimesheetGateway {

    private String host;

    private int port;

    public TimesheetGateway(@Value("${timesheet.host}") String host,
                            @Value("${timesheet.port}") int port) {
        this.host = host;
        this.port = port;
    }

    public void createEmployee(String name) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                host, port)
                .usePlaintext()
                .build();

        TimesheetServiceGrpc.TimesheetServiceBlockingStub stub
                = TimesheetServiceGrpc.newBlockingStub(channel);

        CreateEmployeeResponse createEmployeeResponse = stub.createEmployee(CreateEmployeeRequest.newBuilder()
                .setName(name)
                .build());

        channel.shutdown();
    }
}
