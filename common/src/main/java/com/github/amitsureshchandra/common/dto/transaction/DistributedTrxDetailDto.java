package com.github.amitsureshchandra.common.dto.transaction;

import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
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
