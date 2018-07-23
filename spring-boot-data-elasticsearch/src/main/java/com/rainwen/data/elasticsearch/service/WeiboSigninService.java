package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.model.WeiboSignin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author rain.wen
 * @since 2018/7/23 18:57
 */
public interface WeiboSigninService {

    /**
     * 保存
     * @param weiboSignin
     * @return
     */
    WeiboSignin save(WeiboSignin weiboSignin);

    /**
     * 批量保存
     * @param weiboSigninList
     * @return
     */
    Iterable<WeiboSignin> batchSave(List<WeiboSignin> weiboSigninList);

    /**
     * 根据经纬度查询附近的签到
     * @param lon
     * @param lat
     * @param pageable
     * @return
     */
    Page<WeiboSignin> findNearbyByLonAndLat(String lon, String lat, Pageable pageable);
}
