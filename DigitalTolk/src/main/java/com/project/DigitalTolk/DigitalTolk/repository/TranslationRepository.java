package com.project.DigitalTolk.DigitalTolk.repository;

import com.project.DigitalTolk.DigitalTolk.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, String> {

    @Query(value = """
    SELECT * FROM translations
    WHERE (:key IS NULL OR `key` = :key)
      AND (:tag IS NULL OR tags LIKE CONCAT('%', :tag, '%'))
      AND (:content IS NULL OR LOWER(content) LIKE LOWER(CONCAT('%', :content, '%')))
    """, nativeQuery = true)
    List<Translation> searchTranslations(
            @Param("key") String key,
            @Param("tag") String tag,
            @Param("content") String content
    );

    List<Translation> findByLocale(String locale);
}
