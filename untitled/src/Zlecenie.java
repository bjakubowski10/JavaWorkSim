import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Zlecenie implements Runnable{

    private static HashMap<Integer,Zlecenie> obiektZlecenia = new HashMap<>();
    private Brygada brygada;
    private List<Praca> kolekcjaPrac;//no duplicate tasks
    private LocalDateTime dataUtworzenia=null; //will be set once the first task will begin
    private LocalDateTime dataZakonczenia=null; //will be set after the completionn of all tasks
    private final RodzajZlecenia rodzajZlecenia;
    public static int counter = 1;
    public int uniqueIdZlecenie;

    public Zlecenie(boolean czyZaplanowane){
        this.uniqueIdZlecenie=counter++;
        if(czyZaplanowane == true)
            this.rodzajZlecenia = RodzajZlecenia.PLANOWANE;
        else
            this.rodzajZlecenia = RodzajZlecenia.NIEPLANOWANE;
        this.kolekcjaPrac = new ArrayList<>();
        obiektZlecenia.put(uniqueIdZlecenie,this);


    }
    public Zlecenie(boolean czyZaplanowane,Brygada brygada){
        this.uniqueIdZlecenie=counter++;
        if(czyZaplanowane == true){
            this.rodzajZlecenia = RodzajZlecenia.PLANOWANE;
        }
        else{
            this.rodzajZlecenia = RodzajZlecenia.NIEPLANOWANE;
        }
        this.brygada=brygada;
        this.kolekcjaPrac = new ArrayList<>();
        obiektZlecenia.put(uniqueIdZlecenie,this);
        for(Brygadzista b: brygada.getListaBrygadzistow()){
            b.getListaZlecen().add(this);
        }
        brygada.getLiderBrygady().getListaZlecen().add(this);




    }
    public Zlecenie(boolean czyZaplanowane,ArrayList<Praca> kolekcjaPrac){
        this.uniqueIdZlecenie=counter++;
        if(czyZaplanowane == true){
            this.rodzajZlecenia = RodzajZlecenia.PLANOWANE;
        }
        else{
            this.rodzajZlecenia = RodzajZlecenia.NIEPLANOWANE;
        }
        this.brygada = null;
        this.kolekcjaPrac = kolekcjaPrac;
        obiektZlecenia.put(uniqueIdZlecenie,this);






    }
    public  Zlecenie(boolean czyZaplanowane, ArrayList<Praca> kolekcjaPrac,Brygada brygada){
        this.uniqueIdZlecenie=counter++;
        if(czyZaplanowane == true){
            this.rodzajZlecenia = RodzajZlecenia.PLANOWANE;
        }
        else{
            this.rodzajZlecenia = RodzajZlecenia.NIEPLANOWANE;
        }
        this.brygada = brygada;
        this.kolekcjaPrac = kolekcjaPrac;
        obiektZlecenia.put(uniqueIdZlecenie,this);
        for(Brygadzista b: brygada.getListaBrygadzistow()){
            b.getListaZlecen().add(this);
        }
        brygada.getLiderBrygady().getListaZlecen().add(this);




    }

    public void addPraca(Praca praca){
        AtomicBoolean pracaIstnieje = new AtomicBoolean(false);
        kolekcjaPrac.forEach(p -> {
            if(p.equals(praca)){
                pracaIstnieje.set(true);//variables used in lambda expressions need to be final
                //atomicBoolean has a function set which overrides the need for that
            }
        });
        if(!pracaIstnieje.get())
            kolekcjaPrac.add(praca); //remember to override the equals in praca
    }
    public boolean addBrygada(Brygada b){
        if (this.brygada == null){
            this.brygada=b;
            for(Brygadzista bb: brygada.getListaBrygadzistow()){
                bb.getListaZlecen().add(this);
            }
            brygada.getLiderBrygady().getListaZlecen().add(this);

            return true;
        }
        return false;
    }


    @Override
    public void run() {
        if(this.brygada != null && !kolekcjaPrac.isEmpty()){
            this.dataUtworzenia = LocalDateTime.now();
            //kolekcjaPrac.forEach(Thread::start); z ta linia koda, main thread czeka na
            //kazdy watek ale wszystkie watki oprocz main wykonuja sie w tym samym czasie

            for(Praca p : kolekcjaPrac){
                try{
                    p.start();//start i join sa wywolywane jeden po drugim powodujac ze kazdy watek zaczyna sie w innym czasie
                    p.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }



            this.dataZakonczenia = LocalDateTime.now();
        }
        else{
            throw new IllegalArgumentException("Brygada i kolekcjaPrac nie moga byc puste");
        }


    }
    public StatusZlecenia getstatusZlecenia(){
        if(this.dataUtworzenia == null){
            return StatusZlecenia.Utworzone;
        }
        else if (this.dataZakonczenia == null){
            return StatusZlecenia.Rozpoczete;
        }
        else{
            return StatusZlecenia.Zakonczone;
        }
    }
    

    public List<Praca> getKolekcjaPrac(){
        return kolekcjaPrac;
    }
    public Brygada getBrygada(){
        return brygada;
    }
    public LocalDateTime getDataUtworzenia(){
        return  dataUtworzenia;
    }
    public LocalDateTime getDataZakonczenia(){
        return dataZakonczenia;
    }
    public RodzajZlecenia getrodzajZlecenia(){
        return rodzajZlecenia;
    }
    public String toString(){
        return "Status: " + this.getstatusZlecenia() +", dataZakonczenia: " + this.getDataZakonczenia() + ", datautworzenia: " + this.getDataUtworzenia()
                + ", rodzaj: " + this.getrodzajZlecenia() + "ID Zlecenia: " + this.uniqueIdZlecenie;
    }
    public static Zlecenie getZlecenie(int i){
        if(obiektZlecenia.containsKey(i))
            return obiektZlecenia.get(i);
        else{
            throw new RuntimeException("Nie ma zlecenia z takim kluczem");
        }
    }
}


