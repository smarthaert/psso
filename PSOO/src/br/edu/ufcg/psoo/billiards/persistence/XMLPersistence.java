package br.edu.ufcg.psoo.billiards.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;
import br.edu.ufcg.psoo.billiards.beans.Match;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;

public class XMLPersistence implements PersistenceIF {

	private XStream stream;
	private String xmlPathUsers;
	private String xmlPathLeagues;
	private String xmlPathUserLeague;
	private String xmlPathMatches;
	
	public XMLPersistence() {
		this.stream = new XStream();
	}

	private void createContents(String fileName, String text) {

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

	private <T> ArrayList<T> getContents(String fileName) {
		File file = new File(fileName);

		if (!file.exists())
			return new ArrayList<T>();

		byte[] inputB = new byte[(int) file.length()];
		FileInputStream input = null;

		try {
			input = new FileInputStream(file);
			input.read(inputB);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			return (ArrayList<T>) this.stream.fromXML(new String(inputB));
		} catch (StreamException e) {
			return new ArrayList<T>();
		}

	}

	public void setDatabase(String databaseName) {
		this.xmlPathUsers      = databaseName + "-users.xml";
		this.xmlPathLeagues    = databaseName + "-leagues.xml";
		this.xmlPathMatches    = databaseName + "-matches.xml";
		this.xmlPathUserLeague = databaseName + "-usersLeague.xml";
	}

	public void saveUser(User user) {
		this.saveObject(user, this.xmlPathUsers);
	}

	public void saveLeague(League league) {
		this.saveObject(league, this.xmlPathLeagues);
	}

	private <T> void saveObject(T object, String xmlPath) {
		ArrayList<T> objects = this.getContents(xmlPath);

		if (objects.contains(object))
			objects.remove(object);

		objects.add(object);

		createContents(xmlPath, stream.toXML(objects));
	}

	public ArrayList<User> getUsers() {
		return this.getContents(this.xmlPathUsers);
	}

	public ArrayList<League> getLeagues() {
		return this.getContents(this.xmlPathLeagues);
	}

	public User findUserById(String id) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		for (User u : users) {
			if (u.getUserId().equals(id)) {
				return u;
			}
		}

		return null;
	}

