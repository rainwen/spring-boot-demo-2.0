package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.model.WeiboCheckin;
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
    WeiboCheckin save(WeiboCheckin weiboSignin);

    /**
     * 批量保存
     * @param weiboSigninList
     * @return
     */
    Iterable<WeiboCheckin> batchSave(List<WeiboCheckin> weiboSigninList);

    /**
     * 根据经纬度距离最近的
     *
     * @param lon
     * @param lat
     * @param pageable
     * @return
     */
    Page<WeiboCheckin> findNearbyByDistance(double lat, double lon, Pageable pageable);

    /**
     * 根据分类查询
     *
     * @param categoryName
     * @param pageable
     * @return
     */
    Page<WeiboCheckin> findByCategoryName(String categoryName, Pageable pageable);

    /**
     * 删除全部
     * @return
     */
    void deleteAll();

    /**
     * id查询
     * @param id
     */
    void deleteById(String id);


    /**
     * id查询
     * @param id
     */
    WeiboCheckin findById(String id);

    /**
     * 查询签到次数最多前N条
     * @param topN
     * @return
     */
    List<WeiboCheckin> findByCheckinNumDescTopN(int topN);

    /**
     * 按地址查询签到次数降序前N条
     * @param placeName
     * @param topN
     * @return
     */
    List<WeiboCheckin> findByPlaceNameOrderByCheckinNumDesc(String placeName, int topN);

}
