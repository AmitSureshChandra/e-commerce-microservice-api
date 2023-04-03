package com.github.amitsureshchandra.common.dto.transaction;

import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTransactionListDto {
    private Long id;
    private DistributedTransactionStatus status;
}
