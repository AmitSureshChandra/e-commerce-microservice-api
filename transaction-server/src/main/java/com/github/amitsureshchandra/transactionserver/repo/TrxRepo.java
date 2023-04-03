package com.github.amitsureshchandra.transactionserver.repo;

import com.github.amitsureshchandra.transactionserver.entity.DistributedTrx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.TransactionStatus;

public interface TrxRepo extends JpaRepository<DistributedTrx, Long> {

}
