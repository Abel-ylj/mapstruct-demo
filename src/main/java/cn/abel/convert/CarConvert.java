package cn.abel.convert;

import cn.abel.beans.dto.CarDTO;
import cn.abel.beans.vo.CarVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
                    @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.00"),
                    @Mapping(source = "publishDate", target = "publishDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
            }
    )
    public abstract CarVO dto2vo(CarDTO carDTO);

}