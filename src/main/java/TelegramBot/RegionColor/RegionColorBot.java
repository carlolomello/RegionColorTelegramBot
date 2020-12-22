package TelegramBot.RegionColor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

// estendiamo Region con TelegramLongPollingBot importate con le dipendenze tramite pom.xml
public class RegionColorBot extends TelegramLongPollingBot {

	// questo metodo viene richiamato quando il bot riceve un aggiornamento
	public void onUpdateReceived(Update update) {
		// se riceviamo un messaggio di testo entriamo in questo blocco di istruzioni
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			// prendiamo il messaggio in input
			String input_msg = update.getMessage().getText();
			// prendiamo l'id della chat
			String chatId = update.getMessage().getChatId().toString();
			
			// istanziamo gli oggetti necessari
			SendMessage sendMessage = new SendMessage();	 
			RegionInfo regionInfo = new RegionInfo();			
			DBoperations dboperations = new DBoperations();
			
			// istanziamo le variabili che andremo ad utilizzare
			String id_assegnazione = null;
			String output_msg = "";
	
			// se la connessione al db va a buon fine entriamo in questo blocco
			try (Connection conn = DBconnection.createNewDBconnection()) {
				
				// tramite il metodo info_sql andiamo a scaricare le informazioni dal db
				ResultSet results = dboperations.info_sql(conn, input_msg);
				// tramite i metodi set, assegnamo i valori all'istanza della classe
				while (results.next()) {
					regionInfo.setNomeRegione(results.getString("nome_regione"));
					regionInfo.setColore(results.getString("colore"));
					regionInfo.setDataInizio(results.getString("inizio"));
					regionInfo.setDataFine(results.getString("fine"));
					regionInfo.setDescrizioneMandato(results.getString("descrizione"));
					id_assegnazione = results.getString("id_assegnazione"); 					
				}
				
				// se abbimamo un riscontro, entriamo in questo blocco
				if(id_assegnazione != null) {
					// personaliziamo il messaggio di risposta con i valori assegnati all'oggetto regionInfo
					output_msg = "regione: " + regionInfo.getNomeRegione() + "\n" 
							+ "colore: " + regionInfo.getColore() + "\n"
							+ "inizio: " + regionInfo.getDataInizio() + " - "
							+ "fine: " + regionInfo.getDataInizio() + "\n"
							+ "descrizione: " + regionInfo.getDescrizioneMandato() + "\n";
					
					// tramite il metodo note_sql, andiamo a scaricare eventuali informazioni aggiuntive
					ResultSet results2 = dboperations.note_sql(conn, id_assegnazione);
					// se sono presenti note entriamo in questo blocco di istruzioni 
					// e le aggiungiamo al messaggio finale
					if (results2 != null) {
						int i = 1;
						while (results2.next()) {
							output_msg = output_msg + "nota " +i+": "
									+ results2.getString("nota") + "\n";
							i++;
						}
					}
					
				} else if (input_msg.equals("/start")){
					output_msg = "Scrivi il nome della regione interessata";
				} else {
					output_msg = "nessuna informazione disponibile! \nScrivi il nome della regione interessata";
				}
					
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			//send message telegram 
			sendMessage.setChatId(chatId);
			sendMessage.setText(output_msg);
			try {
				execute(sendMessage);
			} catch (TelegramApiException e) {
				// gestione errore in invio
			}
		}

	}

	public String getBotUsername() {
		return "infoRegioneBot";
	}

	@Override
	public String getBotToken() {
		// inserire qui il proprio token
		return "yourTelegramBotToken <!-- ex 1499333443434:ABCuvS6E6qUsdfgeqGxDCkxxxtACxxxx2g -->";
	}

}