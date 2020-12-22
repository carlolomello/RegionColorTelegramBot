package TelegramBot.RegionColor;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class Main 
{
	public static void main(String[] args) {
		//inizializzazione del bot
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new RegionColorBot());
        } catch (TelegramApiRequestException e) {
        	// gestione errore in registrazione
        }
    }
}
