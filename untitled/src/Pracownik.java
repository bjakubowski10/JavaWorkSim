import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik implements Comparable<Pracownik> {
    public static List<Pracownik> listaPracownikow = new ArrayList<>();
    protected String imie,nazwisko,dzialPracownika,dataUrodzeniaString;
    private LocalDateTime dataUrodzenia;
    public int uniqueID;
    public static int counter = 1;

    public  Pracownik(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.dzialPracownika=dzialPracownika;
        this.uniqueID=counter;
        counter++;
        //the formatter creates a DateTime format to be used for LocalDateTime objects
        //the parse method parses through the text and compares it to the given format pattern
        //then assigns the date to a variable
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        this.dataUrodzenia = LocalDateTime.parse(dataUrodzenia + " 00:00:00",formatter);
        this.dataUrodzeniaString = dataUrodzenia;

    }
    public String getImie(){
        return  imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }
    public String getDataUrodzeniaString(){
        return dataUrodzeniaString;
    }
    public  LocalDateTime getDataUrodzenia(){

        return  dataUrodzenia;
    }
    public String getDzialPracownika(){
        return  dzialPracownika;
    }

    @Override
    public int compareTo(Pracownik p){
        //order of comparison: name,lastname, and lastly birthdate
        int compareName = this.imie.compareTo(p.imie);
        int compareSurname = this.nazwisko.compareTo(p.nazwisko);
        return compareName == 0 ?
                (compareSurname == 0 ? this.dataUrodzenia.compareTo(p.dataUrodzenia) : compareSurname )
                : compareName;

    }

    @Override
    public String toString(){
        return "Imie i nazwisko: " + this.imie + " " + this.nazwisko + ", dzial: " + this.dzialPracownika
                + ", data urodzenia: " + this.getDataUrodzenia() + ", uniqueID: " + this.uniqueID;
    }


}
