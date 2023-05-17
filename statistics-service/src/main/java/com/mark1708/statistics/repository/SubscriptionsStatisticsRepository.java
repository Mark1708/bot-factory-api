package com.mark1708.statistics.repository;

import com.mark1708.statistics.model.entity.SubscriptionsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsStatisticsRepository
    extends JpaRepository<SubscriptionsStatistics, Long>
{

}
