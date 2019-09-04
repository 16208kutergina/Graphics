package ru.nsu.fit.g16202.kutergina.volumeRendering;

import java.io.*;
import java.util.ArrayList;

public class ParseFileGraph{
    public ArrayList<EmissionPoint> getAbsorptionPoints() {
        return absorptionPoints;
    }

    public ArrayList<Emission> getEmissionPoints() {
        return emissionPoints;
    }

    public ArrayList<Charge> getCharges() {
        return charges;
    }

    private ArrayList<EmissionPoint> absorptionPoints = new ArrayList<>();
    private ArrayList<Emission> emissionPoints = new ArrayList<>();
    private ArrayList<Charge> charges = new ArrayList<>();

    public ParseFileGraph(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        readAbsorption(bufferedReader);
        bufferedReader.readLine();
        readEmission(bufferedReader);
        bufferedReader.readLine();
        readCharges(bufferedReader);
    }

    private void readAbsorption(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        String s = getStringWithoutComment(bufferedReader.readLine()).replaceAll("\\s", "");;
        int N = Integer.parseInt(s);
        for(int i = 0; i < N; i++){
            String line = bufferedReader.readLine();
            String data = getStringWithoutComment(line);
            String[] split = data.split(" ");
            int coord = isValidCoord(Integer.parseInt(split[0]));
            int value = Integer.parseInt(split[1]);
            if(value != 0 && value != 1){
                throw new NumberFormatException();
            }
            absorptionPoints.add(new EmissionPoint(coord, value));
        }
    }

    private void readEmission(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        String s = getStringWithoutComment(bufferedReader.readLine()).replaceAll("\\s", "");;
        int N = Integer.parseInt(s);
        for(int i = 0; i < N; i++){
            String line = bufferedReader.readLine();
            String data = getStringWithoutComment(line);
            String[] split = data.split(" ");
            int coord = Integer.parseInt(split[0]);
            int red = isValidColor(Integer.parseInt(split[1]));
            int green = isValidColor(Integer.parseInt(split[2]));
            int blue = isValidColor(Integer.parseInt(split[3]));
            emissionPoints.add(new Emission(coord, red, green, blue));
        }
    }

    private int isValidCoord(int coord){
        if(coord < 0 || coord > 100){
            throw new NumberFormatException();
        }
        return coord;
    }

    private int isValidColor(int color){
        if(color < 0 || color > 255){
            throw new NumberFormatException();
        }
        return color;
    }

    private void readCharges(BufferedReader bufferedReader) throws NumberFormatException, IOException {
        String s = getStringWithoutComment(bufferedReader.readLine()).replaceAll("\\s", "");;
        int N = Integer.parseInt(s);
        for(int i = 0; i < N; i++) {
            String line = bufferedReader.readLine();
            String data = getStringWithoutComment(line);
            String[] split = data.split(" ");
            float x = Float.parseFloat(split[0]);
            float y = Float.parseFloat(split[1]);
            float z = Float.parseFloat(split[2]);
            float power = Float.parseFloat(split[3]);
            charges.add(new Charge(x, y, z, power));
        }
    }


    private String getStringWithoutComment(String s){
        return s.split("//")[0];
    }

}
