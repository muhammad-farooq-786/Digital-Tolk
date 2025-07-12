package com.project.DigitalTolk.DigitalTolk.service;

import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TranslationService {
    Translation create(Translation translation);

    Translation update(String id, Translation translation);

    Optional<Translation> getById(String id);

    Page<Translation> getAll(int page, int size);

    void delete(String id);
}
