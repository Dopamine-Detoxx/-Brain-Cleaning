package com.dd.blog.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class SeederRunner implements CommandLineRunner {

    private final AdminInitializer adminInitializer;
    private final CategoryInitializer categoryInitializer;
    private final PointItemInitializer pointItemInitializer;
    private final UserInitializer userInitializer;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        adminInitializer.run();
        categoryInitializer.run();
        pointItemInitializer.run();
        userInitializer.run();
    }
}
