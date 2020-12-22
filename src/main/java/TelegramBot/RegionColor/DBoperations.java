package TelegramBot.RegionColor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBoperations {
	
	
	// metodo per ricavare le informazioni relative alla regione passata in input
	public ResultSet info_sql(Connection conn, String region) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String qry = "SELECT regione.nome_regione, mandato.colore, mandato.descrizione, assegnazione.id_assegnazione, assegnazione.inizio, assegnazione.fine "
				+ "FROM regione NATURAL JOIN assegnazione NATURAL JOIN mandato "
				+ "WHERE (UPPER(regione.nome_regione) = UPPER(?)) "
				+ "AND assegnazione.inizio <= DATE(NOW()) AND assegnazione.fine > DATE(NOW());";
				
		ps = conn.prepareStatement(qry);
		ps.setString(1, region);
		rs = ps.executeQuery();
		return rs;
	}
	
	// metodo per ricavare le note aggiuntive ad un'assegnazione
	public ResultSet note_sql(Connection conn, String id_assegnazione) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String qry = "SELECT * FROM nota_aggiuntiva WHERE id_assegnazione = (?);";
		ps = conn.prepareStatement(qry);
		ps.setInt(1, Integer.parseInt(id_assegnazione));
		rs = ps.executeQuery();
		return rs;
	}
}
