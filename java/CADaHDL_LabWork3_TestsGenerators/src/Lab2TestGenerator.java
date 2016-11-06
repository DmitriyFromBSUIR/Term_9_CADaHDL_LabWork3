
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
    private int _nBitCounter = 4;
    private String [] _counterMemmory = {"0", "0", "0", "0"};
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

    boolean convertSignalValToBool(String signal){
        if((signal == "Z") || (signal == "0")){
            return false;
        }
        else{
            return true;
        }
    }

    String convertBooleanToStr(boolean signal){
        if(signal == true){
            return "1";
        }
        else return "0";
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

        //emulate the behavior of counter
        RCO = (ENT && convertSignalValToBool(_counterMemmory[0]) && convertSignalValToBool(_counterMemmory[1]) &&
                convertSignalValToBool(_counterMemmory[2]) && convertSignalValToBool(_counterMemmory[2]) );
        CCO = (RCO || (!clk) || (ENT || ENP));

        // 1) Q outputs disabled
        if(notOE){
            //_counterMemmory
            _counterMemmory[0] = "Z";
            _counterMemmory[1] = "Z";
            _counterMemmory[2] = "Z";
            _counterMemmory[3] = "Z";

            String outputSignalsInStr = convertBooleanToStr(CCO) + convertBooleanToStr(RCO) + _counterMemmory[0] +
                    _counterMemmory[1] + _counterMemmory[2] + _counterMemmory[3];
            return outputSignalsInStr;
        }

        // 2) Asynchronous clear
        if((notOE == false) && (notACLR == false)){
            _counterMemmory[0] = "0";
            _counterMemmory[1] = "0";
            _counterMemmory[2] = "0";
            _counterMemmory[3] = "0";

            String outputSignalsInStr = convertBooleanToStr(CCO) + convertBooleanToStr(RCO) + _counterMemmory[0] +
                    _counterMemmory[1] + _counterMemmory[2] + _counterMemmory[3];
            return outputSignalsInStr;
        }

        // 3) Asynchronous load
        if((notOE == false) && (notACLR == true) && (notALOAD == false)){
            _counterMemmory[0] = convertBooleanToStr(inputInBinary[8]);
            _counterMemmory[1] = convertBooleanToStr(inputInBinary[9]);
            _counterMemmory[2] = convertBooleanToStr(inputInBinary[10]);
            _counterMemmory[3] = convertBooleanToStr(inputInBinary[11]);

            String outputSignalsInStr = convertBooleanToStr(CCO) + convertBooleanToStr(RCO) + _counterMemmory[0] +
                    _counterMemmory[1] + _counterMemmory[2] + _counterMemmory[3];
            return outputSignalsInStr;
        }

        // 4) Synchronous clear
        if((notOE == false) && (notACLR == true) && (notALOAD == true) && (notSCLR == false) && clk == true){
            _counterMemmory[0] = "0";
            _counterMemmory[1] = "0";
            _counterMemmory[2] = "0";
            _counterMemmory[3] = "0";

            String outputSignalsInStr = convertBooleanToStr(CCO) + convertBooleanToStr(RCO) + _counterMemmory[0] +
                    _counterMemmory[1] + _counterMemmory[2] + _counterMemmory[3];
            return outputSignalsInStr;
        }

        // 5) Synchronous load
        if((notOE == false) && (notACLR == true) && (notALOAD == true) && (notSCLR == true) && (notSLOAD == false) && clk == true){
            _counterMemmory[0] = convertBooleanToStr(inputInBinary[8]);
            _counterMemmory[1] = convertBooleanToStr(inputInBinary[9]);
            _counterMemmory[2] = convertBooleanToStr(inputInBinary[10]);
            _counterMemmory[3] = convertBooleanToStr(inputInBinary[11]);

            String outputSignalsInStr = convertBooleanToStr(CCO) + convertBooleanToStr(RCO) + _counterMemmory[0] +
                    _counterMemmory[1] + _counterMemmory[2] + _counterMemmory[3];
            return outputSignalsInStr;
        }

        // 6) Count
        if((notOE == false) && (notACLR == true) && (notALOAD == true) && (notSCLR == true) && (notSLOAD == true) && (ENT == true) && (ENP == true) && clk == true){
            String result = "";
            int counterValue = 0;
            int one = 1;
            for(int i=0; i<_nBitCounter; i++) {
                result += _counterMemmory[i];
            }
            if(_counterMemmory[3] == "1"){
                counterValue += (one << 3);
            }
            if(_counterMemmory[2] == "1"){
                counterValue += (one << 2);
            }
            if(_counterMemmory[1] == "1"){
                counterValue += (one << 1);
            }
            if(_counterMemmory[0] == "1"){
                counterValue += 1;
            }
            counterValue += 1;
            String counterVal = Integer.toString(counterValue);
            for(int i=0; i<_nBitCounter; i++) {
                _counterMemmory[i] = counterVal.charAt(i);
            }

        }

        // 7) Inhibit counting
        if((notOE == false) && (notACLR == true) && (notALOAD == true) && (notSCLR == true) && (notSLOAD == true) && (ENT == false)){

        }

        // 8) Inhibit counting
        if((notOE == false) && (notACLR == true) && (notALOAD == true) && (notSCLR == true) && (notSLOAD == true) && (ENP == false)){

        }

        /*
        int digits = inputInBinary.length;
        boolean [] outputSignals = new boolean[digits];
        outputSignals[0] = CCO;
        outputSignals[1] = RCO;
        outputSignals[2] = Qa;
        outputSignals[3] = Qb;
        outputSignals[4] = Qc;
        outputSignals[5] = Qd;
        */
        String outputSignalsInStr = "UUUUUU";
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
