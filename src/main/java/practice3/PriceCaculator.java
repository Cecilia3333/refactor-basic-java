package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private Order order;
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public PriceCaculator(Order order){
        this.order = order;
        this.orderLineItemList = order.getOrderLineItemList();
        this.discounts = order.getDiscounts();
        this.tax = order.getTax();
    }

    public BigDecimal calculate() {
        BigDecimal subTotal = new BigDecimal(0);

        subTotal = subtractDiscounts(calculateTotalUpItems(subTotal));

        return calculateGrandTotal(subTotal);
    }

    private BigDecimal calculateTotalUpItems(BigDecimal subTotal){
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }

    private BigDecimal subtractDiscounts(BigDecimal subTotal){
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }

    private BigDecimal calculateTax(BigDecimal subTotal){
        return subTotal.multiply(tax);
    }

    private BigDecimal calculateGrandTotal(BigDecimal subTotal){
        return subTotal.add(calculateTax(subTotal));
    }
}
