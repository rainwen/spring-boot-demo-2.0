package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.BaseTest;
import com.rainwen.data.elasticsearch.model.WeiboSignin;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rain.wen
 * @since 2018/7/24 0:02
 */
public class WeiboSigninServiceTest extends BaseTest {

    @Autowired
    private WeiboSigninService weiboSigninService;

    @org.junit.Test
    public void save() {
        WeiboSignin weiboSignin = new WeiboSignin();
        weiboSignin.setPoiId("001001");
        weiboSignin.setTitle("testtest");
        weiboSignin.setCategoryName("aaa");
        weiboSignin.setAddress("深圳");
        weiboSignin.setLat("100.1111");
        weiboSignin.setLon("100.1111");
        weiboSigninService.save(weiboSignin);
    }

    @org.junit.Test
    public void batchSave() {
        List<WeiboSignin> weiboSigninList = new ArrayList<>(10);
        for(int i = 0 ; i < 10; i ++) {
            WeiboSignin weiboSignin = new WeiboSignin();
            weiboSignin.setPoiId("001001" + RandomStringUtils.randomNumeric(5));
            weiboSignin.setTitle("testtest");
            weiboSignin.setCategoryName("aaa");
            weiboSignin.setAddress("深圳");
            weiboSignin.setLat("100.1111");
            weiboSignin.setLon("100.1111");
            weiboSigninList.add(weiboSignin);
        }
        Iterable iterable = weiboSigninService.batchSave(weiboSigninList);
        iterable.forEach(item -> System.out.println(item));
    }

    @org.junit.Test
    public void findNearbyByLonAndLat() {

    }
}
