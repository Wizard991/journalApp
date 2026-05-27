package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
            ExtensionContext context) {

        return Stream.of(
                Arguments.of(
                        User.builder()
                                .userName("yash")
                                .password("12345")
                                .build()
                ),
                Arguments.of(
                        User.builder()
                                .userName("raj")
                                .password("67890")
                                .build()
                )
        );
    }
}