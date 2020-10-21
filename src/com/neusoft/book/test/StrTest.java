package com.neusoft.book.test;

import java.io.File;
import java.io.IOException;

public class StrTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strres="/BookManager/ItemServlet/list";

		strres=strres.substring(1);
		String path=strres;
		
		String path1=path.substring(path.indexOf("/"),path.lastIndexOf("/")).substring(1);
//		String path2=path.substring(path.indexOf("wtpwebapps"));
		
//		String path3=path2.substring(path2.indexOf("/")+1);
//		String path4=path3.substring(0,path3.indexOf("/"));
//		
		
		
		System.out.println(path);
		System.out.println(path1);
//		System.out.println(path2);
//		System.out.println(path3);
//		System.out.println(path4);
		
//		File directory = new File("./src"); //实例化一个File对象。参数不同时，获取的最终结果也不同
//
//		try {
//			directory.getCanonicalPath();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //获取标准路径。该方法需要放置在try/catch块中，或声明抛出异常
//
//		directory.getAbsolutePath(); //获取绝对路径。
//		System.out.println(directory.toString());
//		String pas= System.getProperty("user.dir");
//		System.out.println(pas.replace("\\", "/"));
		File file1 = new File("");
		String filePath1= "";
        try {
			 filePath1 = file1.getCanonicalPath()+File.separator+"imagepath\\";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(filePath1);
		

	}

}
