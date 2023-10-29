package edu.hw3.Task6;

import java.util.Objects;

public record Stock(String name, double price) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Double.compare(price, stock.price) == 0 && Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
