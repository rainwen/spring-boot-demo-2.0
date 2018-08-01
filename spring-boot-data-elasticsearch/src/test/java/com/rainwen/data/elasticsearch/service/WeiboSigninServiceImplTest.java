package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.BaseTest;
import com.rainwen.data.elasticsearch.model.Location;
import com.rainwen.data.elasticsearch.model.WeiboCheckin;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author rain.wen
 * @since 2018/7/24 0:02
 */
public class WeiboSigninServiceImplTest extends BaseTest {

    @Autowired
    private WeiboSigninService weiboSigninService;

    @org.junit.Test
    public void save() {
        WeiboCheckin weiboCheckin = new WeiboCheckin();
        weiboCheckin.setPoiId("001001");
        weiboCheckin.setPlacename("车公庙KFC");
        weiboCheckin.setCategoryName("guangdong");
        weiboCheckin.setAddress("广东省深圳市福田区车公庙丰盛町C区负一层");
        Location location = new Location(22.541749, 114.031106);
        weiboCheckin.setLocation(location);
        weiboCheckin.setCreateTime(System.currentTimeMillis());
        weiboSigninService.save(weiboCheckin);
    }

    @org.junit.Test
    public void batchSave() {
        List<WeiboCheckin> weiboSigninList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            WeiboCheckin weiboCheckin = new WeiboCheckin();
            weiboCheckin.setPoiId("1001" + RandomStringUtils.randomNumeric(5));
            weiboCheckin.setPlacename("车公庙KFC");
            weiboCheckin.setCategoryName("aaa");
            weiboCheckin.setCityCode("0755");
            weiboCheckin.setAddress("广东省深圳市福田区车公庙丰盛町C区负一层");
            Location location = new Location(22.541749, 114.031106);
            weiboCheckin.setLocation(location);
            weiboCheckin.setCreateTime(System.currentTimeMillis());
            weiboSigninList.add(weiboCheckin);
        }
        Iterable iterable = weiboSigninService.batchSave(weiboSigninList);
        iterable.forEach(item -> System.out.println(item));
    }

    @org.junit.Test
    public void findNearbyByDistanceTest() {
        double lat = 33.65883;
        double lon = 116.89328;
        Pageable page = PageRequest.of(0, 100);
        Page<WeiboCheckin> weiboCheckinPage = weiboSigninService.findNearbyByDistance(lat, lon, page);
        Assert.assertTrue(weiboCheckinPage.getTotalElements() > 1);

    }

    @Test
    public void findByCategoryName() {
        String categoryName = "公园户外";
        Pageable page = PageRequest.of(0, 10);
        Page<WeiboCheckin> weiboSigninPage = weiboSigninService.findByCategoryName(categoryName, page);
        List<WeiboCheckin> weiboSigninList = weiboSigninPage.getContent();
        Assert.assertTrue(weiboSigninList.size() > 0);
    }

    @Test
    public void selectById() {
        String id = "B2094652DA68A6FE4999";
        WeiboCheckin weiboCheckin = weiboSigninService.findById(id);
        Assert.assertTrue(weiboCheckin != null);
    }

    @Test
    public void deleteAll() {
        weiboSigninService.deleteAll();
    }



    /**
     * 导入
     * @throws IOException
     */
    @Test
    public void importFromFile() throws IOException {
        String file = "E:\\project\\WeiboDataShare\\guangdong.csv";
        FileInputStream fileInputStream = new FileInputStream(new File(file));
        /**
         * http://rensanning.iteye.com/blog/2336005
         * Java处理文件BOM头的方式推荐
         * https://www.cnblogs.com/littleatp/archive/2011/11/07/4354497.html
         */
        BOMInputStream bomInputStream = new BOMInputStream(fileInputStream);
        InputStreamReader isr = new InputStreamReader(bomInputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        long begin = System.currentTimeMillis();
        int batchSize = 20;
        List<WeiboCheckin> weiboCheckins = new ArrayList<>(batchSize);
        while ((line = br.readLine()) != null) {
            String [] data = line.split(",");
            if(data.length != 9) {
                logger.info("错误数据:{}", line);
                continue;
            }
            try {
                WeiboCheckin weiboCheckin = new WeiboCheckin();
                weiboCheckin.setPoiId(data[0]);
                weiboCheckin.setPlacename(data[1]);
                weiboCheckin.setAddress(data[2]);
                Double lat = Double.valueOf(data[4]);
                Double lon = Double.valueOf(data[3]);
                Location location = new Location(lat, lon);
                weiboCheckin.setLocation(location);
                weiboCheckin.setCityCode(data[5]);
                weiboCheckin.setCategoryName(data[6]);
                weiboCheckin.setCheckinNum(Integer.valueOf(data[7]));
                weiboCheckin.setPhotoNum(Integer.valueOf(data[8]));
                weiboCheckin.setCreateTime(System.currentTimeMillis());
                weiboCheckins.add(weiboCheckin);
                if(weiboCheckins.size() == batchSize) {
                    weiboSigninService.batchSave(weiboCheckins);
                    weiboCheckins = new ArrayList<>(batchSize);
                }
            } catch (Exception e) {
                logger.error("异常", e);
                logger.info("{}", line);
            }
        }
        //保存最后一批
        if(weiboCheckins.size() > 0) {
            weiboSigninService.batchSave(weiboCheckins);
        }
        logger.info("耗时:{}", System.currentTimeMillis() - begin);
    }
}
