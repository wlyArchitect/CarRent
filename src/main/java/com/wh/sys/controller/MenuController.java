package com.wh.sys.controller;

import com.wh.sys.constant.SysConstant;
import com.wh.sys.entity.Menu;
import com.wh.sys.entity.User;
import com.wh.sys.service.MenuService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.TreeNodeBuildUtils;
import com.wh.sys.utils.TreeNodeUtils;
import com.wh.sys.utils.WebUtils;
import com.wh.sys.vo.MenuVo;
import com.wh.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器:菜单项管理
 *
 * @author 万浩
 * @data 2019/11/17 10:19
 * @description
 */
@RequestMapping("/menu")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 主页最左边的菜单项,不同的用户看到不同的菜单项
     * 把简单的数据格式集合 转成 有层级关系的标准数据格式集合
     * //todo 修改...
     * 从数据库种获取的数据展示在前台tree通过layui+list数据格式
     *
     * @param menuVo
     * @return
     */
    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNodeUtils> loadIndexLeftMenuJson(MenuVo menuVo) {
        //1.得到当前登陆的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        //2.存储不同用户可用的菜单项
        List<Menu> list = null;
        //设置查询可用的菜单项
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);
        if (user.getType().equals(SysConstant.USER_TYPE_SUPER)) {
            //超级管理员
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
            //普通用户
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }
        //3.返回的结果
        List<TreeNodeUtils> nodes = new ArrayList<>();
        //把list集合中的数据放到nodes
        //就是一个单集合(没有children子集合)
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            //是否展开,前台的dtree接收true false所以需要转换
            Boolean spread = menu.getSpread().equals(SysConstant.SPREAD_TRUE) ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNodeUtils(id, pid, title, icon, href, spread, target));
        }
        System.err.println(nodes);
          /*
        List<TreeNodeUtils> treeNode = new ArrayList<>();
        *  */
          /**
         将简单的(一级展开)json数据 也就是靠parentId格式的json数据
           变成二级的json的格式,也就是标准的json格式
         */
        /*
        //将 简单数据格式转成标准数据格式 方法封装了
        for (TreeNodeUtils n1 : nodes) {
            if (n1.getPid() == 1) {
                treeNode.add(n1);
            }
            for (TreeNodeUtils n2 : nodes) {
                if (n2.getPid().equals(n1.getId())) {
                    n1.getChildren().add(n2);
                }
            }
        }
        System.out.println(treeNode);
        */
        return TreeNodeBuildUtils.build(nodes, SysConstant.TOPID);
    }

    /**
     * 加载菜单管理的左边的数据展示在前台tree,使用layui+list数据格式
     * 依靠parentId
     */
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridViewUtils loadMenuManagerLeftTreeJson(MenuVo menuVo){
        //只查询可用的
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> list=this.menuService.queryAllMenuForList(menuVo);
        List<TreeNodeUtils> nodes= new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            //是否展开,前台的dtree接收true false所以需要转换
            Boolean spread=menu.getSpread().equals(SysConstant.SPREAD_TRUE)?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNodeUtils(id, pid, title, icon, href, spread, target));
        }
        //将list的数据变成Object
        return new DataGridViewUtils(nodes);
    }

    /**
     * 加载不同菜单项对应的不同的表格数据
     * 菜单列表返回DataGridView
     * MenuVo需要两个分页参数
     */
    @RequestMapping("/loadAllMenu")
    public DataGridViewUtils loadAllMenu(MenuVo menuVo) {
        System.out.println("id:"+menuVo.getId());
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     */
    @RequestMapping("/addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
           this.menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            this.menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前菜单项是否存在子节点
     * 存在返回   >=0
     * 不存在返回 <0
     *
     */
    @RequestMapping("/checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单项(子菜单)数量
        Integer count=this.menuService.queryMenuByPid(menuVo.getId());
        if(count>0) {
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }
    /**
     * 删除菜单
     */
    @RequestMapping("/deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo) {
        try {
            //删除菜单表里的数据
            this.menuService.deleteMenuByPrimaryKey(menuVo.getId());
            //以及根据菜单id删除sys_role_menu表里面的数据
            this.menuService.deleteRoleMenuByMid(menuVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
