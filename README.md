# 作者简介: 
冰河，高级软件架构师，Java编程专家，大数据架构师与编程专家，信息安全高级工程师，Mykit系列开源框架创始人、核心架构师和开发者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、分布式事务与大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的架构经验。对Hadoop、Spark、Storm、Flink等大数据框架源码进行过深度分析并具有丰富的实战经验。《海量数据处理与大数据技术实战》、《MySQL开发、优化与运维实战》作者。

# 作者联系方式
QQ：2711098650

# 框架简述
mykit-excel插件是通用的Excel导出导出框架，旨在提供通用的Excel导入导出功能

# 框架结构
mykit-excel-annotation: mykit-excel框架的注解模块，提供注解标识类中的哪些字段需要导出到Excel  
mykit-excel-common: mykit-excel框架的通用工具类，提供通用的工具模板  
mykit-excel-springmvc: mykit-excel框架提供的SpringMVC模块，能够支持Web请求导出Excel  
mykit-excel-springboot: mykit-excel框架提供的SpringBoot测试模块  
测试工具类的Java类为：```io.mykit.excel.springboot.normal.export.TestExportExcelUtils```，直接运行该类即可。  
测试SpringMVC的Java类为```io.mykit.excel.springboot.normal.springmvc.NormalExportExcelContorller```，运行SpringBoot的启动类```io.mykit.excel.springboot.MykitExcelCoreApplication```
之后，使用resources/html目录下的normalExportExcel.html文件导出Excel即可。如果设置的IP和端口与mykit-excel-springboot模块不同，则修改exportExcel.html文件中的IP和端口即可。

# 使用方式
1.如果是普通的Java项目，只是将Excel文件导出到本地磁盘，则只需要在项目的pom.xml文件中增加如下配置  
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
接下来，在程序中按照如下方式导出Excel文件即可  
```
public static void main(String[] args) throws Exception{
    ExportExcelUtils<Student> utils = new ExportExcelUtils<Student>();
    List<Student> list = new ArrayList<Student>();
    for (int i = 0; i < 10; i++) {
        list.add(new Student(111,"张三","男"));
        list.add(new Student(111,"李四","男"));
        list.add(new Student(111,"王五","女"));
    }
    String[] columnNames = { "ID", "姓名", "性别" };
    utils.exportExcel("用户导出", columnNames, list, new FileOutputStream("E:/test.xls"), ExportExcelUtils.EXCEL_FILE_2003);
}
```

2.如果是基于SpringMVC的Web项目，需要导出Excel，则需要在项目的pom.xml文件中，加入如下配置
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-springmvc</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
接下来，在程序中按照如下方式导出Excel文件即可
```
@RequestMapping("/excel")
public void getExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 准备数据
    List<Student> list = new ArrayList<Student>();
    for (int i = 0; i < 10; i++) {
        list.add(new Student(111,"张三","男"));
        list.add(new Student(111,"李四","男"));
        list.add(new Student(111,"王五","女"));
    }
    String[] columnNames = { "ID", "姓名", " 性别"};
    String fileName = "springboot_excel";
    ExportExcelWrapper<Student> util = new ExportExcelWrapper<Student>();
    util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelUtils.EXCEL_FILE_2003);
}
```

# 备注
本项目还在开发中，目前未添加到Maven中央仓库，后续开发完成会添加到Maven中央仓库

