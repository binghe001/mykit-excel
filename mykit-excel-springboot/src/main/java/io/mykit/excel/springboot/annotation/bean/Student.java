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
package io.mykit.excel.springboot.annotation.bean;

import io.mykit.excel.annotation.ExcelColumn;

/**
 * @author binghe
 * @version 1.0.0
 * @description 注解学生类
 */
public class Student extends Person{
    private static final long serialVersionUID = -6180523202831503132L;

    @ExcelColumn(isExport = false, title = "班级编号", sort = 1)
    private String classNo;

    private Integer score;

    @ExcelColumn(isExport = true, title = "爱好", sort = 5)
    private String hobby;

    public Student(String id, String name, String classNo, Integer score, String hobby){
        super(id, name);
        this.classNo = classNo;
        this.score = score;
        this.hobby = hobby;
    }


    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
