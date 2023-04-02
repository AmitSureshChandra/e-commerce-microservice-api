package com.github.amitsureshchandra.transactionserver.service;

import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionListDto;
import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionParticipantDto;
import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.transactionserver.entity.DistributedTrx;
import com.github.amitsureshchandra.transactionserver.entity.DistributedTrxParticipant;
import com.github.amitsureshchandra.transactionserver.exception.ValidationException;
import com.github.amitsureshchandra.transactionserver.projection.CountByTrxAndStatusProjection;
import com.github.amitsureshchandra.transactionserver.repo.DTrxParticipantRepo;
import com.github.amitsureshchandra.transactionserver.repo.TrxRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    final TrxRepo trxRepo;
    final KafkaService kafkaService;
    final DTrxParticipantRepo participantRepo;

    @Value("${transaction-event-topic}")
    private String trxTopic;

    final
    RabbitTemplate rabbitTemplate;
    Map<String, DistributedTransactionListDto> transactions = new HashMap<>();

    public TransactionService(TrxRepo trxRepo, KafkaService kafkaService, DTrxParticipantRepo participantRepo, RabbitTemplate rabbitTemplate) {
        this.trxRepo = trxRepo;
        this.kafkaService = kafkaService;
        this.participantRepo = participantRepo;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Long create(List<DistributedTransactionParticipantDto> participants) {
        DistributedTrx trx = new DistributedTrx();
        trx.setStatus(DistributedTransactionStatus.NEW);
        trx.addParticipants(
                participants.stream().map(
                        p -> new DistributedTrxParticipant(p.getServiceId(), p.getStatus())
                ).collect(Collectors.toList())
        );
        trxRepo.save(trx);
        kafkaService.sendMessage(new DistributedTransactionListDto(trx.getId(), trx.getStatus()));
        return trx.getId();
    }

    public DistributedTrx findById(Long tid) {
        return trxRepo.findById(tid).orElseThrow(() -> new ValidationException("not found by given id : " + tid));
    }

    public void finish(Long tid, DistributedTransactionStatus status) {
        DistributedTrx transaction = findById(tid);
        transaction.setStatus(status);
        trxRepo.save(transaction);
        System.out.println("status : " + status);
        kafkaService.sendMessage(new DistributedTransactionListDto(transaction.getId(), transaction.getStatus()));
    }

    public void addParticipants(Long tid, List<DistributedTransactionParticipantDto> participants) {
        DistributedTrx trx = findById(tid);
        trx.addParticipants(
                participants.stream().map(
                        p -> new DistributedTrxParticipant(p.getServiceId(), p.getStatus())
                ).collect(Collectors.toList())
        );
        trxRepo.save(trx);
    }

    public void updateParticipants(Long tid, String serviceId, DistributedTransactionStatus status) {
        DistributedTrxParticipant participant = participantRepo.findByTrxIdAndServiceId(tid, serviceId);
        if (participant == null) return;
        participant.setStatus(status);
        participantRepo.save(participant);

//        rabbitTemplate.convertAndSend(trxTopic, tid);
    }

    public List<DistributedTransactionListDto> getAll() {
        return trxRepo.findAll().stream().map(trx -> new DistributedTransactionListDto(trx.getId(), trx.getStatus())).collect(Collectors.toList());
    }

    public boolean prepared(Long tid) {
        return checkStatusWithTrx(tid, DistributedTransactionStatus.PREPARE);
    }

    public boolean checkCommittedById(Long tid) {
        return checkStatusWithTrx(tid, DistributedTransactionStatus.COMMIT);
    }

    public boolean checkStatusWithTrx(Long trx, DistributedTransactionStatus status) {
        CountByTrxAndStatusProjection cnt = participantRepo.findCountByTrxIdAndStatus(trx, status);
        return Objects.equals(cnt.getTotal(), cnt.getStatusCount());
    }
}
