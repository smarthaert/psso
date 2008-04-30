/**
 * 
 */
package br.edu.ufcg.psoo.billiards.facade;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.Match;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;
import br.edu.ufcg.psoo.billiards.persistence.PersistenceIF;
import br.edu.ufcg.psoo.billiards.persistence.XMLPersistence;
import br.edu.ufcg.psoo.billiards.util.BilliardsUtil;
import bsh.EvalError;
import bsh.Interpreter;

/**
 * @author ivocalado
 * @author thiagobruno
 * 
 */
public class BilliardsFacade {

	private Random random;

	private PersistenceIF persistence;

	private SimpleDateFormat dateFormat;

	private BilliardsUtil billiardsUtil;

	public BilliardsFacade() {
		random = new Random();
		persistence = new XMLPersistence();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		billiardsUtil = new BilliardsUtil();
	}

	/**
	 * This method is responsible for setting the path database
	 * 
	 * @param databaseName
	 *            The database name
	 */
	public void useDatabase(String databaseName) {
		persistence.setDatabase(databaseName);
	}

	/**
	 * This method removes all matches from persistence
	 */
	public void removeAllMatches() {
		persistence.removeAllMatches();
	}

	/**
	 * This method removes all leagues from persistence
	 */
	public void removeAllLeagues() {
		persistence.removeAllLeagues();
	}

	/**
	 * This method removes all users from persistence
	 */
	public void removeAllUsers() {
		persistence.removeAllUsers();
	}

	/**
	 * This method creates a new user and saves it in persistence
	 * 
	 * @param firstName
	 *            The User first name
	 * @param lastName
	 *            The User last name
	 * @param homePhone
	 *            The User home phone
	 * @param workPhone
	 *            The User work phone
	 * @param cellPhone
	 *            The User cell phone
	 * @param picture
	 *            The User picture
	 * @return Returns the User id of the new user
	 * @throws Exception
	 */
	public String createUser(String firstName, String lastName,
			String homePhone, String workPhone, String cellPhone, String email,
			String picture) throws Exception {
		String error = "";

		if (firstName == null || firstName.equals("")) {
			error = "Required data: first name";
		}

		if (lastName == null || lastName.equals("")) {
			error = (error.equals("")) ? "Required data: last name" : error
					+ ", last name";
		}

		if (email == null || email.equals("")) {
			error = (error.equals("")) ? "Required data: email" : error
					+ ", email";
		}

		if (!error.equals(""))
			throw new Exception(error);

		for (User u : persistence.getUsers()) {
			if (u.getEmail().equals(email)) {
				throw new Exception("User with this email exists");
			}
		}
		String id = String.valueOf(random.nextLong());

		User user = new User(id, firstName, lastName, homePhone, workPhone,
				cellPhone, email, picture);

		persistence.saveUser(user);

		return id;
	}

