package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserSignInUseCaseTest {
    @InjectMocks
    private UserSignInUseCase userSignInUseCase;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ICryptoAdapter cryptoAdapter;


    // antes de todos os testes dessa classe esse metodo vai ser executado
    @BeforeEach
    public void setup(){
        /*por default voce deve retornar o caso de sucesso, então vc sempre deve retornar o usuário aqui, e fazer o caso
        de erro que é retornar null somente no teste que voce quer
         */

        when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.of(new User()));
    }

    @Test
    @DisplayName("Should throw an error if there is no user with provided e-mail")
    public void whenUserNotFoundWithProvidedEmail() {
        // nesse caso como voce quer o erro, então faz sentido colocar o retorno como optional.empty
        when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(()->
                userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }

    @Test
    @DisplayName("Should throw an error if there is no user with provided e-mail")
    public void whenUserNotFoundWithProvidedPassword() {
        // não precisa mock pq por default ele vai retonar o usuário, no setup fazemos isso
        //when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(()->
                userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }

    @Test
    @DisplayName("Should throw an error if cryptoAdapter is false")
    public void withCryptoAdapterFails() {
        // aqui eu faço o mock somente da parte que quero retorna o valor falso
        when(cryptoAdapter.matches(anyString(), anyString())).thenReturn(false);

        Assertions.assertThatExceptionOfType(Error.class)
                .isThrownBy(() ->
                        userSignInUseCase.execute("nonexistent@example.com", "123")
                );

    }
}