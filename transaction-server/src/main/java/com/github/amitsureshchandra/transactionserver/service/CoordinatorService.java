package com.github.amitsureshchandra.transactionserver.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService {

    @Async
    public void coordinate(Long txId) {

        // PHASE 1

        System.out.println("~~~~~~~~ Started Prepare ~~~~~~~~");
        for (int i = 0; i < 1000; i++) {
            // check if all are prepared => if any one is failed then mark transaction as failed
            System.out.println("checked : " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("~~~~~~~~ Ended Prepare ~~~~~~~~");

        // sent msg to commit everyone

        System.out.println("COMMIT MSG Sent");

        // PHASE 2
        System.out.println("~~~~~~~~ Started Commit Phase ~~~~~~~~");

        for (int i = 0; i < 1000; i++) {
            // check if all are commited => if anyone is failed then tell every one to rollback
            System.out.println("checked : " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("~~~~~~~~ Ended Commit Phase ~~~~~~~~");
    }
}
