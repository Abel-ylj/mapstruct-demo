# mapstruct

## 一. 简介

* 官网: https://mapstruct.org/  版本: 1.3.1.Final
* 突出转换逻辑，防止转换的细节淹没在一堆setter中

## 二. 使用

### 2.2 @Mapper 

* 同类型同名属性，自动映射

* （默认映射关系）会自动转换

  * 8种基本类型类型与其包装类型

  * 8种基本类型(包装类型)与string之间

    示例: double totalPrice -> String totalPrice

    ​		 double price -> Double price

  * Date与string之间

    示例: Date publishDate -> String publishDate

### 2.3 @Mappings 和 @Mapping

* 指定属性之间的映射关系
  * 日期格式转换:  dateFormat = "yyyy-MM-dd "

### 2.4 @AfterMapping和@MappingTarget

### 2.5 @BeanMapping

### 2.6 @InheritConfiguration

### 2.7 @InheritInverseConfiguration



## 注意点

List<CarDTO> -> List<CarVO>  转换时如果需要将其中的DriverDTO -> DriverVO 有两种方式

方式一:  在CarConvert转换类中，写上DriverVO convert(DriverDTO driverDTO)的映射逻辑

```java
@Mapping(source = "id", target = "driverId")
@Mapping(source = "name", target = "fullName")
public abstract DriverVO driverDTO2DriverVO(DriverDTO driverDTO);
```

方式二:  如果转换逻辑复杂且放在了另一个DriverConvert类中，那么需要引入

​		引入方式一:  直接在CarConvert 引入public static final DriverConvert DRIVER_CONVERT = Mappers.getMapper(DriverConvert.class); ，然后写上driverDTOToVO的映射

```java
public abstract class CarConvert {

    public static CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);
  
    public static final DriverConvert DRIVER_CONVERT = Mappers.getMapper(DriverConvert.class);
    public DriverVO mapDriverDTOToVO(DriverDTO driverDTO) {
      return DRIVER_CONVERT.driverDTO2DriverVO(driverDTO);
    }
 
}
```

​		引入方式二: 前提将convert都交给spring管理，然后使用注解标注查找bean的范围

```java
@Mapper(componentModel = "spring", uses = DriverConvert.class) // 依赖从spring拿
public abstract class CarConvert {
      @Mappings(
            value = {
										//.....
                    // 对象映射, mapstruct会根据s,t对象类型匹配到映射逻辑->driverDTO2DriverVO
                    @Mapping(source = "driverDTO", target = "driverVO")
            }
    )
    public abstract CarVO dto2vo(CarDTO carDTO);
}

@Mapper(componentModel = "spring") // 交给spring管理
public abstract class DriverConvert {

    @Mapping(source = "id", target = "driverId")
    @Mapping(source = "name", target = "fullName")
    public abstract DriverVO driverDTO2DriverVO(DriverDTO driverDTO);

}
```
