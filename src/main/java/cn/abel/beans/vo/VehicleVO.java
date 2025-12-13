package cn.abel.beans.vo;

import lombok.Data;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@Data
public class VehicleVO {
    private String id;

    private String vin;

    /**
     * 裸车价格
     */
    private String price;

    /**
     * 落地价
     */
    private double totalPrice;

}