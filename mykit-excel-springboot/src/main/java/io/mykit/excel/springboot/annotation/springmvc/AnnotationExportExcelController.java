/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.excel.springboot.annotation.springmvc;

import io.mykit.excel.springboot.annotation.bean.Student;
import io.mykit.excel.springmvc.ExportExcelWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
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
