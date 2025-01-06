package projetarchitecture.projetarchitecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import projetarchitecture.projetarchitecture.dao.AccountDAO;
import projetarchitecture.projetarchitecture.kafka.events.AccountCreatedEvent;
import projetarchitecture.projetarchitecture.kafka.producers.KafkaProducerService;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.service.AccountService;
import projetarchitecture.projetarchitecture.util.AccountValidationChain;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @Mock
    private AccountValidationChain accountValidationChain;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount_shouldSaveAccountAndPublishEvent() {
        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setBalance(1000.0);
        account.setType("Checking");
        account.setClientId("client123");

        when(accountDAO.save(any(Account.class))).thenReturn(account);

        Account result = accountService.createAccount(account);

        assertNotNull(result);
        assertEquals("123456789", result.getAccountNumber());

        verify(accountDAO).save(account);
        verify(kafkaProducerService).publish(eq("account-created"), any(AccountCreatedEvent.class));
    }
}
