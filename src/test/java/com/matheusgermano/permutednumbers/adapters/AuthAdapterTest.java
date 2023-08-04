package com.matheusgermano.permutednumbers.adapters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
class AuthAdapterTest {
    @InjectMocks
    private AuthAdapter authAdapter;

    @Test
    @DisplayName("Should return a token")
    public void whenExecuteGenerateTokenMethod() {
        String token = authAdapter.generateToken("Mocked Name", UUID.randomUUID());

        Assertions.assertThat(token).isNotNull();
        Assertions.assertThat(token).isNotBlank();
    }
}