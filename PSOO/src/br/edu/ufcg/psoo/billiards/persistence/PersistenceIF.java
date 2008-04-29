package br.edu.ufcg.psoo.billiards.persistence;

import java.util.ArrayList;
import java.util.Date;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.Match;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;

public interface PersistenceIF {

	/**
	 * This method initialize the file path which will contain all information about Users, Leagues, Matches, etc
	 * @param databaseName All files will have a common name followed by its property. For instance: teste-users.xml, teste-leagues.xml, etc. This param will hold this common name
	 */
	public void setDatabase(String databaseName);
	
	/**
	 * Save a given player
	 * @param user The user
	 */
	public void saveUser(User user);
	
	/**
	 * Get all stored users
	 * @return Returns a list of users
	 */
	public ArrayList<User> getUsers();
	
	/**
	 * This method is responsible for finding a user for a given user id
	 * @param id The User id
	 * @return Returns a User with id "id"
	 */
	public User findUserById(String id);
	
	/**
	 * This method is responsible for finding a user for a given user name
	 * @param match The user name
	 * @return Returns a list of users which can contain the given name 
	 */	
	public ArrayList<User> findUserByFirstName(String match);
	
	/**
	 * This method is responsible for finding a user for a given player last name
	 * @param match The user last name
	 * @return Returns a list of users
	 */
	public ArrayList<User> findUserByLastName(String match);
	
	/**
	 * This method is responsible for finding a user for a given player email address
	 * @param email The user email address
	 * @return Returns a User
	 */
	public User findUserByEmail(String email);
			
	/**
	 * This method is responsible for removing a user for a given user id
	 * @param userId The user id 
	 */
	public void removeUser(User userId);
	
	/**
	 * This method is responsible for removing all users
	 */
	public void removeAllUsers();
	
	/**
	 * Finds all users (players) who are in a given league
	 * @param league The League which all users (players) must be
	 * @return Returns a list of User (player) objects
	 */
	public ArrayList<User> findUsersByLeague(League league);

	/**
	 * Save a league
	 * @param league The league to be saved
	 */
	public void saveLeague(League league);	
		
	/**
	 * This method is responsible for removing all leagues
	 */
	public void removeAllLeagues();

	/**
	 * Get all stored Leagues
	 * @return Returns a list of leagues 
	 */
	public ArrayList<League> getLeagues();
	
	/**
	 * This method is responsible for removing a given league object
	 * @param league The league which will be removed
	 */
	public void removeLeague(League league);
	
	/**
	 * This method is responsible for joining a user (player) to a league
	 * @param league The league which the player will be
	 * @param userm The user who will join the league 
	 */
	public void putPlayerIntoLeague(League league, User userm, Integer initialHandCap);
	
	/**
	 * This method is responsible for finding a league for a given league id
	 * @param id The league id
	 * @return Returns the desired League object 
	 */
	public League findLeagueById(String id);
	
	/**
	 * This method is responsible for finding a set of leagues for a given league name
	 * @param match The league name
	 * @return Returns a list of League objects which contains the given league name
	 */
	public ArrayList<League> findLeagueByName(String match);
	
	/**
	 * Finds a set of league which a given user (player) is.
	 * @param user The user who is in the league
	 * @return Returns a list of League objects which the given user is.
	 */
	public ArrayList<League> findLeaguesByUser(User user);

	/**
	 * Saves a given match
	 * @param winLoss The match which will be stored
	 */
	public void saveMatch(Match winLoss);
	
	/**
	 * Gets all stored matches
	 * @return Returns a list of all matches 
	 */
	public ArrayList<Match> getAllMatches();
	
	/**
	 * This method is responsible for removing a given match
	 * @param winLoss The match which will be deleted 
	 */
	public void removeMatch(Match winLoss);
	
	/**
	 * This method is responsible for removing all matches
	 */
	public void removeAllMatches();
	
	/**
	 * This method is responsible for storing a user/league relationship
	 * @param userLeague The UserLeague object which will be stored  
	 */
	public void saveUserLeague(UserLeague userLeague);
	
	/**
	 * Get a relationship between a given user and a given league
	 * @param user The user who will be in the league
	 * @param league The league which is in the given league
	 * @return Returns the desired UserLeague object 
	 */
	public UserLeague getUserLeague(User user, League league);
	
	/**
	 * Removes (disassociate) a given user from a given league
	 * @param user The User object
	 * @param league The League object
	 */
	public void leaveLeague(User user, League league);
	
	/**
	 * Finds a set of matches for a given league
	 * @param league The league which is associated to the match
	 * @return Returns a list of matches which is associated with the given league
	 */
	public ArrayList<Match> findMatchesByLeague(League league);
	
	/**
	 * Finds a match for a given match id
	 * @param matchId The match id
	 * @return Returns a Match object with id "id"
	 */
	public Match findMatchById(String matchId);

	/**
	 * Finds matches for a given league, initial and final dates
	 * @param league The league object
	 * @param initDate The initial date 
	 * @param finalDate The final date
	 * @return Returns a list of Matches object which is associated with the given league and the actual date is between initDate date and finalDate date 
	 */
	public ArrayList<Match> findMatchesByDate(League league, Date initDate, Date finalDate);
	
	/**
	 * Finds matches for a given league, initial and final dates and the given user (player) is
	 * @param user The User object which is in the desired match
	 * @param league The League object which is in the desired match
	 * @param initDate The initial date
	 * @param finalDate The final date
	 * @return Returns a list of Matches object which is associated with the given league, contains the given user and the actual date is between initDate date and finalDate date 
	 */
	public ArrayList<Match> findMatchesByDate(User user, League league, Date initDate, Date finalDate); 
	
}