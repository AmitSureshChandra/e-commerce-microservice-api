package com.github.amitsureshchandra.accountservice.dto;

import com.github.amitsureshchandra.accountservice.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DistributedTransactionParticipant {
    private String serviceId;
    private DistributedTransactionStatus status;
}
