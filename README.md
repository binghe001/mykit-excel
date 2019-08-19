# 作者简介: 
Adam Lu(刘亚壮)，高级软件架构师，Java编程专家，开源分布式消息引擎Mysum发起者、首席架构师及开发者，Mykit系列框架独立作者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的经验。对Hadoop、Spark、Storm等大数据框架源码进行过深度分析并具有丰富的实战经验。

# 作者联系方式
QQ：2711098650

# 框架简述
mykit-excel插件是通用的Excel导出导出框架，旨在提供通用的Excel导入导出功能

# 框架结构
mykit-excel-common: mykit-excel框架的通用工具类，提供通用的工具模板  
mykit-excel-springmvc: mykit-excel框架提供的SpringMVC模块，能够支持Web请求导出Excel  
mykit-excel-springboot: mykit-excel框架提供的SpringBoot测试模块  
测试工具类的Java类为：```io.mykit.excel.springboot.export.TestExportExcelUtils```，直接运行该类即可。  
测试SpringMVC的Java类为```io.mykit.excel.springboot.springmvc.TestExportExcelContorller```，运行SpringBoot的启动类```io.mykit.excel.springboot.MykitExcelCoreApplication```
之后，使用resources/html目录下的exportExcel.html文件导出Excel即可。如果设置的IP和端口与mykit-excel-springboot模块不同，则修改exportExcel.html文件中的IP和端口即可。
# 备注
本项目还在开发中，目前未添加到Maven中央仓库，后续开发完成会添加到Maven中央仓库

