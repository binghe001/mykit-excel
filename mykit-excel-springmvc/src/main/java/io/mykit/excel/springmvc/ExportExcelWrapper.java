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
package io.mykit.excel.springmvc;

import io.mykit.excel.utils.ExportExcelUtils;
import io.mykit.excel.utils.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Collection;

/**
 * @author binghe
 * @version 1.0.0
 * @description 响应Web请求的导出Excel
 */
public class ExportExcelWrapper<T> extends ExportExcelUtils<T> {
    /**
     * <p>
     * 导出带有头部标题行的Excel <br>
     * 时间格式默认：yyyy-MM-dd HH:mm:ss <br>
     * </p>
     *
     * @param fileName 文件名称
     * @param title 表格标题
     * @param headers 头部标题集合
     * @param dataset 数据集合
     * @param response HttpServletResponse
     * @param version 2003 或者 2007，不传时默认生成2003版本
     */
    public void exportExcel(String fileName, String title, String[] headers, Collection<T> dataset, HttpServletResponse response, String version) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xls");
            if(StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())){
                exportExcel2003(title, headers, dataset, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
            }else{
                exportExcel2007(title, headers, dataset, response.getOutputStream(), "yyyy-MM-dd HH:mm:ss");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
