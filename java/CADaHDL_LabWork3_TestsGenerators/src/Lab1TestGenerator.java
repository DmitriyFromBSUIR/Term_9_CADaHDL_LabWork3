
/**
 * Created by Dmitry on 27.10.2016.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab1TestGenerator {
    private String _filepath = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\CADaHDL_LabWork3_TestsGenerators\\files\\Lab1_tests";
    private String _filepathToLab1OutputSignals = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\CADaHDL_LabWork3_TestsGenerators\\files\\Lab1_outs";
    private int _numberDigits = 7;
    private int _binNumberInDecimalView = 128;

    public void inputSignalsGenerate() throws Exception, FileNotFoundException, IOException, RuntimeException{
        FileWriter filewriter = new FileWriter(new File(_filepath));
        for(int i=0; i < _binNumberInDecimalView; i++){
            String curBinNum = Integer.toBinaryString(i);
            String resultBinNum = "";
            int binNumDigitsCount = curBinNum.length();
            if(binNumDigitsCount < _numberDigits){
                for(int j=0; j < _numberDigits - binNumDigitsCount; j++) {
                    resultBinNum += "0";
                }
                resultBinNum += curBinNum;
                filewriter.write(resultBinNum + "\n");
            }
            else{
                filewriter.write(curBinNum + "\n");
            }
            //System.out.println(curBinNum.length());

        }
        filewriter.flush();
        filewriter.close();
    }

    boolean[] convertStrToBooleanArr(String input){
        int digits = input.length();
        boolean [] inputBinary = new boolean [digits];
        for(int i=0; i<digits; i++){
            if(input.charAt(i) == '1'){
                inputBinary[i] = true;
            }
            else{
                inputBinary[i] = false;
            }
        }
        return inputBinary;
    }

    String convertBooleanArrToStr(boolean[] input){
        int digits = input.length;
        String outputSignals = "";
        for(int i=0; i<digits; i++){
            if(input[i] == true){
                outputSignals += "1";
            }
            else{
                outputSignals += "0";
            }
        }
        return outputSignals;
    }

    public String generateOutputSignals(String curInputSignals){
        boolean bi_rbo, notLT, notRBI, a, b, c, d, A_out, B_out, C_out, D_out, E_out, F_out, G_out;
        boolean [] inputInBinary = convertStrToBooleanArr(curInputSignals);
        bi_rbo = inputInBinary[0];
        notLT = inputInBinary[1];
        notRBI = inputInBinary[2];
        a = inputInBinary[3];
        b  = inputInBinary[4];
        c = inputInBinary[5];
        d  = inputInBinary[6];
        A_out = (bi_rbo && (! a) && (! b) && (! c) && (! d) && !notLT && notRBI) ||
                (bi_rbo && (! a) && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && (!b) && c && (!d) && notLT) ||
                (bi_rbo && a && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && c && d && notLT) ||
                (bi_rbo && (!notLT));
        B_out = (bi_rbo && (!a) && (!b) && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && a && (!b) && (!c) && (!d) && notLT) ||
                (bi_rbo && (!a) && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && b && (!c) && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && c && (!d) && notLT) ||
                (bi_rbo && a && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && (!c) && d && notLT) ||
                (bi_rbo && c && d && notLT) ||
                (bi_rbo && (!notLT));
        C_out = (bi_rbo && (!a) && (!b) && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && a && (!b) && (!c) && (!d) && notLT) ||
                (bi_rbo && a && b && (!c) && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && c && (!d) && notLT) ||
                (bi_rbo && a && (!b) && c && (!d) && notLT) ||
                (bi_rbo && (!a) && b && c && (!d) && notLT) ||
                (bi_rbo && a && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && b && (!c) && d && notLT) ||
                (bi_rbo && (!notLT));
        D_out = (bi_rbo && (!a) && (!b) && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && (!a) && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && (!b) && c && (!d) && notLT) ||
                (bi_rbo && (!a) && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && (!a) && b && (!c) && d && notLT) ||
                (bi_rbo && a && b && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && c && d && notLT) ||
                (bi_rbo && (!a) && b && c && d && notLT) ||
                (bi_rbo && (!notLT));
        E_out = (bi_rbo && (!a) && (!b) && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && (!a) && b && (!c) && (!d) && notLT) ||
                (bi_rbo && (!a) && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && (!a) && b && (!c) && d && notLT) ||
                (bi_rbo && (!a) && b && c && d && notLT) ||
                (bi_rbo && (!notLT));
        F_out = (bi_rbo && (!a) && (!b) && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && (!a) && (!b) && c && (!d) && notLT) ||
                (bi_rbo && a && (!b) && c && (!d) && notLT) ||
                (bi_rbo && (!a) && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && (!a) && (!b) && c && d && notLT) ||
                (bi_rbo && a && (!b) && c && d && notLT) ||
                (bi_rbo && (!a) && b && c && d && notLT) ||
                (bi_rbo && a && b && c && d && notLT) ||
                (bi_rbo && (!notLT));
        G_out = (bi_rbo && (!a) && b && (!c) && (!d) && notLT) ||
                (bi_rbo && a && b && (!c) && (!d) && notLT && notRBI) ||
                (bi_rbo && (!a) && (!b) && c && (!d) && notLT && notRBI) ||
                (bi_rbo && a && (!b) && c && (!d) && notLT) ||
                (bi_rbo && (!a) && b && c && (!d) && notLT) ||
                (bi_rbo && (!a) && (!b) && (!c) && d && notLT) ||
                (bi_rbo && a && (!b) && (!c) && d && notLT) ||
                (bi_rbo && (!a) && b && (!c) && d && notLT) ||
                (bi_rbo && a && b && (!c) && d && notLT) ||
                (bi_rbo && (!a) && (!b) && c && d && notLT) ||
                (bi_rbo && a && (!b) && c && d && notLT) ||
                (bi_rbo && (!a) && b && c && d && notLT) ||
                (bi_rbo && (!notLT));

        int digits = inputInBinary.length;
        boolean [] outputSignals = new boolean[digits];
        outputSignals[0] = A_out;
        outputSignals[1] = B_out;
        outputSignals[2] = C_out;
        outputSignals[3] = D_out;
        outputSignals[4] = E_out;
        outputSignals[5] = F_out;
        outputSignals[6] = G_out;
        String outputSignalsInStr = convertBooleanArrToStr(outputSignals);
        return outputSignalsInStr;
    }

    public void outputSignalsGenerate() throws Exception, FileNotFoundException, IOException, RuntimeException{
        File file = new File(_filepath);
        Scanner scannerfile = new Scanner(file);
        FileWriter filewriter = new FileWriter(new File(_filepathToLab1OutputSignals));
        String curSignals, outputSignals;
        for(int i=0; i < _binNumberInDecimalView; i++){
            curSignals = scannerfile.next();
            //System.out.println("Signal from File: " + curSignals);
            outputSignals = generateOutputSignals(curSignals);
            filewriter.write(outputSignals);
            filewriter.write("\n");
        }
        scannerfile.close();
        filewriter.flush();
        filewriter.close();
    }

    public void run() throws Exception{
        //inputSignalsGenerate();
        outputSignalsGenerate();
    }
}
