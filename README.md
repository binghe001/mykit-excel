# 作者简介: 
冰河，高级软件架构师，Java编程专家，大数据架构师与编程专家，信息安全高级工程师，Mykit系列开源框架创始人、核心架构师和开发者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、分布式事务与大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的架构经验。对Hadoop、Spark、Storm、Flink等大数据框架源码进行过深度分析并具有丰富的实战经验。《海量数据处理与大数据技术实战》、《MySQL开发、优化与运维实战》作者。

# 作者联系方式
QQ：2711098650

# 框架简述
mykit-excel插件是通用的Excel导出导出框架，旨在提供通用的Excel导入导出功能，支持以注解方式选择JavaBean中的部分字段导出，并提供注解指定Excel列标题和排序功能。

# 框架结构
* mykit-excel-annotation: mykit-excel框架的注解模块，提供注解标识类中的哪些字段需要导出到Excel    
* mykit-excel-common: mykit-excel框架的通用工具类，提供通用的工具模板    
* mykit-excel-servlet: mykit-excel框架提供的Web模块，能够支持Web请求导出Excel    
* mykit-excel-springmvc: mykit-excel框架提供的SpringMVC模块，能够支持Web请求导出Excel   
* mykit-excel-test: mykit-excel框架提供的常规测试模块     
* mykit-excel-springboot: mykit-excel框架提供的SpringBoot测试模块  

测试常规导出Excel工具类的Java类为：```io.mykit.excel.springboot.normal.export.TestExportExcelUtils```，直接运行该类即可。  

测试注解导出Excel工具类的Java类为：```io.mykit.excel.springboot.normal.export.TestExportExcelUtils```，直接运行该类即可。  

测试SpringMVC导出Excel的Java类为```io.mykit.excel.springboot.normal.springmvc.NormalExportExcelContorller```，运行SpringBoot的启动类```io.mykit.excel.springboot.MykitExcelCoreApplication```
之后，使用resources/html目录下的normalExportExcel.html文件导出Excel即可。如果设置的IP和端口与mykit-excel-springboot模块不同，则修改normalExportExcel.html文件中的IP和端口即可。  

测试基于注解导出Java类为```io.mykit.excel.springboot.annotation.springmvc.AnnotationExportExcelController```，运行SpringBoot的启动类```io.mykit.excel.springboot.MykitExcelCoreApplication```
之后，使用resources/html目录下的annotationExportExcel.html文件导出Excel即可。如果设置的IP和端口与mykit-excel-springboot模块不同，则修改annotationExportExcel.html文件中的IP和端口即可。  


# 使用方式

### 普通方式导出Excel
如果是普通的Java项目，只是将Excel文件导出到本地磁盘，则只需要在项目的pom.xml文件中增加如下配置  
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
创建测试JavaBean  
```
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -2987207599880734028L;
    private int id;
    private String name;
    private String sex;

    public Student(){

    }

    public Student(int id, String name, String sex){
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
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
导出的文件如下所示  
![导出文件的效果](https://github.com/sunshinelyz/mykit-excel/blob/master/img/test1_20200101100928.jpg)



###  注解方式导出Excel
如果是普通的Java项目，以注解方式将Excel文件导出到本地磁盘，则只需要在项目的pom.xml文件中增加如下配置  
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
创建测试JavaBean  
(1) 创建父类JavaBean  
```
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 3251965335162340137L;

    @ExcelColumn(isExport = true, title = "编号", sort = 2)
    private String id ;

    @ExcelColumn(isExport = true, title = "姓名", sort = 3)
    private String name;
    
    public Person(){
    }

    public Person(String id, String name){
        this.id = id;
        this.name = name;
    }
}
```
(2) 创建子类JavaBean
```
@Data
public class Student extends Person{
    private static final long serialVersionUID = -6180523202831503132L;

    @ExcelColumn(isExport = false, title = "班级编号", sort = 1)
    private String classNo;

    private Integer score;

    @ExcelColumn(isExport = true, title = "爱好", sort = 5)
    private String hobby;
    
    public Student(){
    }

