package com.mark1708.botfactorycore.controller;

import com.mark1708.clients.tracker.ClickerTrackerClient;
import com.mark1708.clients.tracker.dto.ClickerInfo;
import com.mark1708.clients.tracker.dto.CreateClickerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clicker/{botId}")
public class ClickerController {


}
