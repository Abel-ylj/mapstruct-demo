package cn.abel;

import cn.abel.beans.MapStructApplication;
import cn.abel.beans.dto.CarDTO;
import cn.abel.beans.dto.DriverDTO;
import cn.abel.beans.dto.PartDTO;
import cn.abel.beans.vo.CarVO;
import cn.abel.beans.vo.DriverVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author : ylj
 * create at:  2025/12/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapStructApplication.class)
public class MapStructTest {

    @Test
    public void test1() {
        CarDTO carDTO = buildCarDTO();
        // 转化dto->vo
        CarVO carVO = new CarVO();
        carVO.setId(carDTO.getId());
        carVO.setVin(carDTO.getVin());
        carVO.setPrice(carDTO.getPrice());
        // diff
        double totalPrice = carDTO.getTotalPrice();
        DecimalFormat df = new DecimalFormat("#.00");
        carVO.setTotalPrice(df.format(totalPrice));
        // diff
        Date publishDate = carDTO.getPublishDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        carVO.setPublishDate(sdf.format(publishDate));

        carVO.setBrandName(carDTO.getBrand());
        carVO.setHasPart(CollectionUtils.isEmpty(carDTO.getPartDTOS()));

        DriverVO driverVO = new DriverVO();
        DriverDTO driverDTO = carDTO.getDriverDTO();
        driverVO.setDriverId(driverDTO.getId());
        driverVO.setFullName(driverDTO.getName()); // diff
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
        return carDTO;
    }
}