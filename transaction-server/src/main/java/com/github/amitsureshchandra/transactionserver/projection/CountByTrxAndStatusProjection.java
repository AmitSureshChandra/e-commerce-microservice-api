package com.github.amitsureshchandra.transactionserver.projection;

public interface CountByTrxAndStatusProjection {
    Long getStatusCount();
    Long getTotal();
}
