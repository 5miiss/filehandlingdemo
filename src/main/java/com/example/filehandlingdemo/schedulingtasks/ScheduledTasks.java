package com.example.filehandlingdemo.schedulingtasks;

import com.example.filehandlingdemo.order.business.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
//    private final OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 */2 * * * *")
    public void reportCurrentTime() {
//        orderService.rejectRequest();
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}