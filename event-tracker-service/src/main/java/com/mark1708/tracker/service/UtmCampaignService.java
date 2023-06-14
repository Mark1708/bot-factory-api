package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmCampaign;

public interface UtmCampaignService {

  UtmCampaign getOrCreate(String name);

}
