package br.edu.ufcg.psoo.billiards.persistence;

import java.util.ArrayList;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.WinLoss;

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
	
	public void deleteAllUsers();
	
	public ArrayList<User> getUsersByLeague(League league);
	//----------------------------------------------------------
	
	//--- Methods responsible for league's manager
	public void saveLeague(League league);	
		
	public void deleteAllLeagues();
	
	public ArrayList<League> getLeagues();
	
	public void deleteLeague(League league);
	
	public void putPlayerIntoLeague(League league, User userm, Integer initialHandCap);
	
	public League findLeagueById(String id);
	
	public ArrayList<League> findLeagueByName(String name);
	
	public ArrayList<League> getLeaguesByUser(User user);
	//-----------------------------------------------------
	
	//--- Methods responsible for win/loss 's manager
	public void saveWinLoss(WinLoss winLoss);
	
	public ArrayList<WinLoss> getAllWinLoss();
	
	public void deleteWinLoss(WinLoss winLoss);
	
	public void deleteAllWinLoss();

}
