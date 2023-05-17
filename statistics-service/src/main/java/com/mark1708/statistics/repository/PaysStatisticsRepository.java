package com.mark1708.statistics.repository;

import com.mark1708.statistics.model.entity.PaysStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysStatisticsRepository extends JpaRepository<PaysStatistics, Long> {

}
