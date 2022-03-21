package com.ww.aop;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-20 23:49:20
 */
public class SmartSeller implements Seller {

    @Override
    public int sell(String goods, String clientName) {
        System.out.println("SmartSeller: sell " + goods + " to " + clientName + "...");
        return 100;
    }

    public void checkBill(int billId) {
        if (1 == billId) {
            throw new IllegalArgumentException("iae Exception");
        } else {
            throw new RuntimeException("re Exception");
        }
    }
}
