package com.neusoft.util;

import java.io.File;
import java.io.IOException;

public class GetProjectPah {
	public  String getProjectPath() {
		String string =System.getProperty("user.dir").replace("\\", "/")+"/WebContent/imagepath/";
//		String string = "";
//		try {
//			string = new File("").getCanonicalPath()+"/WebContent/imagepath/";
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("GetProjectPah类："+string);
		return string;
		
		
		

	}
}
