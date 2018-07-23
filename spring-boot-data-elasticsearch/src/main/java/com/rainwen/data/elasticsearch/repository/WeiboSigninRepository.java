package com.rainwen.data.elasticsearch.repository;

import com.rainwen.data.elasticsearch.model.WeiboSignin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author rain.wen
 * @since 2018/7/23 18:57
 */
public interface WeiboSigninRepository extends ElasticsearchRepository<WeiboSignin, String>{

}
