package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmCampaign;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmCampaignRepository extends JpaRepository<UtmCampaign, Long> {

  Optional<UtmCampaign> findByName(String name);
}
