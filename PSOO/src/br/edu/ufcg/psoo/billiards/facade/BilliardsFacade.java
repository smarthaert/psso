/**
 * 
 */
package br.edu.ufcg.psoo.billiards.facade;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.edu.ufcg.psoo.billiards.beans.League;
import br.edu.ufcg.psoo.billiards.beans.Match;
import br.edu.ufcg.psoo.billiards.beans.User;
import br.edu.ufcg.psoo.billiards.beans.UserLeague;
import br.edu.ufcg.psoo.billiards.persistence.PersistenceIF;
import br.edu.ufcg.psoo.billiards.persistence.XMLPersistence;

/**
 * @author ivocalado
 * @author thiagobruno
 * 
 */
public class BilliardsFacade {

	private Random random;

	private PersistenceIF persistence;

	private SimpleDateFormat dateFomat;

	public BilliardsFacade() {
		random = new Random();
		persistence = new XMLPersistence();
		dateFomat = new SimpleDateFormat("dd/MM/yyyy");
	}

	/**
	 * 
	 * @param databaseName
	 */
	public void useDatabase(String databaseName) {
		persistence.setDatabase(databaseName);
	}

	/**
	 * This method removes all matches from persistence
	 */
	public void removeAllMatches() {
		persistence.deleteAllMatches();
	}

	/**
	 * This method removes all leagues from persistence
	 */
	public void removeAllLeagues() {
		persistence.deleteAllLeagues();
	}

	/**
	 * This method removes all users from persistence
	 */
	public void removeAllUsers() {
		persistence.deleteAllUsers();
	}

	/**
	 * This method creates a new user and saves it in persistence
	 * 
	 * @param firstName
	 * @param lastName
	 * @param homePhone
	 * @param workPhone
	 * @param cellPhone
	 * @param picture
	 * @return the user Id of the new user
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
			error = (error.equals("")) ? "Required data:" : error + ", email";
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
	 *            to be set
	 * @throws Exception
	 *             thrown when an error had happened
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
			setField(User.class, attribute, user, value);
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
	 * Find an user by last name. An regular expression can be accepted to
	 * search
	 * 
	 * @param lastName
	 *            of user
	 * @return a string representing set of user
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
	 * 
	 * @param id
	 * @param attribute
	 * @return
	 * @throws Exception
	 */
	public Object getUserAttribute(String id, String attribute)
			throws Exception {
		User user = persistence.findUserById(id);
		if (user == null) {
			throw new Exception("There aren't user with id " + id);
		}
		try {
			Object ret = getField(User.class, attribute, user);
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown user attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser(String id) throws Exception {
		List<League> leagues = persistence.getLeagues();
		for (League league : leagues) {
			if (league.getOperator().equals(id)) {
				throw new Exception("Cannot remove league operator");
			}
		}

		persistence.removeUser(persistence.findUserById(id));
	}

	/**
	 * 
	 * @param name
	 * @return
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
	 * 
	 * @param name
	 * @param operator
	 * @return
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
	 * 
	 * @param idLeague
	 * @param attribute
	 * @return
	 * @throws Exception
	 */
	public Object getLeagueAttribute(String idLeague, String attribute)
			throws Exception {

		League league = persistence.findLeagueById(idLeague);
		if (league == null) {
			throw new Exception("There aren't league with id " + idLeague);
		}
		try {
			Object ret = getField(League.class, attribute, league);
			if (attribute.equals("creationDate") && ret != null) {
				Date date = (Date) ret;
				ret = new StringBuilder(dateFomat.format(date)).toString();

			}
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown league attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}

	}

	/**
	 * 
	 * @return
	 */
	public String todaysDate() {
		return new StringBuilder(dateFomat.format(Calendar.getInstance()
				.getTime())).toString();
	}

	/**
	 * 
	 * @param idLeague
	 * @param attribute
	 * @param value
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

		if (attribute.equals("name")
				&& persistence.findLeagueByName(value).size() != 0) {
			throw new Exception("This league already exists");
		}

		try { // invokes the correct method to changing
			setField(League.class, attribute, league, value);
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
	 * 
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteLeague(String id) throws Exception {
		League league = persistence.findLeagueById(id);
		if (league == null)
			throw new Exception("Unknown league");

		persistence.deleteLeague(league);
	}

	/**
	 * 
	 * @param idUser
	 * @param idLeague
	 * @return
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
	 * 
	 * @param userId
	 * @return
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
	 * 
	 * @param leagueId
	 * @return
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
	 */
	public void dateFormat(String format) {
		dateFomat = new SimpleDateFormat(format);
	}

	/**
	 * 
	 * @param userId
	 * @param leagueid
	 * @param initialHandcap
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
	 * 
	 * @param userId
	 * @param leagueId
	 * @param attribute
	 * @return
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
			Object ret = getField(UserLeague.class, attribute, uleague);
			if (attribute.equals("joinDate") && ret != null) {
				Date date = (Date) ret;
				ret = new StringBuilder(dateFomat.format(date)).toString();

			}
			return ret;
		} catch (Exception e) {
			Exception ex = new Exception("Unknown user attribute");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}

	}

	/**
	 * 
	 * @param userId
	 * @param leagueId
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
	 * 
	 * @param leagueId
	 * @return
	 * @throws Exception
	 */
	public Integer getNumberOfMatches(String leagueId) throws Exception {
		return getMatches(leagueId).size();
	}

	/**
	 * 
	 * @param leagueId
	 * @return
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
	 * 
	 * @param userId
	 * @param leagueId
	 * @return
	 * @throws Exception
	 */
	public Integer getNumberOfWins(String userId, String leagueId)
			throws Exception {
		ArrayList<Match> list = getMatches(leagueId);
		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdWinner().equals(userId)) {
				i++;
			}
		}
		return i;
	}

