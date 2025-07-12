//package com.project.DigitalTolk.DigitalTolk.util;
//
//import com.project.DigitalTolk.DigitalTolk.entity.Translation;
//import com.project.DigitalTolk.DigitalTolk.repository.TranslationRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class TranslationSeeder implements CommandLineRunner {
//
//    private final TranslationRepository translationRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        long existing = translationRepository.count();
//        if (existing > 0) {
//            System.out.println("Database already has " + existing + " entries. Skipping seeding.");
//            return;
//        }
//
//        List<Translation> batch = new ArrayList<>();
//        long start = System.currentTimeMillis();
//
//        for (int i = 1; i <= 100_000; i++) {
//            Translation t = Translation.builder()
//                    .id(UUID.randomUUID().toString())
//                    .locale(i % 3 == 0 ? "en" : i % 3 == 1 ? "fr" : "es")
//                    .key("key_" + i)
//                    .content("This is translation content number " + i)
//                    .tags((i % 2 == 0 ? "mobile" : "desktop") + ",header")
//                    .build();
//
//            batch.add(t);
//
//            // Save in batches of 1,000 for performance
//            if (batch.size() == 1000) {
//                translationRepository.saveAll(batch);
//                batch.clear();
//                System.out.println("Inserted " + i + " entries...");
//            }
//        }
//        if (!batch.isEmpty()) {
//            translationRepository.saveAll(batch);
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("Inserted 100,000+ entries in " + (end - start) + "ms");
//    }
//}
