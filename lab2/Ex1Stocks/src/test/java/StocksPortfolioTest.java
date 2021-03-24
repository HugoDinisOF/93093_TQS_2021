import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {
    private IStockMarket stockMarket;
    private StocksPortfolio portfolio;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        portfolio = new StocksPortfolio();
        stockMarket = mock(IStockMarket.class);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getTotalValue() {
        portfolio.setMarketService(stockMarket);

        when(stockMarket.getPrice("TSLA")).thenReturn(4.0);
        when(stockMarket.getPrice("DOGE")).thenReturn(0.1);

        portfolio.addStock(new Stock("TSLA",3));
        portfolio.addStock(new Stock("DOGE",10));

        assertThat(portfolio.getTotalValue(),is(13.0));
        //assertEquals(portfolio.getTotalValue(),13.0);

        verify(stockMarket,times(2)).getPrice(anyString());
    }
}