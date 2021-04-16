import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private String name;
    private IStockMarket marketService;
    private List<Stock> stocks;
    public StocksPortfolio(){
        stocks = new ArrayList<>();
    }

    public IStockMarket getMarketService() {
        return marketService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    public void addStock(Stock s){
        stocks.add(s);
    }

    public double getTotalValue(){
        double res = 0;
        for (Stock s: stocks){
            res+=marketService.getPrice(s.getName())*s.getQuantity();
        }
        return res;
    }
}
