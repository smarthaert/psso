package br.edu.ufcg.psoo.billiards.persistence;

import java.util.ArrayList;
import java.util.Date;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.Match;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;

public interface PersistenceIF {

	public void setDatabase(String databaseName);
	
	//--- Methods responsible for user's manager	
	public void saveUser(User user);
	
	public ArrayList<User> getUsers();
	
	public User findUserById(String id);
	
	public ArrayList<User> findUserByFirstName(String name);
	
	public ArrayList<User> findUserByLastName(String lastName);
	
	public User findUserByEmail(String email);
			
	public void removeUser(User userId);
	
	public void removeAllUsers();
	
	public ArrayList<User> findUsersByLeague(League league);
	//----------------------------------------------------------
	
	//--- Methods responsible for league's manager
	public void saveLeague(League league);	
		
	public void removeAllLeagues();
	
	public ArrayList<League> getLeagues();
	
	public void removeLeague(League league);
	
	public void putPlayerIntoLeague(League league, User userm, Integer initialHandCap);
	
	public League findLeagueById(String id);
	
	public ArrayList<League> findLeagueByName(String name);
	
	public ArrayList<League> findLeaguesByUser(User user);
	//-----------------------------------------------------
	
	//--- Methods responsible for win/loss 's manager
	public void saveMatch(Match winLoss);
	
	public ArrayList<Match> getAllMatches();
	
	public void removeMatch(Match winLoss);
	
	public void removeAllMatches();
	
	public void saveUserLeague(UserLeague userLeague);
	
	public UserLeague getUserLeague(User user, League league);
	
	public void leaveLeague(User user, League league);
	
	public ArrayList<Match> findMatchesByLeague(League league);
	
	public Match findMatchById(String matchId);

	public ArrayList<Match> findMatchesByDate(League league, Date initDate, Date finalDate);
	
	public ArrayList<Match> findMatchesByDate(User user, League league, Date initDate, Date finalDate); 
	
}