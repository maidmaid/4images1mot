public class Solveur {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Solveur s = new Solveur();
		//System.out.println(s.sql(12, "LLOCUNEIYOMF"));
		System.out.println(s.sql(4, "LLOCUNEIYOMF"));
	}
	
	public String sql(int totalLetters, String letters)
	{
		String clause = "";
		String joinDico = "";
		String concat = "";
		String from = "";
		String sql = "";
		int i = 0;
		
		for (i = 0; i < letters.length(); i++)
		{
			char letter = letters.charAt(i);
			clause += "l = '" + letter + "' OR ";
		}
		clause = clause.substring(0, clause.length() - 4);
		
		for (i = 1; i <= totalLetters; i++)
		{
			String tableA = "a" + i;
			String tableD = "d";
			String column = "l" + i;
			String finalA = tableA + "." + column;
			String finalD = tableD + "." + column;
			concat += finalA + ", ";
			from += "\n(SELECT l AS " + column + " FROM a WHERE " + clause + ") " + tableA + " JOIN ";
			joinDico += "\n" + finalD + " = " + finalA + " AND ";
		}
		concat = concat.substring(0, concat.length() - 2);
		from = from.substring(0, from.length() - 6);
		
		for (i = totalLetters + 1; i <= 25; i++) {
			String tableD = "d";
			String column = "l" + i;
			String finalD = tableD + "." + column;
			joinDico += "\n" + finalD + " = '' AND ";
		}
		joinDico = joinDico.substring(0, joinDico.length() - 5); 
		
		sql = "SELECT DISTINCT CONCAT(" + concat + ") AS mots FROM " + from + " INNER JOIN d4 d ON " + joinDico;
		
		return sql;
	}

}
