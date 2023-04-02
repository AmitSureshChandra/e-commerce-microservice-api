package com.github.amitsureshchandra.accountservice.dto;

import com.github.amitsureshchandra.accountservice.enums.DistributedTransactionStatus;
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
