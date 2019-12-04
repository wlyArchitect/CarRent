package com.wh.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 将TreeNodeUtils工具类获取的一级json数据变成二级json(展开)数据
 *
 * 用于展开的左边的菜单项
 *
 * @author 万浩
 * @data 2019/11/17 11:12
 * @description
 */
public class TreeNodeBuildUtils {
    /**
     * 说明下:根节点
     * 是以pid=1的开始为根节点,因为是二级展开
     * 如果三级展开可用修改pid=0的开始为根节点即可
     *
     * @param nodes
     * @param topPid
     * @return
     */
    public static List<TreeNodeUtils> build(List<TreeNodeUtils> nodes, Integer topPid) {
        List<TreeNodeUtils> treeNodes = new ArrayList<>();
        for (TreeNodeUtils n1 : nodes) {
            //确定根节点,pid=1的为根节点
            if (n1.getPid().equals(topPid)) {
                treeNodes.add(n1);
            }
            for (TreeNodeUtils n2 : nodes) {
                //确定二级节点
                if (n2.getPid().equals(n1.getId())) {
                    //那么在一级节点的基础上,添加二级节点
                    n1.getChildren().add(n2);
                }
            }
        }
        //这样操作完以后就是一个完整的TreeNodeUtils对象数组
        //也就是一个标准的dtree数据格式的数组
        return treeNodes;
    }
}