package com.rainwen.data.elasticsearch.repository;

import com.rainwen.data.elasticsearch.model.WeiboCheckin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author rain.wen
 * @since 2018/7/23 18:57
 */
public interface WeiboCheckinRepository extends ElasticsearchRepository<WeiboCheckin, String>{

    /**
     * 根据分类查询
     *
     * @param categoryName
     * @param pageable
     * @return
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"categoryName\": \"?0\"}}]}}")
    Page<WeiboCheckin> findByCategoryName(String categoryName, Pageable pageable);
}
