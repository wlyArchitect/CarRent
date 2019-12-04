package com.wh.sys.service;

import com.wh.sys.entity.News;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.NewsVo;
import com.wh.sys.vo.NewsVo;

/**
 * 服务层: 角色的日志
 * @author 万浩
 * @data 2019/11/29 16:55
 * @description
 */
public interface NewsService {
    /**
     * 查询所有公告:分页显示
     * table数据表格 前台展示list数据格式
     * @param newsVo
     * @return
     */
    public DataGridViewUtils queryAllNews(NewsVo newsVo);
    /**
     * 添加公告
     * @param newsVo
     */
    public void addNews(NewsVo newsVo);
    /**
     * 修改公告
     * @param newsVo
     */
    public void updateNews(NewsVo newsVo);
    /**
     * 根据id删除公告
     * @param newsid
     */
    public void deleteNews(Integer newsid);
    /**
     * 批量删除公告
     * @param ids
     */
    public void deleteBatchNews(Integer [] ids);

    /**
     * 查询一个公告
     * @param id
     * @return
     */
    public News queryNewsById(Integer id);

}
