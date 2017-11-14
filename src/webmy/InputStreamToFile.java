package webmy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

public class InputStreamToFile {
	public static void checkfolder(String folderpath){
		File theDir = new File("C:/Users/user/web2/coursemanagement/WebContent/updatefile/"+folderpath);
		System.out.println(theDir.exists());
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        System.out.println(theDir.getPath());
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
	}
	
	
    public static void asd(String filepath,String filename){
    	   try {
               InputStream is = new FileInputStream(filepath);             
               OutputStream os = new FileOutputStream("C:/Users/user/web2/coursemanagement/src/updateFiles/"+filename);
               
               byte[] buffer = new byte[1024];
               int bytesRead;
               //read from is to buffer
               while((bytesRead = is.read(buffer)) !=-1){
                   os.write(buffer, 0, bytesRead);
               }
               is.close();
               //flush OutputStream to write any buffered data to file
               os.flush();
               os.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
    public static String[] getFileName(String fileaderrs){
 	   String[] string1 = fileaderrs.split(Pattern.quote(File.separator));
 	  String[] pathAndName = new String[2]; 
	   String str="";
	   
	   int x=0;
	   String nameName=string1[(string1.length-1)];
	   while(x < string1.length){
		str+=string1[x];
		str+="/";
		x++;
	    }
	   pathAndName[0]=str;
	   pathAndName[1]=nameName;
	    System.out.println("path"+pathAndName[0]);
	    System.out.println("Name"+ pathAndName[1]);
	return pathAndName;
	
 }

}