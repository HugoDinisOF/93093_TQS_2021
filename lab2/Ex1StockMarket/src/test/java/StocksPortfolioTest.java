import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {
    StocksPortfolio portfolio;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        portfolio = new StocksPortfolio();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testGetTotalValue() {
        IStockMarket stockMarket = mock(IStockMarket.class);

        Mockito.when(stockMarket.getPrice("APPL")).thenReturn(3.0);
        Mockito.when(stockMarket.getPrice("TSLA")).thenReturn(4.0);

        portfolio.addStock(new Stock("TSLA",2));
        portfolio.addStock(new Stock("AAPL",3));

        assertEquals(portfolio.getTotalValue(),9+8);
        verify(stockMarket,calls(2));

    }
}