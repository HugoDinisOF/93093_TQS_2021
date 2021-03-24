import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private String name;
    private List<Stock> stocks;
    private IStockMarket marketService;

    public StocksPortfolio(){
        stocks = new ArrayList<>();
    }

    public IStockMarket getMarketService() {
        return marketService;
    }

    public void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double getTotalValue(){
        double sum = 0;
        for(Stock s: stocks){
            sum+=marketService.getPrice(s.getName())*s.getQuantity();
        }
        return sum;
    }
}
