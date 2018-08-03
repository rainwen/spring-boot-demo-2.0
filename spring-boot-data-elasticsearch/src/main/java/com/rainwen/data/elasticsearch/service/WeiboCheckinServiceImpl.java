package com.rainwen.data.elasticsearch.service;

import com.rainwen.data.elasticsearch.model.WeiboCheckin;
import com.rainwen.data.elasticsearch.repository.WeiboCheckinRepository;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class WeiboCheckinServiceImpl implements WeiboCheckinService {

    @Autowired
    private WeiboCheckinRepository weiboSigninRepository;

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
        if (optionalWeiboCheckin.isPresent()) {
            return optionalWeiboCheckin.get();
        }
        return null;
    }

    /**
     * 查询签到次数最多前多少名
     *
     * @param topN
     * @return
     */
    @Override
    public List<WeiboCheckin> findByCheckinNumDescTopN(int topN) {
        Pageable pageable = PageRequest.of(0, topN);
        //构建原生查询对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable);
        //按签到次数降序
        SortBuilder sortBuilder = SortBuilders.fieldSort("checkinNum").order(SortOrder.DESC);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withSort(sortBuilder).build();
        return elasticsearchTemplate.queryForList(searchQuery, WeiboCheckin.class);
    }

    /**
     * 按地址查询签到次数降序前N条
     *
     * @param placeName
     * @param topN
     * @return
     */
    @Override
    public List<WeiboCheckin> findByPlaceNameOrderByCheckinNumDesc(String placeName, int topN) {
        Pageable pageable = PageRequest.of(0, topN);
        //查询条件
        QueryBuilder queryBuilder = new MatchQueryBuilder("placename", placeName);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withFilter(queryBuilder).withPageable(pageable);
        //按签到次数降序
        SortBuilder sortBuilder = SortBuilders.fieldSort("checkinNum").order(SortOrder.DESC);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withSort(sortBuilder).build();
        return elasticsearchTemplate.queryForList(searchQuery, WeiboCheckin.class);
    }
}
