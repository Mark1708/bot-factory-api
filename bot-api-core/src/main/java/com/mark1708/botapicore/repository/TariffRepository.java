package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Tariff;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

  List<Tariff> findAllByServiceId(long serviceId);

}
