package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.model.WeiboSignin;
import com.rainwen.data.elasticsearch.repository.WeiboSigninRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rain.wen
 * @since 2018/7/23 19:33
 */
@Service
public class WeiboSigninServiceImpl implements WeiboSigninService {

    @Autowired
    private WeiboSigninRepository weiboSigninRepository;

    /**
     * 保存
     * @param weiboSignin
     * @return
     */
    public WeiboSignin save(WeiboSignin weiboSignin) {
        weiboSigninRepository.save(weiboSignin);
        return weiboSignin;
    }

    /**
     * 批量保存
     * @param weiboSigninList
     * @return
     */
    public Iterable<WeiboSignin> batchSave(List<WeiboSignin> weiboSigninList) {
        return weiboSigninRepository.saveAll(weiboSigninList);
    }

    /**
     * 根据经纬度查询附近的签到
     *
     * @param lon
     * @param lat
     * @param pageable
     * @return
     */
    public Page<WeiboSignin> findNearbyByLonAndLat(String lon, String lat, Pageable pageable) {
        return null;
    }


}
