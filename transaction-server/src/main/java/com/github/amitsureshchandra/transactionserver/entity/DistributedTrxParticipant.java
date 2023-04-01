package com.github.amitsureshchandra.transactionserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
public class DistributedTrxParticipant {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trx_id")
    DistributedTrx trx;

    private String serviceId;
    private DistributedTransactionStatus status;

    public DistributedTrxParticipant(String service, DistributedTransactionStatus status) {
        this.serviceId = service;
        this.status = status;
    }
}
