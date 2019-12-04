package com.wh.sys.utils;

import com.wh.sys.constant.SysConstant;
import org.omg.CORBA.INTERNAL;

/**
 * 菜单操作的结果
 * @author 万浩
 * @data 2019/11/20 20:24
 * @description
 */
public class ResultObj {
    /**
     * 操作状态编码
     */
    private Integer code=0;
    private String msg;

    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR=new ResultObj(SysConstant.CODE_FALSE, SysConstant.ADD_FALSE);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstant.CODE_FALSE, SysConstant.UPDATE_FALSE);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstant.CODE_FALSE, SysConstant.DELETE_FALSE);

    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR=new ResultObj(SysConstant.CODE_FALSE, SysConstant.RESET_FALSE);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS=new ResultObj(SysConstant.CODE_SUCCESS, SysConstant.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR=new ResultObj(SysConstant.CODE_FALSE, SysConstant.DISPATCH_FALSE);
    /**
     * 状态码:1有子节点 -1没有子节点
     * 从而创建对应的对象
     */
    public static final Integer STATUS_SUCCESS_CODE=1;
    public static final Integer STATUS_ERROR_CODE=-1;
    public static final ResultObj STATUS_TRUE=new ResultObj(STATUS_SUCCESS_CODE);
    public static final ResultObj STATUS_FALSE=new ResultObj(STATUS_ERROR_CODE);

    /**
     * 状态码值,是否存在子节点
     */
    public ResultObj(Integer code) {
       this.code=code;
    }

    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
