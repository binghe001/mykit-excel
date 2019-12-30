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
package io.mykit.excel.annotation;

import java.lang.annotation.*;

/**
 * @author binghe
 * @version 1.0.0
 * @description  导入导出时标注到字段上的注解
 */

@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 当前字段是否导出Excel
     * true:导出; false:不导出
     * 默认为false
     */
    boolean isExport() default false;

    /**
     * 导出的标题
     */
    String title() default "";

    /**
     * 字段排序，升序
     */
    int sort() default 0;

}
