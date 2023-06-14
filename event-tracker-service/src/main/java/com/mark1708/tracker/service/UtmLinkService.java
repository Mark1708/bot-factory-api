package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmLink;
import java.util.Optional;
import javax.net.ssl.SSLSession;

public interface UtmLinkService {

  UtmLink save(UtmLink utmLink);

  Optional<UtmLink> findByBotIdAndUtmId(Long botId, String utmId);
}
