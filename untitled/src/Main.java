import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){
        System.out.println("Dodawania dzialu");

        DzialPracownikow dzial1 = DzialPracownikow.createDzial("Wnetrza");
        //rzuci blad
        //DzialPracownikow dzial2 = DzialPracownikow.createDzial("IT"); //ten kod nie zadiala
        DzialPracownikow dzial3 = DzialPracownikow.createDzial("Finanse");
        DzialPracownikow dzial4 = DzialPracownikow.createDzial("Ogrody");


        DzialPracownikow.nazwaDzialow.forEach(dzial -> System.out.println(dzial));

        System.out.println("\nTest klasy specjalista, klasy uzytkownik, i  listy pracownikow");
        Specjalista spec1 = Specjalista.createSpecjalista("Bart","Hmam","Wnetrza","06/01/1996","Lazienki");
        Specjalista spec2 = Specjalista.createSpecjalista("Julia","Zugaj","Wnetrza","02/05/2000","Kuchnie");
        Specjalista spec3 = Specjalista.createSpecjalista("Jan","Kowalski","Ogrody","12/12/2001","Drzewka");
        Specjalista spec4 = Specjalista.createSpecjalista("Henryk","Polonowki","Finanse","10/15/1978","Tax Evasion");
        Specjalista spec5 = Specjalista.createSpecjalista("Tadeusz","Lubella","Finanse","09/12/1984","Embezzling funds");
        //rzuci blad
        //Specjalista spec6 = Specjalista.createSpecjalista("Charles","Actimel","niewiem","11/23/1996","Nic");
        Uzytkownik uzyt1 = Uzytkownik.createUzytkownik("Harry","Potter","Ogrody","12/12/1979","harpar1","voldemort");
        Uzytkownik uzyt2 = Uzytkownik.createUzytkownik("Volde","Mort","Wnetrza","12/01/1965","volgod","voldemort");
        Uzytkownik uzyt3 = Uzytkownik.createUzytkownik("Luke","Skywalker","Wnetrza","11/10/1945","lukeS","obi");
        Uzytkownik uzyt4 = Uzytkownik.createUzytkownik("Henry","Skywalker","Ogrody","11/10/1948","HWEE","obiaw");
        //rzuci blad
        //Uzytkownik uzyt5 = Uzytkownik.createUzytkownik("Obi","Kenobi","Niewiem","19/11/1922","obiwan","wan");

        //Pracownik.listaPracownikow.forEach(pracownik -> System.out.println(pracownik));
        Collections.sort(Pracownik.listaPracownikow);
        Pracownik.listaPracownikow.forEach(pracownik -> System.out.println(pracownik));



        System.out.println("\nTest funkcji pracownicy dzialu w klasie DzialPracownikow");
        DzialPracownikow.pracownicyDzialu(dzial1);


        System.out.println("\nTest klas brygada i brygadzista");
        Brygadzista bryg1 = new Brygadzista(uzyt1.imie,uzyt1.nazwisko,
                uzyt1.dzialPracownika,uzyt1.dataUrodzeniaString,uzyt1.login,uzyt1.haslo);
        Brygadzista bryg2 = new Brygadzista(uzyt2.imie,uzyt2.nazwisko,
                uzyt2.dzialPracownika,uzyt2.dataUrodzeniaString,uzyt2.login,uzyt2.haslo);
        Brygadzista bryg3 = new Brygadzista(uzyt3.imie,uzyt3.nazwisko,
                uzyt3.dzialPracownika,uzyt3.dataUrodzeniaString,uzyt3.login,uzyt3.haslo);
        Brygadzista bryg4 = new Brygadzista(uzyt4.imie,uzyt4.nazwisko,
                uzyt4.dzialPracownika,uzyt4.dataUrodzeniaString,uzyt4.login,uzyt4.haslo);

        //System.out.println(bryg3);

        Brygada brygada1 = new Brygada("Polska");
        Brygada brygada2 = new Brygada("Zagraniczna",bryg3);

        brygada1.setLiderBrygady(bryg2);

        brygada1.dodajPracownikaBrygady(bryg4);
        //brygada1.dodajPracownikaBrygady(bryg4); rzuci blad
        System.out.print("Czlonkowie brygada1: ");
        brygada1.getCzlonkowieBrygady();
        System.out.println("info brygada1: " + brygada1);
        System.out.println();
        ArrayList<Brygadzista> test = new ArrayList<>();
        test.add(bryg3);
        test.add(bryg4);
        test.add(bryg1);
        test.add(bryg1);
        brygada2.dodajPracownikaBrygady(test);
        System.out.println("lider brygada2: " +brygada2.getLiderBrygady());
        System.out.print("czlonkowie brygada2: ");
        brygada2.getCzlonkowieBrygady();

        System.out.print("Lista brygad bryg4: ");
        Brygadzista.printListaBrygad(bryg4);


        //Test praca i zlecenie

        Praca p = new Praca(RodzajPracy.MONTAZ,3,"montaz zyrandola");
        Praca p1 = new Praca(RodzajPracy.DEMONTAZ,3,"demontaz zyrandola");
        Praca p2 = new Praca(RodzajPracy.OGOLNA,3,"budowa");
        Praca p3 = new Praca(RodzajPracy.OGOLNA,3,"demolka");
        Praca p4 = new Praca(RodzajPracy.WYMIANA,3,"nowe drzewka");
        Praca p5 = new Praca(RodzajPracy.OGOLNA,3,"budowa");
        //System.out.println(p5.equals(p2));
        ArrayList<Praca> pracaArray = new ArrayList<>();
        pracaArray.add(p1);
        pracaArray.add(p2);
        pracaArray.add(p3);
        Zlecenie z = new Zlecenie(true);
        Zlecenie z2 = new Zlecenie(true,pracaArray,brygada1);
        z.addPraca(p4);
        z.addPraca(p5);
        z.addPraca(p5);
        //z.getKolekcjaPrac().forEach(System.out::println); //test czy add praca kasuje kopie
        System.out.println("\nstart zadan");
        Thread myThread = new Thread(z2);
        myThread.start();
        System.out.println("STatus zlecenia z2: " + z2.getstatusZlecenia());

        try{
            Thread.sleep(1000);
            System.out.println("STatus zlecenia z2: " +z2.getstatusZlecenia());
            myThread.join();
        //Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("data utworzenia: " +z2.getDataUtworzenia());
        System.out.println("data zakonczenia z2: "+z2.getDataZakonczenia());
        System.out.println("STatus zlecenia z2: "+z2.getstatusZlecenia());

        System.out.println("\n\nTest zlecenia z");
        Thread myThread2 = new Thread(z);
        //myThread2.start(); rzuci illegla argument exception;
        //z.getKolekcjaPrac().forEach(System.out::println);
        z.addBrygada(brygada2);
        System.out.println(z.getKolekcjaPrac().get(1).getczyZrealizowane());//sprawdzam status realizacji pierwszej pracy
        myThread2.start();
        try{
            Thread.sleep(1000);
            System.out.println("STatus zlecenia z: " +z.getstatusZlecenia());
            myThread2.join();
            //Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("utworzenie z: " + z.getDataUtworzenia());
        System.out.println("zamkniecie z: " + z.getDataZakonczenia());
        System.out.println("rodzaj: " + z.getrodzajZlecenia());
        System.out.println("Status: " + z.getstatusZlecenia());

        System.out.println("\nTest statycznej funkcji ktora zwraca zlecenie/praca (map)");
        System.out.println("Zlecenie: "+Zlecenie.getZlecenie(1));
        //System.out.println("\nZlecenie: "+Zlecenie.getZlecenie(3));
        System.out.println("Praca: " + Praca.getPraca(2));
        //System.out.println("Praca: " + Praca.getPraca(9));

        System.out.println("\nTest liczby zlecen brygadzisty");
        System.out.println("zlecenia bryg4");
        bryg4.getListaZlecen().forEach(System.out::println);

    }
}

//cos zrobvic z kolekcja prac w praca




