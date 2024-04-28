import java.util.ArrayList;
import java.util.Iterator;

public class Brygadzista extends Uzytkownik{

    public static int counter=1;
    public int uniqueIdBryg;
    public ArrayList<Brygada> listaBrygad;

    public Brygadzista(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia,String login, String haslo){
        super(imie, nazwisko, dzialPracownika, dataUrodzenia, login, haslo);
        this.uniqueIdBryg=counter++;
        this.listaBrygad=new ArrayList<>();
    }
    public String toString(){
        return "Imie i nazwisko: " + this.imie + " " + this.nazwisko + ", dzial: " + this.dzialPracownika
                + ", data urodzenia: " + this.getDataUrodzeniaString() + ", uniqueID: " + this.uniqueID
                + ", uniqueIDUzyt: " + this.uniqueIDUzyt  + ", login: " + this.login + ", UniqueIDBryg: " + this.uniqueIdBryg
                ;
    }

    public static void printListaBrygad(Brygadzista b){
        //an iterator used to easily go through the collection
        Iterator<Brygada> it = b.listaBrygad.iterator();
        while(it.hasNext()){
            String nazwaBrygady ="";
            //only need to print out the name of the brygada, not the entire object
            nazwaBrygady=it.next().getNazwaBrygady();
            System.out.print(nazwaBrygady+ " ");
        }
        System.out.println();
    }

    public void addBrygada(Brygada b){
        this.listaBrygad.add(b);
    }



}
