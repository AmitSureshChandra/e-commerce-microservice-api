package com.github.amitsureshchandra.transactionserver.dto;

import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTrxDetailDto {
    private Long id;
    private DistributedTransactionStatus status;
    private List<DistributedTransactionParticipantDto> participants;


}
