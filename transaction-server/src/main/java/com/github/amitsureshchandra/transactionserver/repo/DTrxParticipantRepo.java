package com.github.amitsureshchandra.transactionserver.repo;

import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.transactionserver.projection.CountByTrxAndStatusProjection;
import com.github.amitsureshchandra.transactionserver.entity.DistributedTrxParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DTrxParticipantRepo extends JpaRepository<DistributedTrxParticipant, Long> {
    DistributedTrxParticipant findByTrxIdAndServiceId(Long tid, String serviceId);

//    @Query("SELECT COUNT(IF(dtx.status = DistributedTransactionStatus.PREPARE, 1, 0)), COUNT(dtx.status) FROM DistributedTrxParticipant dtx WHERE dtx.trx.id = :tid AND dtx.status = :status")
    @Query("SELECT COUNT(CASE WHEN dtx.status = :status THEN 1 ELSE null END) as statusCount, COUNT(dtx.status) as total FROM DistributedTrxParticipant dtx WHERE dtx.trx.id = :tid")
    CountByTrxAndStatusProjection findCountByTrxIdAndStatus(Long tid, DistributedTransactionStatus status);
}
