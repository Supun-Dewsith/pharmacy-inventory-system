package model.tm;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CartTM {
    private Long medId;
    private String medCode;
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty qty = new SimpleIntegerProperty();
    private DoubleProperty total = new SimpleDoubleProperty();

    public CartTM(Long medId,String medCode,double price, int qty){
        this.medId=medId;
        this.medCode=medCode;
        this.price.set(price);
        this.qty.set(qty);
        this.total.bind(this.price.multiply(this.qty));
    }

    public int getQty(){
        return qty.get();
    }

    public void setQty(int qty){
        this.qty.set(qty);
    }

    public IntegerProperty qtyProperty(){
        return qty;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }
}
