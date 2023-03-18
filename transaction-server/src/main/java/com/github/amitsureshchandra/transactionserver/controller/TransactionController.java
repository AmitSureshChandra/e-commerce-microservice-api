package com.github.amitsureshchandra.transactionserver.controller;

import com.github.amitsureshchandra.transactionserver.dto.DistributedTransaction;
import com.github.amitsureshchandra.transactionserver.dto.DistributedTransactionParticipant;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.transactionserver.service.TransactionService;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController{
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public DistributedTransaction add(@RequestBody DistributedTransaction transaction) {
        return transactionService.add(transaction);
    }

    @GetMapping("/{tid}")
    public DistributedTransaction getById(@PathVariable String tid) {
        return transactionService.findById(tid);
    }

    @PutMapping("/{tid}/finish/{status}")
    public void finish(@PathVariable String tid, DistributedTransactionStatus status){
        transactionService.finish(tid, status);
    }

    @PutMapping("/{tid}/participants")
    public void addParticipants(@PathVariable String tid, @RequestBody DistributedTransactionParticipant participant) {
        transactionService.addParticipants(tid, participant);
    }

    @PutMapping("/{tid}/participants/{serviceId}/{status}")
    public void updateParticipants(@PathVariable String tid,@PathVariable String serviceId,@PathVariable DistributedTransactionStatus status) {
        transactionService.updateParticipants(tid, serviceId, status);
    }
}