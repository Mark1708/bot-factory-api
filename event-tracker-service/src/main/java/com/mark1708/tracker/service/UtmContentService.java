package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmContent;

public interface UtmContentService {

  UtmContent getOrCreate(String name);

}
