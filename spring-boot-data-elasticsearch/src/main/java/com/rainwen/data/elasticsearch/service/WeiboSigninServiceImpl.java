package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.model.WeiboCheckin;
import com.rainwen.data.elasticsearch.repository.WeiboSigninRepository;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author rain.wen
 * @since 2018/7/23 19:33
 */
@Service
public class WeiboSigninServiceImpl implements WeiboSigninService {

    @Autowired
    private WeiboSigninRepository weiboSigninRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 保存
     *
     * @param weiboSignin
     * @return
     */
    @Override
    public WeiboCheckin save(WeiboCheckin weiboSignin) {
        weiboSigninRepository.save(weiboSignin);
        return weiboSignin;
    }

    /**
     * 批量保存
     *
     * @param weiboSigninList
     * @return
     */
    @Override
    public Iterable<WeiboCheckin> batchSave(List<WeiboCheckin> weiboSigninList) {
        return weiboSigninRepository.saveAll(weiboSigninList);
    }

    /**
     * 根据经纬度距离最近的
     *
     * @param lon
     * @param lat
     * @param pageable
     * @return
     */
    @Override
    public Page<WeiboCheckin> findNearbyByDistance(double lat, double lon, Pageable pageable) {
        //构建原生查询对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        //构建查询排序
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location", "")
                .point(lat, lon)
                .unit(DistanceUnit.METERS)
                .order(SortOrder.ASC);
        nativeSearchQueryBuilder.withSort(sortBuilder);

        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        return elasticsearchTemplate.queryForPage(searchQuery, WeiboCheckin.class);
    }

    /**
     * 根据分类查询
     *
     * @param categoryName
     * @param pageable
     * @return
     */
    @Override
    public Page<WeiboCheckin> findByCategoryName(String categoryName, Pageable pageable) {
        return weiboSigninRepository.findByCategoryName(categoryName, pageable);
    }

    @Override
    public void deleteAll() {
        weiboSigninRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        weiboSigninRepository.deleteById(id);
    }

    @Override
    public WeiboCheckin findById(String id) {
        Optional<WeiboCheckin> optionalWeiboCheckin = weiboSigninRepository.findById(id);
        if(optionalWeiboCheckin.isPresent()) {
            return optionalWeiboCheckin.get();
        }
        return null;
    }
}
