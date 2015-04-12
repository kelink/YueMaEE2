package com.gdufs.gd.util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.gdufs.gd.common.C;
import com.gdufs.gd.entity.TransferMessage;
import com.gdufs.gd.response.modle.FriendsActivityObj;

public class JacksonTester {
   public static void main(String args[]){
      JacksonTester tester = new JacksonTester();
      TransferMessage message = new TransferMessage();
	HashMap<String, ArrayList<Student>> resultMap=new HashMap<String,ArrayList<Student>>();
	ArrayList<Student> students=new ArrayList<Student>();		
	
	
	for (int i = 0; i < 5; i++) {
		 Student student = new Student();
	     student.setAge(i);
	     student.setName("Mahesh");
	     ArrayList<String> temp=new ArrayList<String>();
	     temp.add("dasdasd");
	     temp.add("usyfyfnakn");
	     student.setCodeStrings(temp);
	     students.add(student);
	}
	resultMap.put("result",students);
	message.setCode(C.ResponseCode.SUCCESS);
	message.setMessage(C.ResponseMessage.SUCCESS);
	message.setResultMap(resultMap);
	ArrayList<Student> results;
	try {
		System.out.println(JacksonUtil.writeEntity2JSON(message));
		
		System.out.println(JacksonUtil.readJson2Entity(JacksonUtil.writeEntity2JSON(message), TransferMessage.class).getResultMap().get("result"));
		
		System.out.println(JacksonUtil.writeEntity2JSON(students));
		
		results = JacksonUtil.readJson(JacksonUtil.writeEntity2JSON(students), ArrayList.class, Student.class);
		System.out.println(results.get(0).getAge());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException{
      ObjectMapper mapper = new ObjectMapper();	
     System.out.println( mapper.writeValueAsString(student));
      mapper.writeValue(new File("student.json"), student);
   }

   private Student readJSON() throws JsonParseException, JsonMappingException, IOException{
      ObjectMapper mapper = new ObjectMapper();
      Student student = mapper.readValue(new File("student.json"), Student.class);
      return student;
   }
}

class Student {
   private String name;
   private int age;
   private ArrayList<String> codeStrings;
   public Student(){}
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getAge() {
      return age;
   }
   public void setAge(int age) {
      this.age = age;
   }
   public ArrayList<String> getCodeStrings() {
	return codeStrings;
}
public void setCodeStrings(ArrayList<String> codeStrings) {
	this.codeStrings = codeStrings;
}
@Override
public String toString() {
	return "Student [name=" + name + ", age=" + age + ", codeStrings="
			+ codeStrings + "]";
}

}