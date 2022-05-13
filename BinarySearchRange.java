import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BinarySearchRange {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> data = new ArrayList<Integer>();

        data=readData(args[0]);
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);

        BinarySearchRange obj = new BinarySearchRange();
        if (data == null || x > y) {
            System.out.println("null");
        } else if (y < data.get(0)) {
            System.out.println("null");
        } else if (x > data.get(data.size()-1)) {
            System.out.println("null");
        } else if (x < data.get(0) && y > data.get(data.size()-1)) {
            System.out.printf("\nA[0..%d]\n", data.size() - 1);
            printArray(data, 0, data.size() - 1);
        } else if (x <= data.get(0) && y < data.get(1) ) {
            System.out.printf("A[0] \n");
            printArray(data, 0, 0);
        }
        else {
            int ix, iy;
            ix = obj.searchForX(data, x);
            iy = obj.searchForY(data, y);
            if (ix > iy) { //fix
                System.out.println("null");
            } else {
                System.out.printf("\nA[%d..%d]\n", ix, iy);
                printArray(data, ix, iy);
            }
        }
    }

    public int searchForX(ArrayList<Integer> data, int x) {
        int low = 0;
        int high = data.size();
        int mid = 0;
        while (high >= low) {
            mid = (int) Math.floor((high + low) / 2);
            if (x <= data.get(0)) {
                return 0;
            } else if (high - low ==1) {
                return high;
            } else if (x == data.get(mid)) {
                if (x == data.get(mid - 1)) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            } else if (x > data.get(mid)) {
                low = mid;
            } else if (x < data.get(mid)) {
                if (data.get(mid - 1) < x) {
                    return mid;
                } else {
                    high = mid;
                }
            }
        }
        return mid;
    }

    public int searchForY(ArrayList<Integer> data, int y) {
        int low = 0;
        int high = data.size() ;
        int mid = 0;
        while (high >= low) {
            if (high - low ==1) {
                return low;
            }
            mid = (int) Math.floor((high + low) / 2);
            if (y >= data.get(data.size() -1 )){
                return data.size()-1;
            } else if (y == data.get(mid)) {
                if (y == data.get(mid+1)) {
                    low = mid;
                } else if (data.get(mid +1) < y) {
                    low = mid+1;
                } else {
                    return mid;
                }
            } else if (y > data.get(mid)) {
                if ( data.get(mid+1) <= y ) {
                    low = mid;
                } else {
                    return mid;
                }
            } else if (y < data.get(mid)) {
                if (data.get(mid - 1) == y) {
                    return mid - 1;
                } else if (data.get(mid - 1) > y) {
                    high = mid - 1;
                } else {
                    return mid - 1;
                }
            }
        }
        return mid;
    }

    static ArrayList<Integer> readData(String dataFile)
            throws FileNotFoundException, IOException{
        ArrayList<Integer> data = new ArrayList<>();
        File file = new File(dataFile);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st=br.readLine()) != null) {
            data.add(Integer.parseInt(st));
        }
        return data;
    }

    static void printArray(ArrayList<Integer> A, int x, int y) {
        for (int i = x; i <= y; i++) {
            System.out.printf("%4d", A.get(i));
        }
        System.out.println("");
    }
}