	/**
	 * 
	 * @param userId
	 * @param leagueId
	 * @return
	 * @throws Exception
	 */
	public Integer getNumberOfLosses(String userId, String leagueId)
			throws Exception {
		ArrayList<Match> list = getMatches(leagueId);
		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdLoser().equals(userId)) {
				i++;
			}
		}
		return i;
	}

	/**
	 * 
	 * @param userId
	 * @param leagueId
	 * @return
	 * @throws Exception
	 */
	public Integer getNumberOfMatches(String userId, String leagueId)
			throws Exception {
		ArrayList<Match> list = getMatches(leagueId);
		int i = 0;
		for (Match matches : list) {
			if (matches.getUserIdLoser().equals(userId)
					|| matches.getUserIdWinner().equals(userId)) {
				i++;
			}
		}
		return i;
	}

	/**
	 * 
	 * @param leagueId
	 * @param date
	 * @param winnerId
	 * @param loserId
	 * @return
	 * @throws Exception
	 */
	public String addMatchResult(String leagueId, String date, String winnerId,
			String loserId) throws Exception {
		return addMatchResult(leagueId, date, winnerId, loserId, null, null,
				null, null);
	}

	/**
	 * 
	 * @param leagueId
	 * @param date
	 * @param winnerId
	 * @param loserId
	 * @param lenght
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @return
	 * @throws Exception
	 */
	public String addMatchResult(String leagueId, String date, String winnerId,
			String loserId, Integer lenght, Integer score,
			Integer longestRunForWinner, Integer longestRunForLoser)
			throws Exception {
		String id = String.valueOf(random.nextLong());
		Date date2;
		try {
			date2 = dateFomat.parse(date);
			Match match = new Match(id, winnerId, loserId, leagueId, lenght,
					score, longestRunForWinner, longestRunForLoser, date2);
			persistence.saveMatch(match);
			return id;
		} catch (ParseException e) {
			throw new Exception("Invalid date");
		}
	}

	/**
	 * 
	 * @param leagueId
	 * @param index
	 * @return
	 */
	public String getMatch(String leagueId, Integer index) {
		League league = persistence.findLeagueById(leagueId);
		ArrayList<Match> list = persistence.findMatchesByLeague(league);
		return list.get(index - 1).getMatchId();
	}

	public String getMatchDate(String matchId) {
		Match match = persistence.findMatchById(matchId);
		StringBuilder ret = new StringBuilder(dateFomat.format(match
				.getCreationDate()));
		return ret.toString();
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchWinner(String matchId) {
		Match match = persistence.findMatchById(matchId);
		return match.getUserIdWinner();
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchLoser(String matchId) {
		Match match = persistence.findMatchById(matchId);
		return match.getUserIdLoser();
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchLength(String matchId) {
		Match match = persistence.findMatchById(matchId);
		Integer integer = match.getLength();
		return (integer == null) ? "" : String.valueOf(integer);
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchScore(String matchId) {
		Match match = persistence.findMatchById(matchId);
		Integer ret = match.getScore();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchLongestRunForWinner(String matchId) {
		Match match = persistence.findMatchById(matchId);
		Integer ret = match.getLongestRunForWinner();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * 
	 * @param matchId
	 * @return
	 */
	public String getMatchLongestRunForLoser(String matchId) {
		Match match = persistence.findMatchById(matchId);
		Integer ret = match.getLongestRunForLoser();
		return (ret == null) ? "" : String.valueOf(ret);
	}

	/**
	 * 
	 * @param matchId
	 * @param date
	 * @param winnerId
	 * @param loserId
	 * @param length
	 * @param score
	 * @param longestRunForWinner
	 * @param longestRunForLoser
	 * @throws Exception
	 */
	public void updateMatchResult(String matchId, String date, String winnerId,
			String loserId, Integer length, Integer score,
			Integer longestRunForWinner, Integer longestRunForLoser)
			throws Exception {
		Match match = persistence.findMatchById(matchId);
		try {
			Date date2 = dateFomat.parse(date);
			match.setCreationDate(date2);
			match.setUserIdWinner(winnerId);
			match.setUserIdLoser(loserId);
			match.setLength(length);
			match.setScore(score);
			match.setLongestRunForWinner(longestRunForWinner);
			match.setLongestRunForLoser(longestRunForLoser);

			persistence.saveMatch(match);

		} catch (ParseException e) {
			Exception ex = new Exception("Invalid date");
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
	}

	/***************************************************************************
	 * Shared methods ******************
	 * 
	 * 
	 * /**
	 * 
	 * @param a
	 * @return
	 */
	private static String upper(String a) {
		return a.substring(0, 1).toUpperCase() + a.substring(1);
	}

	/**
	 * 
	 * @param clz
	 * @param attribute
	 * @param ob
	 * @return
	 * @throws Exception
	 */
	private Object getField(Class clz, String attribute, Object ob)
			throws Exception {
		Field field = clz.getDeclaredField(attribute);
		if (field.getModifiers() == Field.PUBLIC + Field.DECLARED) {
			return field.get(ob);
		}
		String methodName = "get" + upper(field.getName());
		Method method = ob.getClass().getMethod(methodName);
		return method.invoke(ob);
	}

	/**
	 * 
	 * @param clz
	 * @param attribute
	 * @param ob
	 * @param args
	 * @throws Exception
	 */
	private void setField(Class clz, String attribute, Object ob, Object args)
			throws Exception {
		Field field = clz.getDeclaredField(attribute);
		if (field.getModifiers() == Field.PUBLIC + Field.DECLARED) {
			field.set(ob, args);
			return;
		}
		String methodName = "set" + upper(field.getName());
		ob.getClass().getMethod(methodName, args.getClass()).invoke(ob, args);
	}

}
