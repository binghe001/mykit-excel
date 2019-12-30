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
package io.mykit.excel.annotation.parser;

import io.mykit.excel.annotation.ExcelColumn;
import io.mykit.excel.annotation.compare.AnnotationFieldComparator;
import io.mykit.excel.annotation.pojo.AnnotationFieldPojo;
import io.mykit.excel.annotation.utils.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 解析Annotation
 */
public class ExcelAnnotationParser {

    private static final String SERIALVERSIONUID_FIELD = "serialVersionUID";

    /**
     * 解析带有@ExcelColumn注解的类
     * @param clazz 类的字节码
     * @param <T> 泛型类型
     * @return 排序好的AnnotationFieldPojo List集合
     */
    public static <T> List<AnnotationFieldPojo> parseExcelColumnAnnotation (Class<T> clazz){
        return parseAnnotation(clazz, ExcelColumn.class);
    }
    /**
     * 解析类上的注解信息
     * @param clazz 类的字节码
     * @param <T> 泛型类型
     * @return 排序好的AnnotationFieldPojo List集合
     */
    public static <T> List<AnnotationFieldPojo> parseAnnotation (Class<T> clazz, Class<ExcelColumn> annotationClass) {
        List<AnnotationFieldPojo> list = parseSingleClassAnnotation(clazz, annotationClass);
        clazz = (Class<T>) clazz.getSuperclass();
        while (clazz != null){
            list.addAll(parseSingleClassAnnotation(clazz, annotationClass));
            clazz = (Class<T>) clazz.getSuperclass();
        }
        //将List按照Sort升序排列
        list.sort(new AnnotationFieldComparator());
        return list;
    }

    /**
     * 解析一个类中的所有字段
     * @param clazz 类的字节码
     * @param annotationClass 注解
     * @param <T> 泛型类
     */
    private static <T>  List<AnnotationFieldPojo> parseSingleClassAnnotation(Class<T> clazz, Class<ExcelColumn> annotationClass) {
        List<AnnotationFieldPojo> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if(!ObjectUtils.isEmpty(fields)){
            if (annotationClass != null){
                for (Field field : fields){
                    String fieldName = field.getName();
                    if(SERIALVERSIONUID_FIELD.equals(fieldName)){
                        continue;
                    }
                    //注解class不为空
                    if(field.isAnnotationPresent(annotationClass)){
                        ExcelColumn column = field.getAnnotation(annotationClass);
                        //设置为导出
                        if (column.isExport()){
                            AnnotationFieldPojo pojo = new AnnotationFieldPojo();
                            pojo.setField(field);
                            pojo.setTitle(column.title());
                            pojo.setSort(column.sort());
                            list.add(pojo);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * @param clazz  解析存在指定注解的字段
     * @param <T> 泛型
     * @return Field的List集合
     */
    public static <T>  List<Field> parseField(Class<T> clazz, Class<? extends Annotation> annotationClass){
        List<Field> list = parseSingleClassField(clazz, annotationClass);
        clazz = (Class<T>) clazz.getSuperclass();
        while (clazz != null){
            list.addAll(parseSingleClassField(clazz, annotationClass));
            clazz = (Class<T>) clazz.getSuperclass();
        }
        return list;
    }

    /**
     * 解析一个类中的所有字段
     * @param clazz 类的字节码
     * @param annotationClass 注解
     * @param <T> 泛型类
     */
    private static <T>  List<Field>  parseSingleClassField(Class<T> clazz, Class<? extends Annotation> annotationClass) {
        List<Field> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if(!ObjectUtils.isEmpty(fields)){
            //注解class不为空
            if (annotationClass != null){
                for (Field field : fields){
                    String fieldName = field.getName();
                    if(SERIALVERSIONUID_FIELD.equals(fieldName)){
                        continue;
                    }
                    if(field.isAnnotationPresent(annotationClass)){
                        list.add(field);
                    }
                }
            } else {
                for (Field field : fields){
                    String fieldName = field.getName();
                    if(SERIALVERSIONUID_FIELD.equals(fieldName)){
                        continue;
                    }
                    //注解class不为空
                    list.add(field);
                }
            }
        }
        return list;
    }


    /**
     * 解析类中的所有字段
     * @param clazz 类的字节码
     * @param <T> 泛型类型
     * @return Field的List集合
     */
    public static <T>  List<Field> parseAllField(Class<T> clazz){
       return parseField(clazz, null);
    }

    public static void main(String[] args){
        List<AnnotationFieldPojo> list = parseExcelColumnAnnotation(AnnotationFieldPojo.class);
        for (AnnotationFieldPojo pojo : list){
            System.out.println(pojo);
        }
    }
}
