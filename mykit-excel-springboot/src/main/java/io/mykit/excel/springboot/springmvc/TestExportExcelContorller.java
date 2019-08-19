/**
 * Copyright 2019-2999 the original author or authors.
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
package io.mykit.excel.springboot.springmvc;

import io.mykit.excel.springboot.bean.Student;
import io.mykit.excel.springmvc.ExportExcelWrapper;
import io.mykit.excel.utils.ExportExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试导出Excel的Controller
 */
@Controller
@RequestMapping(value = "/export")
public class TestExportExcelContorller {
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
}
