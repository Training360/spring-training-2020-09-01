package employees.timesheetgateway;

import employees.infra.gateway.Gateway;
import employees.infra.events.EmployeeHasCreatedEvent;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import training.timesheet.gateway.CreateEmployeeRequest;
import training.timesheet.gateway.CreateEmployeeResponse;
import training.timesheet.gateway.TimesheetServiceGrpc;

@Gateway
@Slf4j
@AllArgsConstructor
public class TimesheetGateway {

    private TimesheetConfig timesheetConfig;

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        createEmployee(event.getMessage());
    }

    public void createEmployee(String name) {
        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(
                    timesheetConfig.getHost(), timesheetConfig.getPort())
                    .usePlaintext()
                    .build();

            TimesheetServiceGrpc.TimesheetServiceBlockingStub stub
                    = TimesheetServiceGrpc.newBlockingStub(channel);

            CreateEmployeeResponse createEmployeeResponse = stub.createEmployee(CreateEmployeeRequest.newBuilder()
                    .setName(name)
                    .build());

            channel.shutdown();
        } catch (Exception e) {
            log.error("Can not call timesheet", e);
        }
    }
}
