package TelegramBot.RegionColor;

// classe necessaria per contenere le informazioni della regione 
// con i relativi metodi get e set per leggere o modificarne i valori

public class RegionInfo {
	
	private String nomeRegione;
	private String colore;
	private String descrizioneMandato;
	private String dataInizio;
	private String dataFine;
	
	public String getNomeRegione() {
		return nomeRegione;
	}
	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getDescrizioneMandato() {
		return descrizioneMandato;
	}
	public void setDescrizioneMandato(String descrizioneMandato) {
		this.descrizioneMandato = descrizioneMandato;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	
}
