package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseRepositoryTest {

    @Test
    public void testLoadExpenses() {
        IFancyDatabase mockedFancyDatabase = mock(FancyDatabase.class);
        when(mockedFancyDatabase.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository eR = new ExpenseRepository(mockedFancyDatabase);

        eR.loadExpenses();

        InOrder inOrder = inOrder(mockedFancyDatabase);

        inOrder.verify(mockedFancyDatabase).connect();
        inOrder.verify(mockedFancyDatabase).queryAll();
        inOrder.verify(mockedFancyDatabase).close();

        assertEquals(0, eR.getExpenses().size());
    }

    @Test
    void testSaveExpenses() {
        IFancyDatabase mockedFancyDatabase = mock(FancyDatabase.class);
        when(mockedFancyDatabase.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository eR = new ExpenseRepository(mockedFancyDatabase);

        //Expense eX = new Expense();

        eR.addExpense(new Expense());
        eR.addExpense(new Expense());
        eR.addExpense(new Expense());
        eR.addExpense(new Expense());
        eR.addExpense(new Expense());

        eR.saveExpenses();

        InOrder inOrder = inOrder(mockedFancyDatabase);

        inOrder.verify(mockedFancyDatabase).connect();
        inOrder.verify(mockedFancyDatabase, times(5)).persist(any(Expense.class));
        inOrder.verify(mockedFancyDatabase).close();

        // assertEquals(0, eR.getExpenses().size());
    }
}
