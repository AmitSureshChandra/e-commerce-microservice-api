package com.github.amitsureshchandra.transactionserver.entity;

import com.github.amitsureshchandra.transactionserver.dto.DistributedTransactionParticipantDto;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
public class DistributedTrx {
    @Id @GeneratedValue
    private Long id;

    private DistributedTransactionStatus status;

    @OneToMany(mappedBy = "trx", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DistributedTrxParticipant> participants = new ArrayList<>();

    public void addParticipant(DistributedTrxParticipant participant) {
        participant.setTrx(this);
        this.participants.add(participant);
    }

    public void addParticipants(List<DistributedTrxParticipant> participants) {
        participants.forEach(this::addParticipant);
    }
}
