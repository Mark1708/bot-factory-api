package com.mark1708.tracker.facade;

public interface UtmFacade {

  String generateLink(
      Long botId, String source, String medium,
      String campaign, String content, String term
  );

  void processingUtm(Long botId, String utmId, Long userId, boolean newUser);
}
