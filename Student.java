import java.io.*;
class Student implements Serializable{
    String name; int note;
    Student(String name, int note){this.name = name;this.note = note;}
    public String toString(){
        return name+"   "+note;
    }
}