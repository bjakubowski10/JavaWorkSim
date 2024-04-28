import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Praca extends Thread{
    private RodzajPracy rodzajPracy;
    private int czasPracy;//sekundy
    private boolean czyZrealizowane = false;
    private String opis;
    private List<Praca> kolekcjaPrac;//klasa czeka az sie wykonaja
    //jak pusta to moze sie wykonac od razu
    public static int counter =1;
    public int uniqueIdPraca;

    public Praca(RodzajPracy rodzajPracy,int czasPracy,String opis){
        this.rodzajPracy = rodzajPracy;
        this.czasPracy=czasPracy;
        this.opis=opis;
        this.uniqueIdPraca=counter++;
        this.kolekcjaPrac=new ArrayList<>();

    }
    public String getOpis(){
        return opis;
    }
    public int getCzasPracy(){
        return czasPracy;
    }
    public RodzajPracy getRodzajPracy(){
        return rodzajPracy;
    }
    public List<Praca> getKolekcjaPrac(){
        return kolekcjaPrac;
    }
    public boolean getczyZrealizowane(){
        return this.czyZrealizowane;
    }

    @Override
    public void run() {
        if(kolekcjaPrac.isEmpty()) {
            try {
                Thread.sleep(this.czasPracy * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.czyZrealizowane = true;
            System.out.println(this.rodzajPracy + ", czas: " + this.czasPracy
                    + ", " + this.opis + ", id: " + this.uniqueIdPraca + ", zrealizowane: " + this.czyZrealizowane);
        }
    }
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Praca obj = (Praca) o;
        return this.rodzajPracy == obj.rodzajPracy &&
                this.czasPracy == obj.czasPracy &&
                this.czyZrealizowane == obj.czyZrealizowane &&
                Objects.equals(this.opis, obj.opis);

    }
    public String toString(){
        return "rodzaj pracy: " + this.getRodzajPracy()+
                ", czas: " + this.czasPracy +
                ", zrealizowane: " + this.czyZrealizowane +
                ", opis: " + this.getOpis();
    }


}
