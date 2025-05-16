import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        Map<String, City> mapa= new HashMap<>();
        Scanner odczyt = new Scanner(new File(filePath));
        odczyt.nextLine();
        while(odczyt.hasNext()){
            String linia = odczyt.nextLine();
            City temp = parseLine(linia);
            mapa.put(temp.getName(),temp);
        }
        Map<String,City> mapasortowana=new TreeMap<>();
        mapasortowana.entrySet().stream()
                        .sorted((a,b)-> worstTimezoneFit(a.getValue(),b.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e1, TreeMap::new));
        odczyt.close();
        return mapasortowana;
    }

    public static int worstTimezoneFit(Object clock10, Object clock20) {
        Clock temp,temp1;
        AnalogClock clock1= new AnalogClock(clock10);
        AnalogClock clock2= new AnalogClock(clock20);
        temp=clock1.localmeantime(clock1.getHh(), clock1.getMm(),clock1.getSs());
        temp1=clock2.localmeantime( clock2.getHh(),clock2.getMm(),clock2.getSs());
        int temp2,temp3;
        temp2=Math.abs(clock1.getHh()*3600+clock1.getMm()*60+clock1.getSs()-(temp.getHh()*3600+temp.getMm()*60+temp.getSs()));
        temp3=Math.abs(clock2.getHh()*3600+clock2.getMm()*60+clock2.getSs()-(temp1.getHh()*3600+temp1.getMm()*60+temp1.getSs()));
       return Integer.compare(temp2,temp3);
    }
    public static void generateAnalogClockSvg(List<City> lista, AnalogClock zegar) throws IOException {
        if(!Paths.get(zegar.toString()).toFile().exists()){
        Files.createDirectory(Paths.get(zegar.toString()));
        }
        String path = zegar.toString();
        for (int i = 0; i < lista.size(); i++)
        {

            zegar.setTimeSvg(Integer.parseInt(String.valueOf(zegar.getHh()+lista.get(i).getStrefa())), zegar.getMm(), zegar.getSs(), zegar.getCity());
            zegar.toSvg(path+"/"+lista.get(i).getName()+".svg");
        }
    }

}
