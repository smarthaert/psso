class Player implements Comparable{
	String userId
	String lastName
	String numberOfWins
	String numberOfLosses
	String playerStanding
	String order
	
	public int compareTo(Object o) {
		def ply = (Player) o
		return this."${order}".compareTo(ply."${order}")
	}

	public void orderBy(String o) {
		order = o
	}
}
