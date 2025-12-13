package cn.abel.beans.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@Data
public class CarDTO {
    private Long id;
    private String vin;
    /**
     * 裸车价格
     */
    private double price;
    /**
     * 落地价
     */
    private double totalPrice;
    /**
     * 生产日期
     */
    private Date publishDate;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 零件列表
     */
    private List<PartDTO> partDTOS;
    /**
     * 司机
     */
    private DriverDTO driverDTO;

    /**
     * 颜色 演示ignore
     */
    private String color;
}