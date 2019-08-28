# Java SSM基于SSM框架的人力资源管理后台系统

用到的技术点有：
* 框架：SSM
* 数据库：MySQL

## 一、准备
**1 数据库建表**
tbl_emp表：
```
DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp`(
`emp_id` int(11) UNSIGNED NOT NULL auto_increment,
`emp_name` VARCHAR(22) NOT NULL DEFAULT '',
 `emp_email` VARCHAR(256) NOT NULL DEFAULT '',
 `gender` CHAR(2) NOT NULL DEFAULT '',
  `department_id` int(11) NOT NULL DEFAULT 0,
PRIMARY KEY(`emp_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
```
tbl_dept表：
```
DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept`(
`dept_id` int(11) NOT NULL DEFAULT 0,
`dept_name` VARCHAR(255) NOT NULL DEFAULT '',
 `dept_leader` VARCHAR(255) NOT NULL DEFAULT ''

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
对应的实体类见bean/Employee.java和bean/Department.java。

**2 SSM项目的配置文件**

（1）首先导入项目中可能用到的依赖包：
见pom.xml.

（2）web.xml：
见WEB-INF/web.xml.

（3）Spring容器配置文件：applicationContext.xml：
见resources/applicationContext.xml.

（4）SpringMVC配置文件：springmvc.xml：
见resources/springmvc.xml.

（5）MyBasits配置文件：resources/MyBatis.xml.

**2 SSM项目的流程图**
框架流程：
![SSM框架流程](https://github.com/xueba609/JAVA.File/blob/master/%E5%9B%BE%E7%89%87/SSM.png)
