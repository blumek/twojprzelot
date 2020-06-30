package pl.twojprzelot.backend.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringWakeUpController {

    @GetMapping("/wake-up")
    public ResponseEntity<Object> wakeUp() {
        return ResponseEntity.ok()
                .build();
    }

}
