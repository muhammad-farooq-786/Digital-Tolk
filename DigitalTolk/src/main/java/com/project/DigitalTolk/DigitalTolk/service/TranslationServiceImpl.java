package com.project.DigitalTolk.DigitalTolk.service;


import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import com.project.DigitalTolk.DigitalTolk.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService{

    private final TranslationRepository translationRepository;

    @Override
    public Translation create(Translation translation) {
        translation.setId(UUID.randomUUID().toString());
        return translationRepository.save(translation);
    }

    @Override
    public Translation update(String id, Translation translation) {
        return translationRepository.findById(id)
                .map(existing -> {
                    existing.setLocale(translation.getLocale());
                    existing.setKey(translation.getKey());
                    existing.setContent(translation.getContent());
                    existing.setTags(translation.getTags());
                    existing.setUpdatedAt(new Date());
                    return translationRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Translation not found with ID:" + id));
    }

    @Override
    public Optional<Translation> getById(String id) {
        return translationRepository.findById(id);
    }

    @Override
    public Page<Translation> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return translationRepository.findAll(pageable);
    }

    @Override
    public void delete(String id) {
        translationRepository.deleteById(id);
    }

    @Override
    public List<Translation> searchTranslations(String key, String tag, String content) {
        return translationRepository.searchTranslations(key, tag, content);
    }

    @Override
    public Map<String, Map<String, String>> exportGroupedByLocale(String locale) {
        List<Translation> translations = (locale == null || locale.isBlank())
                ? translationRepository.findAll()
                : translationRepository.findByLocale(locale);
        Map<String, Map<String, String>> grouped = new HashMap<>();

        for (Translation t : translations) {
            grouped
                    .computeIfAbsent(t.getLocale(), l -> new HashMap<>())
                    .put(t.getKey(), t.getContent());
        }

        return grouped;
    }
}
