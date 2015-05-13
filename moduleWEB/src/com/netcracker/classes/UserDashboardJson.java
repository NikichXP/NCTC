package com.netcracker.classes;

/**
 * Created by Juger on 12.05.2015.
 */
public class UserDashboardJson {

    private String startOrder;
    private String finishOrder;
    private String price;

    public String getStartOrder(){
        return startOrder;
    }

    public void setStartOrder(String startOrder) {
        this.startOrder = startOrder;
    }

    public String getFinishOrder() {
        return finishOrder;
    }

    public void setFinishOrder(String finishOrder) {
        this.finishOrder = finishOrder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
