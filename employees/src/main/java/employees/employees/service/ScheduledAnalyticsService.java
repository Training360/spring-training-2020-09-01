package employees.employees.service;

import employees.employees.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
@AllArgsConstructor
@Slf4j
public class ScheduledAnalyticsService {

    private EmployeesRepository repository;

    @Scheduled(fixedRate = 10_000)
    public void makeAnalytics() {
        var count = repository.count();
        log.info("The number of the employees: " + count);
    }
}
