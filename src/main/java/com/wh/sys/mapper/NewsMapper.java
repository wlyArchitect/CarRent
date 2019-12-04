package com.wh.sys.mapper;

import com.wh.sys.entity.News;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    /**
     * 查询所有的公告
     */
    List<News> queryAllNews(News news);
}