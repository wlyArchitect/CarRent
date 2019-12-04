package com.wh.sys.controller;

import com.wh.sys.entity.News;
import com.wh.sys.entity.User;
import com.wh.sys.service.NewsService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import com.wh.sys.utils.WebUtils;
import com.wh.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 万浩
 * @data 2019/12/1 11:29
 * @description
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 加载公告列表返回DataGridView
     */
    @RequestMapping("/loadAllNews")
    public DataGridViewUtils loadAllNews(NewsVo newsVo) {
        System.out.println(newsVo.getPage());
        System.out.println(newsVo.getLimit());
        return this.newsService.queryAllNews(newsVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping("/addNews")
    public ResultObj addNews(NewsVo newsVo) {
        try {
            //设置添加公告的时间
            newsVo.setCreatetime(new Date());
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            //设置添加人名,也就是登录的用户名
            newsVo.setOpername(user.getRealname());
            this.newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改公告
     */
    @RequestMapping("/updateNews")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            this.newsService.updateNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("/deleteNews")
    public ResultObj deleteNews(NewsVo newsVo) {
        try {
            this.newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("/deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            this.newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("/loadNewsById")
    public News loadNewsById(Integer id) {
        return this.newsService.queryNewsById(id);
    }

}
