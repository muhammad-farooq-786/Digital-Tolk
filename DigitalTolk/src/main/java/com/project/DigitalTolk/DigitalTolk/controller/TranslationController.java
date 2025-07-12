package com.project.DigitalTolk.DigitalTolk.controller;


import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import com.project.DigitalTolk.DigitalTolk.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping("/createTranslation")
    public ResponseEntity<Translation> createTranslation(@RequestBody Translation translation){
        return ResponseEntity.ok(translationService.create(translation));
    }

    @PutMapping("/updateTranslation/{id}")
    public ResponseEntity<Translation> updateTranslation(@PathVariable String id, @RequestBody Translation translation){
        return ResponseEntity.ok(translationService.update(id, translation));
    }

    @GetMapping("/getTranslation/{id}")
    public ResponseEntity<Translation> getTranslationById(@PathVariable String id){
        return translationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getTranslations")
    public ResponseEntity<Page<Translation>> getTranslations(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "1000") int size){
        return ResponseEntity.ok(translationService.getAll(page, size));
    }

    @DeleteMapping("/deleteTranslation/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        translationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Translation>> searchTranslations(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String content
    ) {
        List<Translation> results = translationService.searchTranslations(key, tag, content);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/export")
    public ResponseEntity<Map<String, Map<String, String>>> exportTranslations(@RequestParam(required = false) String locale) {
        Map<String, Map<String, String>> result = translationService.exportGroupedByLocale(locale);
        return ResponseEntity.ok(result);
    }
}
