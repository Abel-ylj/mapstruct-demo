package cn.abel.convert;

import cn.abel.beans.dto.CarDTO;
import cn.abel.beans.dto.DriverDTO;
import cn.abel.beans.dto.PartDTO;
import cn.abel.beans.vo.CarVO;
import cn.abel.beans.vo.DriverVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 1. 引入依赖
 * 2. 抽象类或接口并标注@Mapper
 * 3. 写方法
 * 4. 获取实例去转换
 * car相关的pojo转换
 * @author : ylj
 * create at:  2025/12/13
 */
@Mapper
public abstract class CarConvert {

    public static CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);

    /**
     * carDto -> carVO
     */
    @Mappings(
            value = {
                    // 内容格式转换
                    @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.00"),
                    @Mapping(source = "publishDate", target = "publishDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
                    // 忽略指定字段
                    @Mapping(target = "color", ignore = true),
                    // 属性名称不一样进行映射
                    @Mapping(source = "brand", target = "brandName"),
                    // 对象映射, mapstruct会根据s,t对象类型匹配到映射逻辑->driverDTO2DriverVO
                    @Mapping(source = "driverDTO", target = "driverVO")
            }
    )
    public abstract CarVO dto2vo(CarDTO carDTO);

    /**
     * driverDTO --> driverVO
     * @param driverDTO
     * @return
     */
    @Mapping(source = "id", target = "driverId")
    @Mapping(source = "name", target = "fullName")
    public abstract DriverVO driverDTO2DriverVO(DriverDTO driverDTO);

    // 在自动映射完成后进入该后置逻辑
    @AfterMapping
    public void dto2voAfter(CarDTO carDTO, @MappingTarget CarVO carVO) {
        // carVO中hasPart字段赋值 , carVO中是boolean, 在CarDTO中是list
        List<PartDTO> partDTOS = carDTO.getPartDTOS();
        carVO.setHasPart(!CollectionUtils.isEmpty(partDTOS));
    }
}