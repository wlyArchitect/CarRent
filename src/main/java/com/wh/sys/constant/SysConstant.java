package com.wh.sys.constant;

/**
 * 常量接口
 *
 * @author wh
 * @data 2019/11/15 20:37
 * @description
 */
public class SysConstant {
    public static final String USER_LOGIN_ERROR = "用户名或者密码错误!";
    /**
     * pid=1的sys_menu表中的菜单就是根菜单项
     */
    public static final Integer TOPID=1;
    /**
     * 菜单项是否可用:  1可用 0不可用
     */
    public static final Integer AVAILABLE_TRUE = 1;
    public static final Integer AVAILABLE_FALSE = 0;
    /**
     * 用户类型: 1超级管理员  2普通用户
     */
    public static final Integer USER_TYPE_SUPER = 1;
    public static final Integer USER_TYPE_NORMAL = 2;
    /**
     * 菜单项是否展开
     */
    public static final Integer SPREAD_TRUE = 1;
    public static final Integer SPREAD_FALSE = 0;
    /**
     * 菜单项 操作状态
     */
    public static final String ADD_SUCCESS="添加成功";
    public static final String ADD_FALSE="添加失败";
    public static final String DELETE_SUCCESS="删除成功";
    public static final String DELETE_FALSE="删除失败";
    public static final String UPDATE_SUCCESS="修改成功";
    public static final String UPDATE_FALSE="修改失败";
    public static final String RESET_SUCCESS="重置成功";
    public static final String RESET_FALSE="重置失败";
    public static final String DISPATCH_SUCCESS="分配成功";
    public static final String DISPATCH_FALSE="分配失败";
    /**
     * 操作成功与失败
     */
    public static final Integer CODE_SUCCESS=1;
    public static final Integer CODE_FALSE=-1;

    /**
     * 公共常量
     */
    public static final Integer CODE_ZERO =0 ;
    public static final Integer CODE_ONE =1 ;
    public static final Integer CODE_TWO =2 ;
    /**
     * 默认密码
     */
    public static final String USER_DEFAULT_PWD = "123456";

    /**
     * 临时文件标记
     */
    public static final String FILE_UPLOAD_TEMP = "_temp";
    /**
     * 默认图片地址
     */
    public static final Object DEFAULT_CAR_IMG = "images/defaultcarimage.png";
    /**
     * 单号前缀
     */
    public static final String CAR_ORDER_CZ = "CZ";
    public static final String CAR_ORDER_JC = "JC";
    /**
     * 汽车归还状态: false 0
     *             true  1
      */
    public static final Integer RENT_BACK_FALSE = 0;
    public static final Integer RENT_BACK_TRUE = 1;
    /**
     * 汽车出租状态: false 0  未出租
     *             true  1  已出租
     */
    public static final Integer RENT_CAR_FALSE =0 ;
    public static final Integer RENT_CAR_TRUE =1 ;
}
