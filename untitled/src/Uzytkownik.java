public class Uzytkownik extends  Pracownik {
    protected String login,haslo;
    public static int counter=1;
    public int uniqueIDUzyt;
    protected String initial;

    public  Uzytkownik(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia,String login, String haslo){
        super(imie,nazwisko,dzialPracownika,dataUrodzenia);
        this.login=login;
        this.haslo=haslo;
        this.uniqueIDUzyt=counter;
        counter++;
    }
    public static Uzytkownik createUzytkownik(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia,String login, String haslo){
        //Before a new uzytkownik is created. the foreach looks searches for the dzial
        //the uzytkownik is part of. if the dzial does not exist an exception is thrown
        for(DzialPracownikow dzial : DzialPracownikow.nazwaDzialow){
            if(dzial.getNazwa().equals(dzialPracownika)){
                Uzytkownik uzytkownik = new Uzytkownik(imie,nazwisko,dzialPracownika,dataUrodzenia,login,haslo);
                Pracownik.listaPracownikow.add(uzytkownik);
                uzytkownik.initial = uzytkownik.imie.charAt(0) +""+ uzytkownik.nazwisko.charAt(0);
                return uzytkownik;
            }
        }
        throw new RuntimeException("Nie mozna dodac uzytkownik do dzialu ktory nie istnieje");
    }

    public void changeImie(String imie){
        this.imie=imie;
        this.initial = this.imie.charAt(0)+""+this.nazwisko.charAt(0);
    }
    public void changeNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
        this.initial = this.imie.charAt(0)+""+this.nazwisko.charAt(0);
    }

    public String getLogin(){
        return login;
    }
    public String getHaslo(){
        return haslo;
    }
    @Override
    public String toString(){
        return "Imie i nazwisko: " + this.imie + " " + this.nazwisko + ", dzial: " + this.dzialPracownika
                + ", data urodzenia: " + this.getDataUrodzenia() + ", uniqueID: " + this.uniqueID
                + ", uniqueIDUzyt: " + this.uniqueIDUzyt  + ", login: " + this.login + " initial:"+this.initial;
    }



}
