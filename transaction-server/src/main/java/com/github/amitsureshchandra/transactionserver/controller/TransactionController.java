package com.github.amitsureshchandra.transactionserver.controller;

import com.github.amitsureshchandra.transactionserver.dto.DistributedTransactionListDto;
import com.github.amitsureshchandra.transactionserver.dto.DistributedTransactionParticipantDto;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.transactionserver.service.CoordinatorService;
import com.github.amitsureshchandra.transactionserver.service.TransactionService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@EnableDiscoveryClient
class TransactionController{
    private final TransactionService transactionService;
    private final CoordinatorService coordinatorService;

    public TransactionController(TransactionService transactionService, CoordinatorService coordinatorService) {
        this.transactionService = transactionService;
        this.coordinatorService = coordinatorService;
    }

    @PostMapping
    public Long create(@RequestBody List<DistributedTransactionParticipantDto> participants) {
        return transactionService.create(participants);
    }

    @PutMapping("/{tid}/participants")
    public void addParticipants(@PathVariable Long tid, @RequestBody List<DistributedTransactionParticipantDto> participants) {
        transactionService.addParticipants(tid, participants);
    }

    @GetMapping
    public List<DistributedTransactionListDto> getAll(){
        return transactionService.getAll();
    }

    @GetMapping("/{tid}/prepared")
    public boolean checkPreparedById(@PathVariable Long tid) {
        return transactionService.prepared(tid);
    }

    @GetMapping("/{tid}/committed")
    public boolean checkCommittedById(@PathVariable Long tid) {
        return transactionService.checkCommittedById(tid);
    }

    @PutMapping("/{tid}/finish/{status}")
    public void finish(@PathVariable Long tid, DistributedTransactionStatus status){
        transactionService.finish(tid, status);
    }



    @PutMapping("/{tid}/participants/{serviceId}/{status}")
    public void updateParticipants(@PathVariable Long tid,@PathVariable String serviceId,@PathVariable DistributedTransactionStatus status) {
        transactionService.updateParticipants(tid, serviceId, status);
    }

    @PostMapping("/{tx_id}/coordinate")
    public void coordinate(@PathVariable Long tx_id) {
        coordinatorService.coordinate(tx_id);
    }
}