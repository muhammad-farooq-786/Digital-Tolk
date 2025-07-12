package com.project.DigitalTolk.DigitalTolk.service;


import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import com.project.DigitalTolk.DigitalTolk.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
}
