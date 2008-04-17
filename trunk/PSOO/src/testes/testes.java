package testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;
import br.edu.ufcg.psoo.billiards.persistence.XMLPersistence;

import com.thoughtworks.xstream.XStream;

public class testes {
	
	public static void main(String[] args) {
		
			XMLPersistence p = new XMLPersistence();
			p.setDatabase("teste");
			
			User u1 = null;
			User u2 = null;
			User u3 = null;
			
			try {
				u1 = new User("1", "Thiago", "Sales", "123", "321", "543", "tsales@wer.com", "pc");
				u2 = new User("2", "Bruno", "Sales", "123", "321", "543", "tsales@wer.com", "pc");
				u3 = new User("3", "Melo", "Sales", "123", "321", "543", "tsales@wer.com", "pc");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//p.saveUser(u1);
			//p.saveUser(u2);
			//p.saveUser(u3);
			
			League l = new League("10", u1.getUserId(), "Liga", Calendar.getInstance().getTime());
			
			//p.putPlayerIntoLeague(l, u2, 200);
			//p.putPlayerIntoLeague(l, u3, 200);
			
			//UserLeague ul = new UserLeague(u2.getUserId(), l.getLeagueId(), 300, Calendar.getInstance().getTime());
			
			//p.saveUserLeague(ul);
			
			//p.leaveLeague(u2, l);
			
			ArrayList<User> users = p.findUsersByLeague(l);
			
			for(User u : users) {
				System.out.println(u.getFirstName());
			}
		
	}

}
