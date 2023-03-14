package com.github.amitsureshchandra.accountservice.dto;

import com.github.amitsureshchandra.accountservice.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTransaction {
    private String id;
    private DistributedTransactionStatus status;

    private List<DistributedTransactionParticipant> participants;
}
