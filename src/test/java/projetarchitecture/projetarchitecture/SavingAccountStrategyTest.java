package projetarchitecture.projetarchitecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.strategy.account.SavingAccountStrategy;

@ExtendWith(MockitoExtension.class)
class SavingAccountStrategyTest {

    @InjectMocks
    private SavingAccountStrategy savingAccountStrategy;

    @Test
    void processAccountBehavior_shouldThrowExceptionIfBalanceBelowMinimum() {
        Account account = new Account();
        account.setBalance(150.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                savingAccountStrategy.processAccountBehavior(account, 100.0));

        assertEquals("Savings account must maintain a minimum balance of 100.0", exception.getMessage());
    }





}

