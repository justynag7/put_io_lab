package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    @Test
    void testCalculateTotal() {
        ExpenseRepository mockedExpenseRepository = mock(ExpenseRepository.class);
        List<Expense> list = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense eX = new Expense();
            eX.setAmount(i);
            list.add(eX);
        }

        when(mockedExpenseRepository.getExpenses()).thenReturn(list);

        FancyService fS = new FancyService();
        ExpenseManager eM = new ExpenseManager(mockedExpenseRepository, fS);

        assertEquals(3, eM.calculateTotal());
    }

    @Test
    void testCalculateTotalForCategory() {
        ExpenseRepository mockedExpenseRepository = mock(ExpenseRepository.class);
        List<Expense> list = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense eX = new Expense();
            eX.setAmount(i);
            list.add(eX);
        }

        when(mockedExpenseRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mockedExpenseRepository.getExpensesByCategory("Home")).thenReturn(list);
        when(mockedExpenseRepository.getExpensesByCategory("Car")).thenReturn(list);

        FancyService fS = new FancyService();
        ExpenseManager eM = new ExpenseManager(mockedExpenseRepository, fS);

        assertEquals(0, eM.calculateTotalForCategory("ANY"));
        assertEquals(3, eM.calculateTotalForCategory("Home"));
        assertEquals(3, eM.calculateTotalForCategory("Car"));

        // 5.1. Koleność ma znaczenie
    }

    @Test
    void testCalculateTotalInDollars() throws ConnectException {
        ExpenseRepository mockedExpenseRepository = mock(ExpenseRepository.class);
        List<Expense> list = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            Expense eX = new Expense();
            eX.setAmount(8);
            list.add(eX);
        }
        //FancyService fS = new FancyService();
        FancyService mockedFancyService = mock(FancyService.class);
        //when(mockedFancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenReturn(5.0);
        //when(mockedFancyService.convert(eq(0.0), eq("PLN"), eq("USD"))).thenThrow(new  ConnectException());
        when(mockedFancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        double v = (double)args[0] * 0.25;
                        return v;
                    }
                });
        when(mockedExpenseRepository.getExpenses()).thenReturn(list);
        ExpenseManager eM = new ExpenseManager(mockedExpenseRepository, mockedFancyService);
        assertEquals(6, eM.calculateTotalInDollars());

    }
}
