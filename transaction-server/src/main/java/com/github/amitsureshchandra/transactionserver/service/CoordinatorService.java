package com.github.amitsureshchandra.transactionserver.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoordinatorService {
    public void coordinate(UUID txId) {

        // PHASE 1

        for (int i = 0; i < 1000; i++) {
            // check if all are prepared => if any one is failed then mark transaction as failed
        }

        // sent msg to commit everyone

        // PHASE 2

        for (int i = 0; i < 1000; i++) {
            // check if all are commited => if anyone is failed then tell every one to rollback
        }
    }
}
