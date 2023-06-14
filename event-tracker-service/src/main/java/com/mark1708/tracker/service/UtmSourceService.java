package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmSource;

public interface UtmSourceService {

  UtmSource getOrCreate(String name);
}
