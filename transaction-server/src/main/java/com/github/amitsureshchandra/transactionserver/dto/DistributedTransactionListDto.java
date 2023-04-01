package com.github.amitsureshchandra.transactionserver.dto;

import com.github.amitsureshchandra.transactionserver.entity.DistributedTrx;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTransactionListDto {
    private Long id;
    private DistributedTransactionStatus status;

    public DistributedTransactionListDto(DistributedTrx trx) {
        this.id = trx.getId();
        this.status = trx.getStatus();
    }
}
