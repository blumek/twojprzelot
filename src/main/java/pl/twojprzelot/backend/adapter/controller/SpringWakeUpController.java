package pl.twojprzelot.backend.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wake-up")
final class SpringWakeUpController {

    @GetMapping
    public ResponseEntity<Object> wakeUp() {
        return ResponseEntity.ok()
                .build();
    }

}
