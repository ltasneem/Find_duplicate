//https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html
//https://github.com/andrewjonesdev/TwilioInteraction
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Find_duplicate {
	

	public static void main(String[] args){
		 //Create checksum for this file
		walk("/home/oracle/duplicate"); 
		/*File file = new File("examples.txt");
		 
		 //Use MD5 algorithm
		 MessageDigest md5Digest;
		 String checksum;
		 
		 try {
		 md5Digest = MessageDigest.getInstance("MD5");
		 checksum = getFileChecksum(md5Digest, file);
		 //see checksum
		 System.out.println(checksum);
		 } catch (IOException e) {
		 e.printStackTrace();
		 } catch (NoSuchAlgorithmException e) {
		 e.printStackTrace();
		 }
		 
		 //Use SHA-1 algorithm
		 MessageDigest shaDigest;
		 try {
		 shaDigest = MessageDigest.getInstance("SHA-1");
		 } catch (NoSuchAlgorithmException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 */
		 
		 /*
		 //SHA-1 checksum 
		 try {
		 String shaChecksum = getFileChecksum(shaDigest, file);
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 */
		 }
	
		public static void walk ( String path ){
			File root = new File(path);
			File[] list = root.listFiles();
			int i=0;
			
			if (list==null) return;
			
			String [] checksum =new String[list.length];
			int collision=0;
			HashMap<String, String> myMap = new HashMap<String, String>();
			for (File f : list){
				if (f.isDirectory()){
					walk(f.getAbsolutePath());
					System.out.println("Dir: " + f.getAbsoluteFile() );
				}else{
					//System.out.println("File: "+ f.getAbsoluteFile());
					MessageDigest md5Digest;
					 String chck;
					 
					 try {
					 md5Digest = MessageDigest.getInstance("SHA-1");
					 chck = getFileChecksum(md5Digest, f);
					 //see checksum
					 //System.out.println(chck);
					 myMap.put(f.getAbsoluteFile().getName(),chck);

					 for(int j=0;j<checksum.length;j++)
					 {
						 if(chck.equals(checksum[j]))
						 {
							 System.out.println("duplicate file detected" + f.getAbsoluteFile());
							 collision++;
						 }
					 }
					 checksum[i]=chck;
					 } catch (IOException e) {
					 e.printStackTrace();
					 } catch (NoSuchAlgorithmException e) {
					 e.printStackTrace();
					 }
					 i++;
				}
			}
			for (String key : myMap.keySet()) {
			    System.out.println("Key = " + key);
			}
			for (String value : myMap.values()) {
			    System.out.println("Value = " + value);
			}

			System.out.println("Number of Collisions: "+ collision);
		}

		 private static String getFileChecksum(MessageDigest digest, File file) throws IOException
		 {
		 //Get file input stream for reading the file content
		 FileInputStream fis = new FileInputStream(file);
		 
		 //Create byte array to read data in chunks
		 byte[] byteArray = new byte[1024];
		 int bytesCount = 0; 
		 
		 //Read file data and update in message digest
		 while ((bytesCount = fis.read(byteArray)) != -1) {
		 digest.update(byteArray, 0, bytesCount);
		 };
		 
		 //close the stream; We don't need it now.
		 fis.close();
		 
		 //Get the hash's bytes
		 byte[] bytes = digest.digest();
		 
		 //This bytes[] has bytes in decimal format;
		 //Convert it to hexadecimal format
		 StringBuilder sb = new StringBuilder();
		 for(int i=0; i< bytes.length ;i++)
		 {
		 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		 }
		 
		 //return complete hash
		 return sb.toString();
		 
		}
		 
			static String timeConversion(String s) {
		        // Complete this function
		        StringTokenizer st2 = new StringTokenizer(s, ":");
		        int num1 = Integer.parseInt((String) st2.nextElement());
		        String sc=(String) st2.nextElement();
		        int num2 = Integer.parseInt(sc);
		        String last = (String) st2.nextElement();
		        String ap = last.substring(2, 4);
		        String dp = last.substring(0, 2);
		        if(ap.equals("PM"))
		        {
		        	if(num1<12)
		            {
		        	    num1+=12;
		            }
		        	return num1+":"+sc+":"+dp;
		        }
		        else if(ap.equals("AM"))
		        {
		        	return s;
		        }
		         return dp;
		    }
}
