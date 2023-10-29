package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> stockPriorityQueue;

    public StockMarketImpl() {
        stockPriorityQueue = new PriorityQueue<>((stock1, stock2) -> Double.compare(stock2.price(), stock1.price()));
    }

    @Override
    public void add(Stock stock) {
        stockPriorityQueue.offer(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockPriorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockPriorityQueue.peek();
    }
}
