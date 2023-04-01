package com.github.amitsureshchandra.orderservice.dto;

import com.github.amitsureshchandra.orderservice.enums.DistributedTransactionStatus;
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
