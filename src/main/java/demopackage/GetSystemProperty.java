package demopackage;

public class GetSystemProperty {
	
	public static void main(String[] str) {
		
		System.getProperties().list(System.out);
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));

	}

}
