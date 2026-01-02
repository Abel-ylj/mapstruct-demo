package cn.abel.convert;

import cn.abel.beans.dto.DriverDTO;
import cn.abel.beans.vo.DriverVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : ylj
 * create at:  2026/1/2
 */
//@Mapper(componentModel = "spring")  // 演示子对象转换(DriverDTO -> DriverVO)方式三
public abstract class DriverConvert {

    public static DriverConvert INSTANCE = Mappers.getMapper(DriverConvert.class);

    @Mapping(source = "id", target = "driverId")
    @Mapping(source = "name", target = "fullName")
    public abstract DriverVO driverDTO2DriverVO(DriverDTO driverDTO);

}