	/**
	 * This method change an attribute from user profile
	 * 
	 * @param id
	 *            identify the user
	 * @param attribute
	 *            which must be changed
	 * @param value
	 *            to be set up
	 * @throws Exception
	 *             thrown when an error happens
	 */
	public void changeUserAttribute(String id, String attribute, String value)
			throws Exception {

		attribute = (attribute == null) ? "" : attribute;
		value = (value == null) ? "" : value;

		User user = persistence.findUserById(id);
		if (user == null) {
			throw new Exception("Could not find user with id=" + id);
		}

		if (attribute.equals("email")) {
			User user2 = persistence.findUserByEmail(value);
			if (user2 != null && !user2.equals(user)) {
				throw new Exception("User with this email exists");
			}
		}

		try { // invokes the correct method to changing
			billiardsUtil.setField(User.class, attribute, user, value);
		} catch (NoSuchFieldException e) {
			if (attribute == null || attribute.equals("")) {
				Exception ex = new Exception(
						"Must provide an attribute to be changed");
				ex.setStackTrace(e.getStackTrace());
				throw ex;
			}
			Exception ex = new Exception("Unknown user attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		} catch (InvocationTargetException ins) {
			throw (Exception) ins.getTargetException();
		} catch (Exception e) {
			throw e;
		}

		persistence.saveUser(user);
	}

	/**
	 * Finds a user by last name. A regular expression can be accepted to make
	 * the search *
	 * 
	 * @param lastName
	 *            of user
	 * @return Returns a string representing a set of users
	 * @throws Exception
	 */
	public String findUserByLastName(String lastName) throws Exception {

		List<User> list = persistence.findUserByLastName(lastName);
		if (list.size() == 0) {
			throw new Exception("Could not find user " + lastName);
		}
		String ret = "[";
		for (User user : list) {
			ret += user.getLastName() + ", ";
		}
		ret = ret.substring(0, ret.length() - 2) + "]";

		return ret;
		// TODO Permitir busca por expressoes regulares
	}

	/**
	 * Gets a value of a user attribute
	 * 
	 * @param id
	 * @param attribute
	 * @return Returns an object containing the value of the given attribute
	 * @throws Exception
	 */
	public Object getUserAttribute(String id, String attribute)
			throws Exception {
		User user = persistence.findUserById(id);
		if (user == null) {
			throw new Exception("There aren't user with id " + id);
		}
		try {
			Object ret = billiardsUtil.getField(User.class, attribute, user);
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown user attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
	}

	/**
	 * This method is responsible for removing a user for a given user id
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser(String id) throws Exception {
		User user = persistence.findUserById(id);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		List<League> leagues = persistence.getLeagues();

		for (League league : leagues) {
			if (league.getOperator().equals(id))
				throw new Exception("Cannot remove league operator");
		}

		for (League league : leagues) {
			if (isLeagueMember(user.getUserId(), league.getLeagueId())) {
				for (int i = 1; i <= getNumberOfMatches(id, league
						.getLeagueId()); i--)
					deleteMatch(getMatch(id, league.getLeagueId(), i));
				leaveLeague(id, league.getLeagueId());
			}

		}

		persistence.removeUser(user);
	}

	/**
	 * Finds a league for a given league name
	 * 
	 * @param name
	 *            The name of the league
	 * @return Returns a set containing all league name which matches the given
	 *         name
	 * @throws Exception
	 */
	public String findLeague(String name) throws Exception {
		List<League> league = persistence.findLeagueByName(name);
		if (league.size() == 0) {
			throw new Exception("Could not find league " + name);
		}

		// / sort list by leaguename
		Collections.sort(league, new Comparator<League>() {

			@Override
			public int compare(League o1, League o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		String ret = "[";
		for (League l : league) {
			ret += l.getName() + ", ";
		}
		ret = ret.substring(0, ret.length() - 2) + "]";
		return ret;
	}

	/**
	 * Stores a new league
	 * 
	 * @param name
	 *            The name of the league
	 * @param operator
	 *            The user id who will be the league operator
	 * @return Returns the league id
	 * @throws Exception
	 */
	public String createLeague(String name, String operator) throws Exception {
		if (name == null || name.equals("")) {
			throw new Exception("Required data: league name");
		}

		if (operator == null || operator.equals("")) {
			throw new Exception("Required data: league operator");
		}

		if (persistence.findUserById(operator) == null) {
			throw new Exception("Unknown user");
		}

		ArrayList<League> leagues = persistence.findLeagueByName(name);
		for (League league : leagues) {
			if (league.getName().equals(name)) {
				throw new Exception("This league already exists");
			}
		}

		String id = String.valueOf(random.nextLong());

		League league = new League(id, operator, name, Calendar.getInstance()
				.getTime());

		persistence.saveLeague(league);

		return id;
	}

	/**
	 * Gets a value of a given league attribute
	 * 
	 * @param idLeague
	 *            The League id
	 * @param attribute
	 *            The attribute name
	 * @return Returns the attribute value
	 * @throws Exception
	 */
	public Object getLeagueAttribute(String idLeague, String attribute)
			throws Exception {
		League league = persistence.findLeagueById(idLeague);
		if (league == null) {
			throw new Exception("There aren't league with id " + idLeague);
		}
		try {
			Object ret = billiardsUtil
					.getField(League.class, attribute, league);
			if (attribute.equals("creationDate") && ret != null) {
				Date date = (Date) ret;
				ret = new StringBuilder(dateFormat.format(date)).toString();

			}
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown league attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}

	}

	/**
	 * Gets all user's id
	 * 
	 * @return Returns a list of string with all user's id
	 */

	public List<String> getAllUsersId() {
		ArrayList<String> ids = new ArrayList<String>();
		List<User> list = persistence.getUsers();
		for (User user : list) {
			ids.add(user.getUserId());
		}
		return ids;
	}

	/**
	 * Gets all league's id
	 * 
	 * @return Returns a list of string with all league's id
	 */
	public List<String> getAllLeaguesId() {
		ArrayList<String> ids = new ArrayList<String>();
		List<League> list = persistence.getLeagues();
		for (League l : list) {
			ids.add(l.getLeagueId());
		}
		return ids;
	}

	/**
	 * Gets the actual date
	 * 
	 * @return Returns a string representing the actual date
	 */
	public String todaysDate() {
		return new StringBuilder(dateFormat.format(Calendar.getInstance()
				.getTime())).toString();
	}

	/**
	 * Changes the value of a league attribute
	 * 
	 * @param idLeague
	 *            The League id
	 * @param attribute
	 *            The league attribute name
	 * @param value
	 *            The new value for this attribute
	 * @throws Exception
	 */
	public void changeLeagueAttribute(String idLeague, String attribute,
			String value) throws Exception {
		League league = persistence.findLeagueById(idLeague);
		attribute = (attribute == null) ? "" : attribute;
		value = (value == null) ? "" : value;
		if (value.equals("")) {
			throw new Exception("Required data: league " + attribute);
		}

		if (attribute.equals("operator")
				&& persistence.findUserById(value) == null) {
			throw new Exception("Unknown user");
		}

		Object param = value;

		if (attribute.equals("creationDate")) {
			try {
				param = dateFormat.parse(value);
			} catch (Exception e) {
				throw new Exception("Invalid date");
			}
		}

		if (attribute.equals("name")
				&& persistence.findLeagueByName(value).size() != 0
				&& !getLeagueAttribute(idLeague, "name").equals(value)) {
			throw new Exception("This league already exists");
		}

		try { // invokes the correct method to changing
			billiardsUtil.setField(League.class, attribute, league, param);
		} catch (NoSuchFieldException e) {
			if (attribute == null || attribute.equals("")) {
				Exception ex = new Exception(
						"Must provide an attribute to be changed");
				ex.setStackTrace(e.getStackTrace());
				throw ex;
			}
			Exception ex = new Exception("Unknown league attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		} catch (InvocationTargetException ins) {
			throw (Exception) ins.getTargetException();
		} catch (Exception e) {
			throw e;
		}

		persistence.saveLeague(league);

	}

	/**
	 * Removes a league for a given league id
	 * 
	 * @param id
	 *            The league id
	 * @throws Exception
	 */
	public void deleteLeague(String id) throws Exception {
		League league = persistence.findLeagueById(id);
		if (league == null)
			throw new Exception("Unknown league");

		for (int i = 1; i <= getNumberOfMatches(id); i++)
			deleteMatch(getMatch(id, i));
		persistence.removeLeague(league);
	}

	/**
	 * This method is responsible for checking if a given user is member of
	 * given league
	 * 
	 * @param idUser
	 *            The User id
	 * @param idLeague
	 *            The League id
	 * @return Returns true or false depending if the user is member or not
	 * @throws Exception
	 */
	public Boolean isLeagueMember(String idUser, String idLeague)
			throws Exception {
		User user = persistence.findUserById(idUser);
		if (user == null) {
			throw new Exception("Unknown user");
		}
		League league = persistence.findLeagueById(idLeague);
		if (league == null) {
			throw new Exception("Unknown league");
		}
		ArrayList<User> users = persistence.findUsersByLeague(league);
		return users.contains(user);
	}

	/**
	 * Finds all leagues which the given user is member
	 * 
	 * @param userId
	 *            The user id
	 * @return Returns a string containing all leagues
	 * @throws Exception
	 */
	public String getPlayerLeagues(String userId) throws Exception {
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		ArrayList<League> leagues = persistence.findLeaguesByUser(user);
		if (leagues.size() == 0) {
			return "";
		}
		String ret = "";
		for (League l : leagues) {
			ret += l.getName() + ", ";
		}
		return ret.substring(0, ret.length() - 2);
	}

	/**
	 * Finds all users (players) who are members of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @return Returns a string containing all users
	 * @throws Exception
	 */
	public String getLeagueMembers(String leagueId) throws Exception {
		League league = persistence.findLeagueById(leagueId);

		if (league == null) {
			throw new Exception("Unknown league");
		}

		ArrayList<User> users = persistence.findUsersByLeague(league);
		String ret = "";
		for (User user : users) {
			ret += user.getLastName() + ", ";
		}
		return ret.substring(0, ret.length() - 2);

	}

	/**
	 * 
	 * @param format
	 * @throws Exception
	 */
	public void dateFormat(String format) throws Exception {
		if (format == null || format.equals("")) {
			throw new Exception("Unknown date format");
		}
		format = format.replace("mm", "MM");
		try {
			dateFormat = new SimpleDateFormat(format);
			dateFormat.setLenient(false);
		} catch (IllegalArgumentException e) {
			throw new Exception("Unknown date format");
		}

	}

	/**
	 * Gets the date format
	 * 
	 * @return Returns a DateFormat object
	 */
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * Joins a user (player) in a league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueid
	 *            The League id
	 * @param initialHandcap
	 *            The user initial handcap
	 * @throws Exception
	 */
	public void joinLeague(String userId, String leagueid, String initialHandcap)
			throws Exception {
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		League league = persistence.findLeagueById(leagueid);

		if (league == null) {
			throw new Exception("Unknown league");
		}

		if (initialHandcap == null || initialHandcap.equals("")) {
			throw new Exception("Must provide initial player handicap");
		}

		Integer integer = Integer.parseInt(initialHandcap);

		if (integer < 0) {
			throw new Exception("Handicap cant be negative");
		}

		ArrayList<User> list = persistence.findUsersByLeague(league);
		if (list.contains(user)) {
			throw new Exception("User is already a league member");
		}

		persistence.putPlayerIntoLeague(league, user, integer);

	}

	/**
	 * Gets the UserLeague attribute value for a given attribute of the
	 * UserLeague class
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @param attribute
	 *            The attribute name
	 * @throws Exception
	 */
	public Object getUserLeagueAttribute(String userId, String leagueId,
			String attribute) throws Exception {
		attribute = (attribute == null) ? "" : attribute;
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		UserLeague uleague = persistence.getUserLeague(user, league);
		if (uleague == null) {
			throw new Exception("User is not a league member");
		}
		try {
			Object ret = billiardsUtil.getField(UserLeague.class, attribute,
					uleague);
			if (attribute.equals("joinDate") && ret != null) {
				Date date = (Date) ret;
				ret = new StringBuilder(dateFormat.format(date)).toString();

			}
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown user attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}

	}

	/**
	 * Desassociate the user (player) of a given league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @throws Exception
	 */
	public void leaveLeague(String userId, String leagueId) throws Exception {
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		if (!persistence.findUsersByLeague(league).contains(user)) {
			throw new Exception("User is not a league member");
		}

		if (league.getOperator().equals(user.getUserId())) {
			throw new Exception("Operator cannot leave league");
		}

		persistence.leaveLeague(user, league);

	}

	/**
	 * Gets the number of stored matches of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @return Returns the number of matches
	 * @throws Exception
	 */
	public Integer getNumberOfMatches(String leagueId) throws Exception {
		return getMatches(leagueId).size();
	}

	/**
	 * Gets all matches of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @return Returns a list of all stored matches
	 * @throws Exception
	 */
	private ArrayList<Match> getMatches(String leagueId) throws Exception {
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		return persistence.findMatchesByLeague(league);
	}

	/**
	 * Gets the number of wins of a given user (player) in a given league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @return Returns the number of wins
	 * @throws Exception
	 */
	public Integer getNumberOfWins(String userId, String leagueId)
			throws Exception {

		ArrayList<Match> list = getMatches(leagueId); // It can thrown
		// 'Unknown league'
		// error

		User user = persistence.findUserById(userId);

		if (user == null) {
			throw new Exception("Unknown user");
		}

		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdWinner().equals(user.getUserId())) {
				i++;
			}
		}
		return i;
	}

	/**
	 * Gets the number of losses of a given user (player) in a given league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @return Returns the number of losses
	 * @throws Exception
	 */
	public Integer getNumberOfLosses(String userId, String leagueId)
			throws Exception {
		ArrayList<Match> list = getMatches(leagueId); // It can thrown
		// 'Unknown league'
		// error

		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdLoser().equals(user.getUserId())) {
				i++;
			}
		}
		return i;
	}

	/**
	 * Gets the number of matches of a given user in a given league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @return Returns the number of matches
	 * @throws Exception
	 */
	public Integer getNumberOfMatches(String userId, String leagueId)
			throws Exception {

		ArrayList<Match> list = getMatches(leagueId);
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}
		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdLoser().equals(user.getUserId())
					|| matches.getUserIdWinner().equals(user.getUserId())) {
				i++;
			}
		}
		return i;
	}

	/**
	 * Stores the match result of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @param date
	 *            The match date
	 * @param winnerId
	 *            The User (winner) id
	 * @param loserId
	 *            The User (loser) id
	 * @return Returns the match id
	 * @throws Exception
	 */
	public String addMatchResult(String leagueId, String date, String winnerId,
			String loserId) throws Exception {
		return addMatchResult(leagueId, date, winnerId, loserId, null, null,
				null, null);
	}

	/**
	 * Stores the match result of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @param date
	 *            The match date
	 * @param winnerId
	 *            The User (winner) id
	 * @param loserId
	 *            The User (loser) id
	 * @param lenght
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @return Returns the result id
	 * @throws Exception
	 */
	public String addMatchResult(String leagueId, String date, String winnerId,
			String loserId, String lenght, String score,
			String longestRunForWinner, String longestRunForLoser)
			throws Exception {

		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}
		User winner = persistence.findUserById(winnerId);
		User loser = persistence.findUserById(loserId);
		if (winner == null || loser == null) {
			throw new Exception("Unknown user");
		}

		if (winner.equals(loser)) {
			throw new Exception("Users must be different");
		}

		Integer l = null;
		Integer sc = null;
		Integer lrWinner = null;
		Integer lrLoser = null;

		if (!(lenght == null && score == null && longestRunForLoser == null && longestRunForWinner == null)) {
			try {
				l = Integer.parseInt(lenght);
				if (l <= 0) {
					throw new Exception("Invalid match length");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid match length");
			}

			try {
				sc = Integer.parseInt(score);
				if (sc < 0 || sc >= l) {
					throw new Exception("Invalid score");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid score");
			}

			try {
				lrWinner = Integer.parseInt(longestRunForWinner);
				lrLoser = Integer.parseInt(longestRunForLoser);
				if (lrWinner < 1 || lrWinner > l || lrLoser < 0 || lrLoser > sc) {
					throw new Exception("Invalid run");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid run");
			}

		}

		Date date2 = null;
		try {
			date2 = dateFormat.parse(date);
		} catch (Exception e) {
			throw new Exception("Invalid date");
		}
		String id = String.valueOf(random.nextLong());
		Match match = new Match(id, winnerId, loserId, leagueId, l, sc,
				lrWinner, lrLoser, date2);
		persistence.saveMatch(match);
		persistence.putPlayerIntoLeague(league, winner, 0);
		persistence.putPlayerIntoLeague(league, loser, 0);
		return id;
	}

	/**
	 * Gets a match of a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @param index
	 * @return Returns the match
	 * @throws Exception
	 */
	public String getMatch(String leagueId, Integer index) throws Exception {
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		ArrayList<Match> list = persistence.findMatchesByLeague(league);

		try {
			return list.get(index - 1).getMatchId();
		} catch (Exception e) {
			throw new Exception("Invalid index");
		}

	}

	/**
	 * Gets a match of a given league and user
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @param index
	 * @return Returns the match
	 * @throws Exception
	 */
	public String getMatch(String userId, String leagueId, Integer index)
			throws Exception {
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		ArrayList<Match> list = persistence.findMatchesByLeague(league);
		ArrayList<Match> list2 = new ArrayList<Match>();
		for (Match match : list) {
			if (match.getUserIdLoser().equals(userId)
					|| match.getUserIdWinner().equals(userId)) {
				list2.add(match);
			}
		}

		Collections.sort(list2, new Comparator<Match>() {

			public int compare(Match o1, Match o2) {
				return o1.getCreationDate().compareTo(o2.getCreationDate());
			}

		});

		try {
			return list2.get(index - 1).getMatchId();
		} catch (Exception e) {
			throw new Exception("Invalid index");
		}
	}

	/**
	 * Gets the date of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns a date in a string format
	 */
	public String getMatchDate(String matchId) {
		Match match = persistence.findMatchById(matchId);
		StringBuilder ret = new StringBuilder(dateFormat.format(match
				.getCreationDate()));
		return ret.toString();
	}

	/**
	 * Gets the winner (user) of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the user id
	 * @throws Exception
	 */
	public String getMatchWinner(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		return match.getUserIdWinner();
	}

	/**
	 * Gets the loser (user) of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the user id
	 * @throws Exception
	 */
	public String getMatchLoser(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		return match.getUserIdLoser();
	}

	/**
	 * Get the league of a given match
	 * 
	 * @param matchId
	 *            The match id
	 * @return Returns the league id
	 * @throws Exception
	 */
	public String getMatchLeague(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		return match.getLeagueId();
	}

	/**
	 * Gets the length of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the match's length
	 * @throws Exception
	 */
	public String getMatchLength(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		Integer integer = match.getLength();
		return (integer == null) ? "" : String.valueOf(integer);
	}

	/**
	 * Gets the score of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the match's score
	 * @throws Exception
	 */
	public String getMatchScore(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		Integer ret = match.getScore();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * Gets the LongestRunForWinner of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the match's LongestRunForWinner
	 * @throws Exception
	 */
	public String getMatchLongestRunForWinner(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		Integer ret = match.getLongestRunForWinner();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * Gets the MatchLongestRunForLoser of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @return Returns the match's MatchLongestRunForLoser
	 * @throws Exception
	 */
	public String getMatchLongestRunForLoser(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		Integer ret = match.getLongestRunForLoser();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * Updates the result of a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @param date
	 *            The match date
	 * @param winnerId
	 *            The user (winner) id
	 * @param loserId
	 *            The user (loser) id
	 * @param lenght
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @throws Exception
	 */
	public void updateMatchResult(String matchId, String date, String winnerId,
			String loserId, String lenght, String score,
			String longestRunForWinner, String longestRunForLoser)
			throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}

		Date date2 = null;
		try {
			date2 = dateFormat.parse(date);
		} catch (Exception e) {
			Exception ex = new Exception("Invalid date");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
		User winner = persistence.findUserById(winnerId);
		User loser = persistence.findUserById(loserId);
		if (winner == null || loser == null) {
			throw new Exception("Unknown user");
		}
		if (winner.equals(loser)) {
			throw new Exception("Users must be different");
		}

		Integer l = null;
		Integer sc = null;
		Integer lrWinner = null;
		Integer lrLoser = null;

		if (!(lenght == null && score == null && longestRunForLoser == null && longestRunForWinner == null)) {
			try {
				l = Integer.parseInt(lenght);
				if (l <= 0) {
					throw new Exception("Invalid match length");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid match length");
			}

			try {
				sc = Integer.parseInt(score);
				if (sc < 0 || sc >= l) {
					throw new Exception("Invalid score");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid score");
			}

			try {
				lrWinner = Integer.parseInt(longestRunForWinner);
				lrLoser = Integer.parseInt(longestRunForLoser);
				if (lrWinner < 1 || lrWinner > l || lrLoser < 0 || lrLoser > sc) {
					throw new Exception("Invalid run");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Invalid run");
			}

		}
		match.setCreationDate(date2);
		match.setUserIdWinner(winnerId);
		match.setUserIdLoser(loserId);
		match.setLength(l);
		match.setScore(sc);
		match.setLongestRunForWinner(lrWinner);
		match.setLongestRunForLoser(lrLoser);

		persistence.saveMatch(match);
		persistence.putPlayerIntoLeague(persistence.findLeagueById(match.getLeagueId()), winner, 0);
		persistence.putPlayerIntoLeague(persistence.findLeagueById(match.getLeagueId()), loser, 0);
	}

	/**
	 * Removes a given match
	 * 
	 * @param matchId
	 *            The Match id
	 * @throws Exception
	 */
	public void deleteMatch(String matchId) throws Exception {
		Match match = persistence.findMatchById(matchId);
		if (match == null) {
			throw new Exception("Unknown match");
		}
		persistence.removeMatch(match);
	}

	/**
	 * Gets all matches of a given league and dates
	 * 
	 * @param leagueId
	 *            The League id
	 * @param startDate
	 *            The match start date
	 * @param endDate
	 *            The match final date
	 * @param index
	 * @throws Exception
	 */
	public String getMatchByDate(String leagueId, String startDate,
			String endDate, Integer index) throws Exception {
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = dateFormat.parse(startDate);
			eDate = dateFormat.parse(endDate);
		} catch (Exception e) {
			throw new Exception("Invalid date");
		}
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		ArrayList<Match> list = persistence.findMatchesByDate(league, sDate,
				eDate);
		Collections.sort(list, new Comparator<Match>() {
			public int compare(Match o1, Match o2) {
				return o1.getCreationDate().compareTo(o2.getCreationDate());
			}
		});

		try {
			return list.get(index - 1).getMatchId();
		} catch (Exception e) {
			throw new Exception("Invalid index");
		}

	}

	/**
	 * Gets all matches of a league and dates where a given user is
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @param startDate
	 *            Start date
	 * @param endDate
	 *            Final date
	 * @param index
	 * @throws Exception
	 */
	public String getMatchByDate(String userId, String leagueId,
			String startDate, String endDate, Integer index) throws Exception {
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = dateFormat.parse(startDate);
			eDate = dateFormat.parse(endDate);
		} catch (Exception e) {
			throw new Exception("Invalid date");
		}
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		ArrayList<Match> list = persistence.findMatchesByDate(user, league,
				sDate, eDate);
		Collections.sort(list, new Comparator<Match>() {
			public int compare(Match o1, Match o2) {
				return o1.getCreationDate().compareTo(o2.getCreationDate());
			}
		});

		try {
			return list.get(index - 1).getMatchId();
		} catch (Exception e) {
			throw new Exception("Invalid index");
		}

	}

	/**
	 * Defines the expression for a given league
	 * 
	 * @param leagueId
	 *            The League id
	 * @param expression
	 *            The League expression
	 * @throws Exception
	 */
	public void defineStandingsExpression(String leagueId, String expression)
			throws Exception {
		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}
		if (expression == null || expression.equals("")) {
			throw new Exception("Syntax error in standings expression");
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("seasonWins", random.nextInt());
		map.put("seasonLosses", random.nextInt());

		billiardsUtil.evaluateExpression(expression, map);

		league.setStandingExpression(expression);
		persistence.saveLeague(league);
	}

	/**
	 * Gets the player standings of a given user and league
	 * 
	 * @param userId
	 *            The User id
	 * @param leagueId
	 *            The League id
	 * @return Returns the user standings
	 * @throws Exception
	 */
	public String getPlayerStanding(String userId, String leagueId)
			throws Exception {
		User user = persistence.findUserById(userId);
		if (user == null) {
			throw new Exception("Unknown user");
		}

		League league = persistence.findLeagueById(leagueId);
		if (league == null) {
			throw new Exception("Unknown league");
		}

		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("seasonLosses", getNumberOfLosses(userId, leagueId));
		map.put("seasonWins", getNumberOfWins(userId, leagueId));

		String ret = billiardsUtil.evaluateExpression(league
				.getStandingExpression(), map);
		return ret;

	}

}
