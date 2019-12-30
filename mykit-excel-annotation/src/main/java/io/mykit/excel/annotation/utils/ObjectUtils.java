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
package io.mykit.excel.annotation.utils;

import java.util.Collection;

/**
 * @author binghe
 * @version 1.0.0
 * @description 对象工具类
 */
public class ObjectUtils {

    public static boolean isEmpty(Collection<?> c){
        return c == null || c.isEmpty();
    }
    public static boolean isEmpty(Object[] objs){
        return objs == null || objs.length <= 0;
    }
}
