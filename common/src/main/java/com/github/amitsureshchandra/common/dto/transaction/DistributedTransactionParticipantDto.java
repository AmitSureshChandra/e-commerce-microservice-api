package com.github.amitsureshchandra.common.dto.transaction;

import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
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
