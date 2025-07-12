package com.project.DigitalTolk.DigitalTolk.repository;

import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, String> {
}
