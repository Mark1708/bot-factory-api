package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.utm.UtmTerm;

public interface UtmTermService {

  UtmTerm getOrCreate(String name);

}
