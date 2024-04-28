import java.util.ArrayList;
import java.util.*;

public class DzialPracownikow {
    private String nazwa;
    public static List<DzialPracownikow> nazwaDzialow = new ArrayList<>();
    private int uniqueIQ;
    public static int counter = 1;

    DzialPracownikow(String nazwa){
        this.nazwa=nazwa;
        this.uniqueIQ=counter;
        counter++;
    }

    public String getNazwa(){
        return this.nazwa;
    }
    public static DzialPracownikow createDzial(String nazwa){
        //the for loop searches for the same dzial in the static list
        //if the same dzial exists, then createDzial throws an exception
        DzialPracownikow.nazwaDzialow.forEach(dzial -> {
            if(dzial.getNazwa().equals(nazwa)){
                try {
                    throw new NotUniqueNameException("Dzial juz istnieje");
                    //lambdas do not allow directly throwing checked exceptions
                    //because lambdas dont allow checked exprs to be thrown without handling
                } catch (NotUniqueNameException e) {
                    throw new RuntimeException(e);
                    //how to go around the aforemementioned problem?
                    //1)throw the checked exception inside and rethrow it as an unchecked
                    //2)exception which doesnt have to be handled explicitly
                }
            }
                });
        //if the exception is not thrown, a new dzial is created, added to the static list and returned
        DzialPracownikow nowyDzial = new DzialPracownikow(nazwa);
        nazwaDzialow.add(nowyDzial);
        return nowyDzial;
    }

    public static void pracownicyDzialu(DzialPracownikow dzial){
        //filter out any Pracownik objects that do have matching dzial name as the given param
        //iterate ouver each Pracownik passed through the filter and print it to the std output
        Pracownik.listaPracownikow.stream()
                .filter(pracownik -> pracownik.getDzialPracownika().equals(dzial.getNazwa()))
                .forEach(System.out::println);

    }
    @Override
    public String toString(){
        return "Nazwa dzialu: " + this.nazwa + ", ID: " + this.uniqueIQ;
    }

}
