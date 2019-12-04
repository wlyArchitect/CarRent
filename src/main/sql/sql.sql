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