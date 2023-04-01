package com.github.amitsureshchandra.transactionserver.dto;

import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTransactionParticipantDto {
    private String serviceId;
    private DistributedTransactionStatus status;
}
