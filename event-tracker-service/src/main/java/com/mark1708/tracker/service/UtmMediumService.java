package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmMedium;

public interface UtmMediumService {

  UtmMedium getOrCreate(String name);

}
