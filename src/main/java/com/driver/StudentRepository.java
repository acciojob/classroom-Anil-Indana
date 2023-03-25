package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> teacherStudent = new HashMap<>();
    public void addStudent(Student student){
        String key = student.getName();
        studentHashMap.put(key,student);
    }
    public void addTeacher(Teacher teacher){
        String key = teacher.getName();
        teacherHashMap.put(key,teacher);
    }
    public void addStudentTeacherPair(String student,String teacher){
        List<String> list;
        if(teacherStudent.containsKey(teacher)){
            list = teacherStudent.get(teacher);
        }
        else{
            list = new ArrayList<>();
        }
        list.add(studentHashMap.get(student).getName());
        teacherStudent.put(teacher,list);
    }
    public Student getStudentByName(String name){
        if(studentHashMap.containsKey(name)) return  studentHashMap.get(name);
        return null;
    }
    public Teacher getTeacherByName(String name){
        if(teacherHashMap.containsKey(name)) return teacherHashMap.get(name);
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacher){
        return teacherStudent.get(teacher);
    }
    public List<String> getAllStudents(){
        List<String> students = new ArrayList<>();
        for(String name : studentHashMap.keySet()){
            students.add(name);
        }
        return students;
    }
    public void deleteTeacherByName(String teacher){
        teacherHashMap.remove(teacher);
        List<String> students = teacherStudent.get(teacher);
        teacherStudent.remove(teacher);
        for(String name : students){
            if(studentHashMap.containsKey(name)) studentHashMap.remove(name);
        }
    }
    public void deleteAllTeachers(){
        for(String teacher : teacherHashMap.keySet()){
            teacherHashMap.remove(teacher);
            List<String> students = teacherStudent.get(teacher);
            teacherStudent.remove(teacher);
            for(String name : students){
                if(studentHashMap.containsKey(name)) studentHashMap.remove(name);
            }
        }
    }
}
