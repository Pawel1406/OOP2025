import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class City {
    private String name,N_S,W_E; // szerokosc, dlugosc
    private int strefa;

    private static City parseLine(String line){
        String temp[]=line.split(",");
        City lokalizacja = new City();
        lokalizacja.name = temp[0];
        lokalizacja.strefa = Integer.parseInt(temp[1]);
        lokalizacja.N_S = temp[2];
        lokalizacja.W_E = temp[3];
        return lokalizacja;
    }


    public String getName() {
        return name;
    }

    public String getN_S() {
        return N_S;
    }

    public String getW_E() {
        return W_E;
    }

    public int getStrefa() {
        return strefa;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\''+'\n' +
                ", N_S='" + N_S + '\''+'\n' +
                ", W_E='" + W_E + '\''+'\n' +
                ", strefa=" + strefa +
                '}'+'\n';
    }

    public static Map<String, City> parseFile(String filePath) throws FileNotFoundException {
        Map<String, City> mapa= new TreeMap<>();
        Scanner odczyt = new Scanner(new File(filePath));
        odczyt.nextLine();
        while(odczyt.hasNext()){
            String linia = odczyt.nextLine();
            City temp = parseLine(linia);
            mapa.put(temp.getName(),temp);
        }

        odczyt.close();
        return mapa;
    }

    public static int worstTimezoneFit(Clock clock1, Clock clock2){
        Clock temp,temp1;//obiekt typu clock, który przechowuje godzinę zgodną z położeniem geograficznym miejsca
        temp=clock1.localmeantime(clock1.getHh(), clock1.getMm(),clock1.getSs());
        temp1=clock2.localmeantime( clock2.getHh(),clock2.getMm(),clock2.getSs());
        int temp2,temp3;//różnica miedzy czasem zgodnym ze strefą a zgodnym z położeniem w sekundach
        temp2=Math.abs(clock1.getHh()*3600+clock1.getMm()*60+clock1.getSs()-(temp.getHh()*3600+temp.getMm()*60+temp.getSs()));
        temp3=Math.abs(clock2.getHh()*3600+clock2.getMm()*60+clock2.getSs()-(temp1.getHh()*3600+temp1.getMm()*60+temp1.getSs()));
        if(temp2<temp3) return -1;
        if(temp2>temp3) return 1;
        return 0;
    }

}
