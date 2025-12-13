# mapstruct

## 一. 简介

* 官网: https://mapstruct.org/  版本: 1.3.1.Final
* 突出转换逻辑，防止转换的细节淹没在一堆setter中

## 二. 使用

### 2.2 @Mapper

* 同类型同名属性，自动映射

* 会自动转换

  * 8种基本类型类型与其包装类型

  * 8种基本类型(包装类型)与string之间

    示例: double totalPrice -> String totalPrice

    ​		 double price -> Double price

  * Date与string之间

    示例: Date publishDate -> String publishDate

### 2.3 @Mappings 和 @Mapping

### 2.4 @AfterMapping和@MappingTarget

### 2.5 @BeanMapping

### 2.6 @InheritConfiguration

### 2.7 @InheritInverseConfiguration

