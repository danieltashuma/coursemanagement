package webmy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropsHelper {

	private static String course="";
	private static Properties props = null;
	private static String FILE_NAME = "c:/temp/";

	public static String get(String name) {
		if (props == null) {
			init();
		}
		return props.getProperty(name);
	}

	public static Reply1 set(String name, String val,String coursename) {
		course=coursename;
		if (props == null) {
			init();
		}
		try {
			props.setProperty(name, val);
			save();
			return new Reply1();
		} catch (Exception e) {
			Reply1 r = new Reply1();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	private static void save() {
		if (props != null) {
			try {
				System.out.println("777777777-"+FILE_NAME+course+".properties");
				FileOutputStream out = new FileOutputStream(new File(FILE_NAME+course+".properties"));
				props.save(out, "");
				props = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void init() {
		try {
			System.out.println("6666666666666-"+FILE_NAME+course+".properties");
			props = new Properties();
			File file= new File(FILE_NAME+course+".properties");
			if(file.exists()==true){
				System.out.println("exist file");
				FileInputStream in = new FileInputStream(file.getAbsolutePath());
				props.load(in);
			}else{
			file.createNewFile();
			
			FileInputStream in = new FileInputStream(FILE_NAME+course+".properties");
			props.load(in);
			in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCourse() {
		return course;
	}

	public static void setCourse(String course) {
		PropsHelper.course = course;
	}

}
