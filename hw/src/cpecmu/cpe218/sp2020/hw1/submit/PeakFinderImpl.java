package cpecmu.cpe218.sp2020.hw1.submit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import cpecmu.cpe218.sp2020.hw1.PeakFinder;

public class PeakFinderImpl implements PeakFinder {

    /** input file name */
    protected static final String inFile = "D:/261218-hw-release/hw/hw1tests/peakfinder02.in";
    //protected static final String inFile = "D:/261218-hw-release/hw/hw1tests/Wowza/peakfinder03.in";

    @Override
    public int peakPosition(int[] a) {
      return peakPosition(a, 0, a.length); // call recursive function
    }
    // recursive function
    public static int peakPosition(int[] a, int first, int last){
        int size = a.length;
        int middle = (last + first)/2;
        if(size == 1){  //ถ้ามีสมาชิก 1 ตัว ให้ตอบตัวนั้น
            return 0;
        }else{
            //ถ้าอยู่ริมสุดและมีค่ามากกว่าอีกตัวข้างๆให้ตอบตัวนั้น หรือ ถ้าไม่ได้อยู่ริมต้องมากกว่าสองตัวข้างซ้าย/ขวา ถึงจะเป็นคำตอบ
            if((middle==0 && a[middle] >= a[middle+1]) || (middle == size-1 && a[middle] >= a[middle-1]) 
                || (middle!=0 && middle!=(size-1) && a[middle] >= a[middle-1] && a[middle] >= a[middle+1])){
                return middle;
            }else{
                //ถ้าตัวเลขที่อยู่ข้างซ้ายมีค่ามากกว่า ให้ทำ recursive ทางฝั่งซ้าย
                if(a[middle] < a[middle-1]){
                    return peakPosition(a, first, middle-1);
                }else{
                    //ถ้าตัวเลขที่อยู่ข้างขวามีค่ามากกว่า ให้ทำ recursive ทางฝั่งขวา
                    if(a[middle] < a[middle+1]){
                        return peakPosition(a, middle+1, last);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try (FileReader fr = new FileReader(inFile);
             Scanner s = new Scanner(fr)) {
            int n = s.nextInt();
            // read array
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = s.nextInt();

            // invoke algorithm
            PeakFinder sbm = new PeakFinderImpl();
            int res = sbm.peakPosition(a);
            System.out.println(res);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
