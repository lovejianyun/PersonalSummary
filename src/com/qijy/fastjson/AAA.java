package com.qijy.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

class AAA{
        @JSONField(name = "NAME")
        private String name;
        @JSONField(name = "GRADE")
        private String grade;
        @JSONField(name = "AGE")
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }