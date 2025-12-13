package cn.abel.beans.vo;

import lombok.Data;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@Data
public class DriverVO {
    /**
     * 驾驶员id  dto上是id
     */
    private Long driverId;
    /**
     * 驾驶员名称 dto上是name
     */
    private String fullName;
}