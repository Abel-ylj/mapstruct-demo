package cn.abel.beans.vo;

import lombok.Data;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@Data
public class CarVO {
    private Long id;
    private String vin;
    private Double price;

    /**
     * cardto上是double
     */
    private String totalPrice;
    /**
     * cardto上是date
     */
    private String publishDate;
    /**
     * cardto上是brand
     */
    private String brandName;
    /**
     * cardto上是零件列表
     */
    private Boolean hasPart;
    /**
     * 司机
     */
    private DriverVO driverVO;

    /**
     * 演示从dto->vo时 ignore该字段
     */
    private String color;
}