package br.edu.ufcg.psoo.billiards.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.Match;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;

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

	/**
	 * This method creates a file for .xml content type. This file stores information such as Users, League, 
	 * Matches, and so on.
	 * @param fileName The file name. For instance, teste-users.xml
	 * @param text The data which will be stored in the .xml file
	 */
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

	/**
	 * This is a generic method. It is responsible to return the contents of .xml file. This content can be
	 * Users, Leagues, Matches and so on.
	 * @param <T>  
	 * @param fileName The file name which contains the desired contents 
	 * @return It returns a generic array which stores the information above cited
	 */
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

	/**
	 * This method initialize the file path which will contain all information about Users, Leagues, Matches, etc
	 * @param databaseName All files will have a common name followed by its property. For instance: teste-users.xml, teste-leagues.xml, etc. This param will hold this common name
	 */
	public void setDatabase(String databaseName) {
		this.xmlPathUsers = databaseName + "-users.xml";
		this.xmlPathLeagues = databaseName + "-leagues.xml";
		this.xmlPathMatches = databaseName + "-matches.xml";
		this.xmlPathUserLeague = databaseName + "-usersLeague.xml";
	}

	/**
	 * Save a given player
	 * @param user The user to be saved
	 */
	public void saveUser(User user) {
		this.saveObject(user, this.xmlPathUsers);
	}

	/**
	 * Save a league
	 * @param league The league to be saved
	 */
	public void saveLeague(League league) {
		this.saveObject(league, this.xmlPathLeagues);
	}

	/**
	 * This method is responsible for saving a generic information. It can hold users info, league info, etc.  
	 * @param <T>
	 * @param object This is an object which will be stored. It can hold a User object, League object, etc.
	 * @param xmlPath This param will hold the file path which will store the desired object 
	 */
	private <T> void saveObject(T object, String xmlPath) {
		ArrayList<T> objects = this.getContents(xmlPath);

		if (objects.contains(object))
			objects.remove(object);

		objects.add(object);

		this.createContents(xmlPath, stream.toXML(objects));
	}

	/**
	 * Get all stored users
	 * @return Returns a list of users
	 */
	public ArrayList<User> getUsers() {
		return this.getContents(this.xmlPathUsers);
	}

	/**
	 * Get all stored Leagues
	 * @return Returns a list of leagues 
	 */
	public ArrayList<League> getLeagues() {
		return this.getContents(this.xmlPathLeagues);
	}

	/**
	 * This method is responsible for finding a user for a given user id
	 * @param id The User id
	 * @return Returns a User with id "id"
	 */
	public User findUserById(String id) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		for (User u : users) {
			if (u.getUserId().equals(id)) {
				return u;
			}
		}

		return null;
	}

	/**
	 * This method is responsible for finding a user for a given user name
	 * @param match The user name
	 * @return Returns a list of users which can contain the given name 
	 */
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

	/**
	 * This method is responsible for finding a user for a given player last name
	 * @param match The user last name
	 * @return Returns a list of users
	 */
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

	/**
	 * This method is responsible for finding a user for a given player email address
	 * @param email The user email address
	 * @return Returns a User
	 */
	public User findUserByEmail(String email) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		for (User u : users) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
	}

	/**
	 * This method is responsible for removing a user for a given user id
	 * @param userId The user id 
	 */
	public void removeUser(User userId) {
		ArrayList<User> users = this.getContents(this.xmlPathUsers);

		users.remove(userId);

		this.createContents(this.xmlPathUsers, this.stream.toXML(users));

	}

	/**
	 * This method is responsible for removing all desired objects, such as Users, Leagues, Matches, etc 
	 * @param filePath
	 */
	private void deleteAllObjects(String filePath) {
		File f = new File(filePath);
		f.delete();
	}

	/**
	 * This method is responsible for removing all users
	 */
	public void removeAllUsers() {
		this.deleteAllObjects(this.xmlPathUsers);
	}

	/**
	 * This method is responsible for removing all leagues
	 */
	public void removeAllLeagues() {
		this.deleteAllObjects(this.xmlPathLeagues);
	}

	/**
	 * This method is responsible for removing all matches
	 */
	public void removeAllMatches() {
		this.deleteAllObjects(this.xmlPathMatches);
	}

	/**
	 * This method is responsible for joining a user (player) to a league
	 * @param league The league which the player will be
	 * @param user The user who will join the league 
	 */
	public void putPlayerIntoLeague(League league, User user,
			Integer initialHandCap) {
		ArrayList<UserLeague> usersAndLeagues = this
				.getContents(this.xmlPathUserLeague);
		UserLeague userleague = new UserLeague(user.getUserId(), league
				.getLeagueId(), initialHandCap, Calendar.getInstance()
				.getTime());

		if (usersAndLeagues.contains(userleague))
			usersAndLeagues.remove(userleague);

		usersAndLeagues.add(userleague);
		this.createContents(this.xmlPathUserLeague, this.stream
				.toXML(usersAndLeagues));
	}

	/**
	 * This method is responsible for storing a user/league relationship
	 * @param userLeague The UserLeague object which will be stored  
	 */
	public void saveUserLeague(UserLeague userLeague) {
		this.saveObject(userLeague, this.xmlPathUserLeague);
	}

	/**
	 * Get a relationship between a given user and a given league
	 * @param user The user who will be in the league
	 * @param league The league which is in the given league
	 * @return Returns the desired UserLeague object 
	 */
	public UserLeague getUserLeague(User user, League league) {
		ArrayList<UserLeague> userLeagueList = this
				.getContents(this.xmlPathUserLeague);

		for (UserLeague ul : userLeagueList) {
			if (ul.getUserId().equals(user.getUserId())
					&& ul.getLeagueId().equals(league.getLeagueId())) {
				return ul;
			}
		}

		return null;

	}

	/**
	 * This method is responsible for finding a league for a given league id
	 * @param id The league id
	 * @return Returns the desired League object 
	 */
	public League findLeagueById(String id) {
		ArrayList<League> leagues = this.getLeagues();

		for (League l : leagues) {
			if (l.getLeagueId().equals(id))
				return l;
		}

		return null;

	}
	
	/**
	 * This method is responsible for finding a set of leagues for a given league name
	 * @param match The league name
	 * @return Returns a list of League objects which contains the given league name
	 */
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

	/**
	 * This method is responsible for removing a given league object
	 * @param league The league which will be removed
	 */
	public void removeLeague(League league) {
		ArrayList<League> leagues = this.getLeagues();

		leagues.remove(league);

		this.deletePlayerAndLeague(league);
		this.createContents(this.xmlPathLeagues, this.stream.toXML(leagues));

	}

	/**
	 * This method is responsible for removing the relationship between a user and a league 
	 * @param l The league which all relationships will be deleted
	 */
	private void deletePlayerAndLeague(League l) {
		ArrayList<UserLeague> userLeaguesList = this.getAllPlayersInALeague();

		UserLeague userLeague = null;

		for (int i = 0; i < userLeaguesList.size(); i++) {
			userLeague = userLeaguesList.get(i);
			if (userLeague.getLeagueId().equals(l.getLeagueId())) {
				userLeaguesList.remove(i);
			}
		}

		this.createContents(this.xmlPathUserLeague, this.stream
				.toXML(userLeaguesList));
	}

	/**
	 * Gets all player/league relationship 
	 * @return Returns a list of all UserLeague (player/league relationship) object
	 */
	private ArrayList<UserLeague> getAllPlayersInALeague() {
		return this.getContents(this.xmlPathUserLeague);
	}

	/**
	 * Gets all stored matches
	 * @return Returns a list of all matches 
	 */
	public ArrayList<Match> getAllMatches() {
		return this.getContents(this.xmlPathMatches);
	}

	/**
	 * This method is responsible for removing a given match
	 * @param winLoss The match which will be deleted 
	 */
	public void removeMatch(Match winLoss) {
		ArrayList<Match> winLossList = this.getAllMatches();

		winLossList.remove(winLoss);

		this
				.createContents(this.xmlPathMatches, this.stream
						.toXML(winLossList));
	}

	/**
	 * Saves a given match
	 * @param winLoss The match which will be stored
	 */
	public void saveMatch(Match winLoss) {
		this.saveObject(winLoss, this.xmlPathMatches);
	}

	/**
	 * Finds a set of league which a given user (player) is.
	 * @param user The user who is in the league
	 * @return Returns a list of League objects which the given user is.
	 */
	public ArrayList<League> findLeaguesByUser(User user) {
		ArrayList<UserLeague> userLeagueList = this
				.getContents(this.xmlPathUserLeague);
		ArrayList<League> leaguesFound = new ArrayList<League>();

		for (UserLeague ul : userLeagueList) {
			if (ul.getUserId().equals(user.getUserId())) {
				leaguesFound.add(this.findLeagueById(ul.getLeagueId()));
			}
		}

		return leaguesFound;
	}

	/**
	 * Finds all users (players) who are in a given league
	 * @param league The league which all users (players) must be
	 * @return Returns a list of User (player) objects
	 */
	public ArrayList<User> findUsersByLeague(League league) {
		ArrayList<UserLeague> userLeagueList = this
				.getContents(this.xmlPathUserLeague);
		ArrayList<User> usersFound = new ArrayList<User>();

		usersFound.add(this.findUserById(league.getOperator()));

		for (UserLeague ul : userLeagueList) {
			if (ul.getLeagueId().equals(league.getLeagueId())) {
				usersFound.add(this.findUserById(ul.getUserId()));
			}
		}

		return usersFound;
	}

	/**
	 * Removes (disassociate) a given user from a given league
	 * @param user The User object
	 * @param league The League object
	 */
	public void leaveLeague(User user, League league) {
		ArrayList<UserLeague> userLeagueList = this
				.getContents(this.xmlPathUserLeague);

		for (int i = 0; i < userLeagueList.size(); i++) {
			if (userLeagueList.get(i).getUserId().equals(user.getUserId())
					&& (userLeagueList.get(i).getLeagueId().equals(league
							.getLeagueId()))) {
				userLeagueList.remove(i);
			}
		}

		this.createContents(this.xmlPathUserLeague, this.stream
				.toXML(userLeagueList));

	}

	/**
	 * Finds a set of matches for a given league
	 * @param league The league which is associated to the match
	 * @return Returns a list of matches which is associated with the given league
	 */
	public ArrayList<Match> findMatchesByLeague(League league) {
		ArrayList<Match> matches = this.getContents(this.xmlPathMatches);
		ArrayList<Match> matchesFound = new ArrayList<Match>();

		for (Match m : matches) {
			if (m.getLeagueId().equals(league.getLeagueId())) {
				matchesFound.add(m);
			}
		}

		return matchesFound;
	}

	/**
	 * Finds a match for a given match id
	 * @param matchId The match id
	 * @return Returns a Match object with id "id"
	 */
	public Match findMatchById(String matchId) {
		ArrayList<Match> matchList = this.getContents(this.xmlPathMatches);

		for (Match m : matchList) {
			if (m.getMatchId().equals(matchId))
				return m;
		}

		return null;
	}

	/**
	 * Finds matches for a given league, initial and final dates
	 * @param league The league object
	 * @param initDate The initial date 
	 * @param finalDate The final date
	 * @return Returns a list of Matches object which is associated with the given league and the actual date is between initDate date and finalDate date 
	 */
	public ArrayList<Match> findMatchesByDate(League league, Date initDate,
			Date finalDate) {
		ArrayList<Match> matchList = this.findMatchesByLeague(league);
		ArrayList<Match> matchesFound = new ArrayList<Match>();

		for (Match match : matchList) {
			Date date = match.getCreationDate();
			if ((date.compareTo(initDate) >= 0)
					&& (date.compareTo(finalDate) <= 0)) {
				matchesFound.add(match);
			}
		}

		return matchesFound;

	}

	/**
	 * Finds matches for a given league, initial and final dates and the given user (player) is
	 * @param user The User object which is in the desired match
	 * @param league The League object which is in the desired match
	 * @param initDate The initial date
	 * @param finalDate The final date
	 * @return Returns a list of Matches object which is associated with the given league, contains the given user and the actual date is between initDate date and finalDate date 
	 */
	public ArrayList<Match> findMatchesByDate(User user, League league,
			Date initDate, Date finalDate) {
		ArrayList<Match> matchList = this.findMatchesByDate(league, initDate,
				finalDate);
		ArrayList<Match> matchesFound = new ArrayList<Match>();

		for (Match match : matchList) {
			if (match.getUserIdWinner().equals(user.getUserId())
					|| match.getUserIdLoser().equals(user.getUserId())) {
				matchesFound.add(match);
			}
		}

		return matchesFound;

	}

}