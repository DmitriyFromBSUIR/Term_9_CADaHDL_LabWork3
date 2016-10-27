
/**
 * Created by Dmitry on 27.10.2016.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab2TestGenerator {
    private String _filepath = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\CADaHDL_Lab1TestGenerator\\files\\Lab2_tests";
    private String _filepathToLab1OutputSignals = "D:\\Projects\\JetBrains\\IntelliJ_IDEA_Workspace\\Term_9\\CADaHDL_LabWork3_TestsGenerators\\files\\Lab2_outs";
    private int _numberDigits = 12;
    private int _binNumberInDecimalView = 4096;
    public void inputSignalsGenerate() throws Exception{
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
        boolean notOE, ENT, ENP, notSCLR, notSLOAD, clk, notACLR, notALOAD, a, b, c, d, CCO, RCO, Qa, Qb, Qc, Qd;
        boolean [] inputInBinary = convertStrToBooleanArr(curInputSignals);
        notOE = inputInBinary[0];
        ENT = inputInBinary[1];
        ENP = inputInBinary[2];
        notSCLR = inputInBinary[3];
        notSLOAD  = inputInBinary[4];
        clk = inputInBinary[5];
        notACLR  = inputInBinary[6];
        notALOAD = inputInBinary[7];
        a = inputInBinary[8];
        b = inputInBinary[9];
        c = inputInBinary[10];
        d  = inputInBinary[11];

        //

        int digits = inputInBinary.length;
        boolean [] outputSignals = new boolean[digits];
        outputSignals[0] = CCO;
        outputSignals[1] = RCO;
        outputSignals[2] = Qa;
        outputSignals[3] = Qb;
        outputSignals[4] = Qc;
        outputSignals[5] = Qd;
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