    public Student(String id, String name, String classNo, Integer score, String hobby){
        super(id, name);
        this.classNo = classNo;
        this.score = score;
        this.hobby = hobby;
    }
}
```

接下来，在程序中按照如下方式导出Excel文件即可  
```
public class TestAnnotationExportExcelUtils {
    public static void main(String[] args) throws FileNotFoundException {
        // 准备数据
        List<Student> list = new ArrayList<Student>();
        for (int i = 1; i <= 10; i++) {
            list.add(new Student("00" + i, "张三", "001", 100, "篮球"));
        }
        AnnotationExcelExportUtils<Student> utils = new AnnotationExcelExportUtils<Student>();
        utils.exportExcel("用户导出", list, new FileOutputStream("e:/E:/test.xls"), Student.class, AnnotationExcelExportUtils.EXCEL_FILE_2003);
    }
}
```
导出的文件如下所示  
![导出文件的效果](https://github.com/sunshinelyz/mykit-excel/blob/master/img/test2_20200101101121.jpg)


### Web方式导出Excel
如果是基于Java Web或Spring MVC项目，需要导出Excel，则需要在项目的pom.xml文件中，加入如下配置
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-servlet</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
创建测试JavaBean  
```
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -2987207599880734028L;
    private int id;
    private String name;
    private String sex;

    public Student(){

    }

    public Student(int id, String name, String sex){
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
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
导出的文件如下所示  
![导出文件的效果](https://github.com/sunshinelyz/mykit-excel/blob/master/img/test1_20200101100928.jpg)


### 注解方式导出Excel
如果是基于Java Web或Spring MVC项目，需要基于注解导出Excel，则需要在项目的pom.xml文件中，加入如下配置
```
<dependency>
    <groupId>io.mykit.excel</groupId>
    <artifactId>mykit-excel-servlet</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
创建测试JavaBean  
(1) 创建父类JavaBean  
```
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 3251965335162340137L;

    @ExcelColumn(isExport = true, title = "编号", sort = 2)
    private String id ;

    @ExcelColumn(isExport = true, title = "姓名", sort = 3)
    private String name;
    
    public Person(){
    }

    public Person(String id, String name){
        this.id = id;
        this.name = name;
    }
}
```
(2) 创建子类JavaBean
```
@Data
public class Student extends Person{
    private static final long serialVersionUID = -6180523202831503132L;

    @ExcelColumn(isExport = false, title = "班级编号", sort = 1)
    private String classNo;

    private Integer score;

    @ExcelColumn(isExport = true, title = "爱好", sort = 5)
    private String hobby;
    
    public Student(){
    }

    public Student(String id, String name, String classNo, Integer score, String hobby){
        super(id, name);
        this.classNo = classNo;
        this.score = score;
        this.hobby = hobby;
    }
}
```
接下来，在程序中按照如下方式导出Excel文件即可
```
@Controller
@RequestMapping(value = "/annotation/export")
public class AnnotationExportExcelController {

    @RequestMapping("/excel")
    public void getExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 准备数据
        List<Student> list = new ArrayList<Student>();
        for (int i = 1; i <= 10; i++) {
            list.add(new Student("00" + i, "张三", "001", 100, "篮球"));
        }
        String fileName = "springboot_excel";
        ExportExcelWrapper<Student> wrapper = new ExportExcelWrapper<Student>();
        wrapper.annotationExportExcel(fileName, fileName, list, Student.class, response, ExportExcelWrapper.EXCEL_FILE_2003);
    }
}
```

导出的文件如下所示  
![导出文件的效果](https://github.com/sunshinelyz/mykit-excel/blob/master/img/test2_20200101101121.jpg)

### 前端测试代码
前端测试代码放在mykit-excel-springboot模块的```src/main/resources/html```目录下，修改html文件中的连接地址后，将其放在Tomcat或其他Web容器中，进行测试即可。

# 测试方式
### 常规测试
直接运行mykit-excel-springboot项目中的io.mykit.excel.springboot.normal.export.TestExportExcelUtils类即可

### 基于注解的常规测试
直接运行mykit-excel-springboot项目中的io.mykit.excel.springboot.annotation.export.TestAnnotationExportExcelUtils类即可

### Web测试
启动mykit-excel-springboot项目，即运行mykit-excel-springboot项目中的io.mykit.excel.springboot.MykitExcelCoreApplication类，
将mykit-excel-springboot项目的src/main/resources/html下的normalExportExcel.html文件发布到Tomcat等Web容器中访问normalExportExcel.html文件的连接地址，
打开页面点击“Submit”按钮即可。

### 基于注解的Web测试
启动mykit-excel-springboot项目，即运行mykit-excel-springboot项目中的io.mykit.excel.springboot.MykitExcelCoreApplication类，
将mykit-excel-springboot项目的src/main/resources/html下的annotationExportExcel.html文件发布到Tomcat等Web容器中访问annotationExportExcel.html文件的连接地址，
打开页面点击“Submit”按钮即可。


