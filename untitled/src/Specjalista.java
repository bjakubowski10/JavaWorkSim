public class Specjalista extends Pracownik{

    private String specjalizacja;
    public static int counter = 1;
    public int uniqueIDSpec;

    public Specjalista(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia, String specjalizacja){
        super(imie, nazwisko, dzialPracownika, dataUrodzenia);
        this.specjalizacja=specjalizacja;
        this.uniqueIDSpec = counter;
        counter++;

    }

    public static Specjalista createSpecjalista(String imie, String nazwisko, String dzialPracownika,String dataUrodzenia, String specjalizacja){
        //before a new Specjalista is created, the for each look looks to see if the Dzial the specialist works in even exists
        //if not an exception is thrown
      for (DzialPracownikow dzial : DzialPracownikow.nazwaDzialow){
          if(dzial.getNazwa().equals(dzialPracownika)){
              Specjalista spec = new Specjalista(imie,nazwisko,dzialPracownika,dataUrodzenia,specjalizacja);
              Pracownik.listaPracownikow.add(spec);
              return spec;
          }
      }
      throw new RuntimeException("Nie mozna dodac specjalisty do dzialu ktory nie istnieje");
    }
    public String getSpecjalizacja(){
        return specjalizacja;
    }

    @Override
    public String toString(){
        return "Imie i nazwisko: " + this.imie + " " + this.nazwisko + ", dzial: " + this.dzialPracownika
                + ", data urodzenia: " + this.getDataUrodzenia() + ", uniqueID: " + this.uniqueID
                + ", uniqueIDSpec: " + this.uniqueIDSpec  + ", specjalizacja: " + this.specjalizacja;
    }
}
