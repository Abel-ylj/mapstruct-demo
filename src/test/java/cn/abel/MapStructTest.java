package cn.abel;

import cn.abel.beans.MapStructApplication;
import cn.abel.beans.dto.CarDTO;
import cn.abel.beans.dto.DriverDTO;
import cn.abel.beans.dto.PartDTO;
import cn.abel.beans.vo.CarVO;
import cn.abel.beans.vo.DriverVO;
import cn.abel.beans.vo.VehicleVO;
import cn.abel.convert.CarConvert;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapStructApplication.class)
public class MapStructTest {

    /**
     * 关闭默认映射功能(字段名称相同则映射)，每一条映射规则由自己指定
     * 场景: A对象到B对象有100个默认映射属性。但我只要映射其中某几条
     */
    @Test
    public void test4() {
        CarDTO carDTO = buildCarDTO();
        VehicleVO vehicleVO = CarConvert.INSTANCE.carDTO2vehicleVO(carDTO);
        System.out.println(carDTO);
        System.out.println(vehicleVO);
    }

    /**
     * 批量映射
     * List<CarDto> -> List<CarVO>
     */
    @Test
    public void test3() {
        ArrayList<CarDTO> carDTOS = Lists.newArrayList(buildCarDTO(), buildCarDTO2());
        // 方式一(不推荐)
//        List<CarVO> carVOS = new ArrayList<>();
//        for (CarDTO carDTO : carDTOS) {
//            carVOS.add(CarConvert.INSTANCE.dto2vo(carDTO));
//        }

        // 方式二(推荐)
        List<CarVO> carVOS = CarConvert.INSTANCE.dtos2vos(carDTOS);
        System.out.println(carVOS);
    }


    /**
     * mapstruct优化
     */
    @Test
    public void test2() {
        CarDTO carDTO = buildCarDTO();
        CarVO carVO = CarConvert.INSTANCE.dto2vo(carDTO);
        System.out.println(carDTO);
        System.out.println(carVO);
        System.out.println("done");
    }

    @Test
    public void test1() {
        CarDTO carDTO = buildCarDTO();
        // 转化dto->vo
        CarVO carVO = new CarVO();
        carVO.setId(carDTO.getId());
        carVO.setVin(carDTO.getVin());
        carVO.setPrice(carDTO.getPrice());
        // diff 输出值不同
        double totalPrice = carDTO.getTotalPrice();
        DecimalFormat df = new DecimalFormat("#.00");
        carVO.setTotalPrice(df.format(totalPrice));
        // diff 输出值格式不同
        Date publishDate = carDTO.getPublishDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        carVO.setPublishDate(sdf.format(publishDate));

        // diff 命名不同
        carVO.setBrandName(carDTO.getBrand());
        carVO.setHasPart(CollectionUtils.isEmpty(carDTO.getPartDTOS()));

        DriverVO driverVO = new DriverVO();
        DriverDTO driverDTO = carDTO.getDriverDTO();
        driverVO.setDriverId(driverDTO.getId());
        driverVO.setFullName(driverDTO.getName());
        // diff 引用对象不同
        carVO.setDriverVO(driverVO);

        System.out.println(carVO);
        System.out.println("ok");
    }

    private CarDTO buildCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(330L);
        carDTO.setVin("vin123456789");
        carDTO.setPrice(123789.126d);
        carDTO.setTotalPrice(143789.126d);
        carDTO.setPublishDate(new Date());
        carDTO.setBrand("大众");
        // 零件
        PartDTO partDTO1 = new PartDTO();
        partDTO1.setPartId(1L);
        partDTO1.setPartName("多功能方向盘");
        PartDTO partDTO2 = new PartDTO();
        partDTO2.setPartId(2L);
        partDTO2.setPartName("智能车门");
        ArrayList<PartDTO> partDTOList = new ArrayList<>();
        partDTOList.add(partDTO1);
        partDTOList.add(partDTO2);
        carDTO.setPartDTOS(partDTOList);
        // 驾驶员
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(88l);
        driverDTO.setName("小明");
        carDTO.setDriverDTO(driverDTO);

        carDTO.setColor("白色");
        return carDTO;
    }

    private CarDTO buildCarDTO2() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(331L);
        carDTO.setVin("vin1234567999");
        carDTO.setPrice(22222.345d);
        carDTO.setTotalPrice(333333.1123d);
        carDTO.setPublishDate(new Date());
        carDTO.setBrand("吉利");
        // 零件
        ArrayList<PartDTO> partDTOList = new ArrayList<>();
        carDTO.setPartDTOS(partDTOList);
        // 驾驶员
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(66l);
        driverDTO.setName("小杨");
        carDTO.setDriverDTO(driverDTO);

        carDTO.setColor("蓝色");
        return carDTO;
    }
}