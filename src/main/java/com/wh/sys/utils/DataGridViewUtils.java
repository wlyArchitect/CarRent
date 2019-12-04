package com.wh.sys.utils;

/**
 * 封装layui数据表格的数据对象
 * 满足对d-tree对json格式数据的要求(返回一个对象)
 * 将传入的List集合的数据也就是(二级展开的json数据)转换成Object类型的数据
 *
 * //todo 修改... layui+list数据格式
 * 由于后台数据库查询出的数据是list的平级数据,使用tree的第4种layui+list风格
 * 细节这里的list是指数据库查询出的数据
 * @author 万浩
 * @data 2019/11/18 20:40
 * @description
 *
 */
public class DataGridViewUtils {
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;

    public DataGridViewUtils() {
    }
    public DataGridViewUtils(Object data) {
        super();
        this.data = data;
    }

    /**
     * 分页需要使用到
     * @param count
     * @param data
     */
    public DataGridViewUtils(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
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
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
