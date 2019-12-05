-- 测试
select sysdate()
from dual;
-- md5加密,需要对传入的数据加密再传入查询
select *
from carrent.sys_user
where loginname = 'admin'
  and pwd = '123456';
--
select *
from sys_menu;
-- 查询子菜单数
select count(1)
from sys_menu
where pid = 24;
-- 角色查询菜单项
select t1.*
from sys_menu t1
       inner join sys_role_menu t2 on (t1.id = t2.mid)
where t2.rid = 1;
/**
  客户地区统计
 */
select address as name,count(1) as value from bus_customer group by address;
/*
  业务员年度业务统计
 */
select opername as name,sum(price) as value from bus_rent where DATE_FORMAT(createtime,'%Y')='2018' group by opername;
/**
   公司年度月份销售额
   通过union all进行连接,重复的就不连接

 */
select sum(price) from bus_rent where
    DATE_FORMAT(createtime,"%Y%m")='201912';


