package com.wh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.sys.entity.News;
import com.wh.sys.mapper.NewsMapper;
import com.wh.sys.service.NewsService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 万浩
 * @data 2019/12/1 11:20
 * @description
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询 所有的公告信息
     * @param newsVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllNews(NewsVo newsVo) {
        //分页插件的使用
        Page<Object> page= PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
        List<News> data = this.newsMapper.queryAllNews(newsVo);
//        System.err.println(data.get(0).getId());
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 添加公告
     * @param newsVo
     */
    @Override
    public void addNews(NewsVo newsVo) {
        this.newsMapper.insertSelective(newsVo);
    }

    @Override
    public void deleteNews(Integer newsid) {
        this.newsMapper.deleteByPrimaryKey(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteNews(integer);
        }
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        this.newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    /**
     * 查询一个公告
     * @param id
     * @return
     */
    @Override
    public News queryNewsById(Integer id) {
        return this.newsMapper.selectByPrimaryKey(id);
    }
}
