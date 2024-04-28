import java.util.ArrayList;
import java.util.*;

public class Brygada {
    public static int counter = 1;
    public int uniqueIDBrygada;
    private String nazwaBrygady;
    private Brygadzista liderBrygady;
    List<Brygadzista> listaBrygadzistow;


    public Brygada(String nazwaBrygady){
        this.nazwaBrygady=nazwaBrygady;
        this.uniqueIDBrygada=counter++;
        this.listaBrygadzistow= new ArrayList<>();
    }
    public Brygada(String nazwaBrygady,Brygadzista b){
        this.nazwaBrygady=nazwaBrygady;
        this.uniqueIDBrygada=counter++;
        this.listaBrygadzistow= new ArrayList<>();
        this.liderBrygady = b;
        //brygada dodana do brygad w ktorych uczestniczy lider
        b.addBrygada(this);

    }

    public String getNazwaBrygady(){
        return nazwaBrygady;
    }
    public Brygadzista getLiderBrygady(){
        return liderBrygady;
    }
    public void setLiderBrygady(Brygadzista b){
        this.liderBrygady = b;
        if(!b.listaBrygad.contains(this)){
            b.addBrygada(this);
        }
    }

    public void dodajPracownikaBrygady(Brygadzista b){
        //a brygadzista cannot be added if he exists in the list
        if(!this.listaBrygadzistow.contains(b) && !this.liderBrygady.equals(b)){
            this.listaBrygadzistow.add(b);
            //make sure the employee class also adds the brigade
            // to the list storing employees past and current brigades
            b.addBrygada(this);
        }else{
            throw new IllegalArgumentException("Brygadzista juz jest w brygadzie");
        }
    }
    public void dodajPracownikaBrygady(List<Brygadzista> b){

        for(Brygadzista brygadzista : b){
            if(!this.listaBrygadzistow.contains(brygadzista) && !this.liderBrygady.equals(brygadzista)){
                this.listaBrygadzistow.add(brygadzista);
                brygadzista.addBrygada(this);
            }
        }
    }

    public void getCzlonkowieBrygady(){
        this.listaBrygadzistow.forEach(brygadzista -> {
            System.out.print(brygadzista.getImie() + " " + brygadzista.getNazwisko()+ " ") ;
        });
        System.out.println();
    }
    public String toString(){
        return "Nazwa: " + this.nazwaBrygady + ", lider: " +this.getLiderBrygady().getImie() + " " + this.liderBrygady.getNazwisko()+
                ", UniqueIDBrygada: " +this.uniqueIDBrygada ;
    }



}
