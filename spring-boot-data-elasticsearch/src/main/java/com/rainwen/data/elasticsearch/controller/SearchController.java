package com.rainwen.data.elasticsearch.controller;

import com.rainwen.data.elasticsearch.model.WeiboCheckin;
import com.rainwen.data.elasticsearch.service.WeiboCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class SearchController {

    @Autowired
    private WeiboCheckinService weiboCheckinService;


    /**
     * 按地址查询签到次数降序前N条
     *
     * @return
     */
    @RequestMapping("/search")
    public List<WeiboCheckin> search(@RequestParam(name = "placeName", required = false) String placeName,
                                    @RequestParam(name = "topN", defaultValue = "10") Integer topN) {
        List<WeiboCheckin> weiboCheckinList = weiboCheckinService.findByPlaceNameOrderByCheckinNumDesc(placeName, topN);
        return weiboCheckinList;
    }

    /**
     * 按距离最近查询前N条
     *
     * @return
     */
    @RequestMapping("/searchNearby")
    public List<WeiboCheckin> searchNearby(@RequestParam(name = "lon") Double lon,
                                           @RequestParam(name = "lat") Double lat,
                                           @RequestParam(name = "topN", defaultValue = "10") Integer topN) {
        Pageable page = PageRequest.of(0, topN);

        Page<WeiboCheckin> weiboCheckinPage = weiboCheckinService.findNearbyByDistance(lat, lon, page);

        return weiboCheckinPage.getContent();
    }

}