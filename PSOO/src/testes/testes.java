package testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import br.edu.ufcg.psoo.billiards.beans.User;
import com.thoughtworks.xstream.XStream;

public class testes {
	
	private XStream stream;
	
	public void createContents(String fileName, String text) {
		
		File file = new File(fileName);
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(file);
			output.write(text.getBytes());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public <T>ArrayList<T> getContents(String fileName) {
	//public String getContents(String fileName) {
		this.stream = new XStream();
		
		File file 	  = new File(fileName);		
		byte[] inputB = new byte[(int)file.length()];		
		FileInputStream input = null;
		
		try {
			input = new FileInputStream(file);
			input.read(inputB);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (ArrayList<T>)this.stream.fromXML(new String(inputB));
		//return new String(inputB);
			
	}
	
	public static void main(String[] args) {
		
		System.out.println(String.valueOf(new Random().nextLong()));
/*		testes t = new testes();
		XStream stream = new XStream();
		String xml = null;
		
		try {
			ArrayList<User> usersL = new ArrayList<User>();
			User u = new User(new Long(10), "thiago", "bruno", "00000", "321654", "456789", "thiago@gf.com", "pic.jpg");
			usersL.add(u);
			xml = stream.toXML(usersL);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//System.out.println(xml);
				
		//t.createContents("user.xml", xml);
		//String u = t.getContents("user.xml");
		//System.out.println("-----------------/n" + u);
		//User uu = (User)stream.fromXML(xml);
		//System.out.println(uu.getFirstName());
	}

}