	public ArrayList<User> findUserByFirstName(String match) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);
		ArrayList<User> usersFound = new ArrayList<User>();

		String s2 = match.toLowerCase();
		for (User user : users) {
			String s1 = user.getFirstName().toLowerCase();

			if (s1.equals(s2) || s1.matches(s2)) {
				usersFound.add(user);
			}
		}
		
		return usersFound;
	}

	public ArrayList<User> findUserByLastName(String match) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);
		ArrayList<User> usersFound = new ArrayList<User>();

		String s2 = match.toLowerCase();
		for (User user : users) {
			String s1 = user.getLastName().toLowerCase();

			if (s1.equals(s2) || s1.matches(s2)) {
				usersFound.add(user);
			}
		}
		return usersFound;
	}

	public User findUserByEmail(String email) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		for (User u : users) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
	}

	public void removeUser(User userId) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		users.remove(userId);

		this.createContents(this.xmlPathUsers, this.stream.toXML(users));

	}

	private void deleteAllObjects(String filePath) {
		File f = new File(filePath);
		f.delete();
	}

	public void deleteAllUsers() {
		this.deleteAllObjects(this.xmlPathUsers);
	}

	public void deleteAllLeagues() {
		this.deleteAllObjects(this.xmlPathLeagues);
	}

	public void deleteAllMatches() {
		this.deleteAllObjects(this.xmlPathMatches);
	}

	public void putPlayerIntoLeague(League league, User user, Integer initialHandCap) {
		ArrayList<UserLeague> usersAndLeagues = this
				.getContents(this.xmlPathUserLeague);
		UserLeague userleague = new UserLeague(user.getUserId(), league
				.getLeagueId(), initialHandCap, Calendar.getInstance().getTime());

		if (usersAndLeagues.contains(userleague))
			usersAndLeagues.remove(userleague);

		usersAndLeagues.add(userleague);
		this.createContents(this.xmlPathUserLeague, this.stream
				.toXML(usersAndLeagues));
	}
	
	public void saveUserLeague(UserLeague userLeague) {
		this.saveObject(userLeague, this.xmlPathUserLeague);
	}
	
	public UserLeague getUserLeague(User user, League league) {
		ArrayList<UserLeague> userLeagueList = this.getContents(this.xmlPathUserLeague);
		
		for(UserLeague ul : userLeagueList) {
			if(ul.getUserId().equals(user.getUserId()) && ul.getLeagueId().equals(league.getLeagueId())) {
				return ul;
			}
		}
		
		return null;
		
	}
	
	public League findLeagueById(String id) {
		ArrayList<League> leagues = this.getLeagues();
		
		for(League l : leagues) {
			if(l.getLeagueId().equals(id))
				return l;
		}
		
		return null;
		
	}
	
	public ArrayList<League> findLeagueByName(String match) {
		ArrayList<League> leagues = this.getLeagues();
        ArrayList<League> foundLeagues = new ArrayList<League>();
        String s2 = match.toLowerCase();
        for (League l : leagues) {
                String s1 = l.getName().toLowerCase();

                if (s1.equals(s2) || s1.matches(s2)) {
                        foundLeagues.add(l);
                }
        }
        
        return foundLeagues;
		
	}

	public void deleteLeague(League league) {
		ArrayList<League> leagues = this.getLeagues();
		
		leagues.remove(league);
		
		this.deletePlayerAndLeague(league);
		this.createContents(this.xmlPathLeagues, this.stream.toXML(leagues));
		
	}

	private void deletePlayerAndLeague(League l) {
		ArrayList<UserLeague> userLeaguesList = this.getAllPlayersInALeague();
		
		UserLeague userLeague = null;
		
		for(int i=0; i<userLeaguesList.size(); i++) {
			userLeague = userLeaguesList.get(i);
			if(userLeague.getLeagueId().equals(l.getLeagueId())) {
				userLeaguesList.remove(i);
			}
		}
		
		this.createContents(this.xmlPathUserLeague, this.stream.toXML(userLeaguesList));
	}
	
	private ArrayList<UserLeague> getAllPlayersInALeague() {
		return this.getContents(this.xmlPathUserLeague);
	}
	
	public void deleteAllWinLoss() {
		this.deleteAllObjects(this.xmlPathMatches);
	}

	public ArrayList<Match> getAllMatches() {
		return this.getContents(this.xmlPathMatches);
	}

	public void deleteMatch(Match winLoss) {
		ArrayList<Match> winLossList = this.getAllMatches();
		
		winLossList.remove(winLoss);
		
		this.createContents(this.xmlPathMatches, this.stream.toXML(winLossList));		
	}

	public void saveMatch(Match winLoss) {
		this.saveObject(winLoss, this.xmlPathMatches);
	}

	public ArrayList<League> findLeaguesByUser(User user) {
		ArrayList<UserLeague> userLeagueList = this.getContents(this.xmlPathUserLeague);				
		ArrayList<League> leaguesFound 			 = new ArrayList<League>();
		
		for(UserLeague ul : userLeagueList) {
			if(ul.getUserId().equals(user.getUserId())) {
				leaguesFound.add(this.findLeagueById(ul.getLeagueId()));							
			}
		}
		
		return leaguesFound;
	}


	public ArrayList<User> findUsersByLeague(League league) {
		ArrayList<UserLeague> userLeagueList = this.getContents(this.xmlPathUserLeague);				
		ArrayList<User> usersFound 			 = new ArrayList<User>();
				
		usersFound.add(this.findUserById(league.getOperator()));
		
		for(UserLeague ul : userLeagueList) {
			if(ul.getLeagueId().equals(league.getLeagueId())) {
				usersFound.add(this.findUserById(ul.getUserId()));							
			}
		}
		
		return usersFound;
	}

	public void leaveLeague(User user, League league) {
		ArrayList<UserLeague> userLeagueList = this.getContents(this.xmlPathUserLeague);		

		for(int i=0; i<userLeagueList.size(); i++) {
			if(userLeagueList.get(i).getUserId().equals(user.getUserId()) && (userLeagueList.get(i).getLeagueId().equals(league.getLeagueId()))) {
				userLeagueList.remove(i);
			}
		}
		
		this.createContents(this.xmlPathUserLeague, this.stream.toXML(userLeagueList));
		
	}


	public ArrayList<Match> findMatchesByLeague(League league) {
		ArrayList<Match> matches 		= this.getContents(this.xmlPathMatches);
		ArrayList<Match> matchesFound = new ArrayList<Match>();
		
		for(Match m : matches) {
			if(m.getLeagueId().equals(league.getLeagueId())) {
				matchesFound.add(m);
			}
		}
			
		return matchesFound;
	}
	
}
