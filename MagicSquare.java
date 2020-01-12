package finalLab;
import java.util.*;
import java.util.ArrayList;

public class MagicSquare {

    int[][] square;
    int n;

    int[] rowSum;
    int[] colSum;
    int[] diagSum;

    int magicSum;

    MagicSquare(int n){
        this.square = new int[n][n];
        this.n = n;
        this.magicSum = ((int)Math.pow(n,3) + n) / 2;
        this.rowSum = new int[n];
        this.colSum = new int[n];
        this.diagSum = new int[2];

    }

    MagicSquare(MagicSquare ms){
        this.square = new int[ms.n][ms.n];
        this.n = ms.n;
        this.rowSum = new int[n];
        this.colSum = new int[n];
        this.diagSum = new int[2];
        for(int i = 0; i < ms.n; i++){
            for(int j = 0; j < ms.n; j++){
                int cur = ms.square[i][j];
                this.square[i][j] = cur;
                rowSum[i] += cur;
                colSum[j] += cur;
                if(i - j == 0){
                    diagSum[0] += cur;
                }
                if(i + j == n - 1){
                    diagSum[1] += cur;
                }
            }
        }
        this.magicSum = ms.magicSum;
    }

    public void addRow(ArrayList<Integer> row){
        int i = rowIndex();
        for(int j = 0; j < n; j++){
            square[i][j] = row.get(j);
            rowSum[i] += row.get(j);
            colSum[j] += row.get(j);
            if(i - j == 0){
                diagSum[0] += row.get(j);
            }
            if(i + j == n - 1){
                diagSum[1] += row.get(j);
            }
        }
    }

    public int rowIndex(){
        for(int i = 0; i < n; i++){
            if(square[i][0] == 0){
                return i;
            }
        }
        return -1;
    }

    public boolean isFull(){
        return square[n-1][1] != 0;
    }

    public void rbruteBuild(ArrayList<ArrayList<Integer>> rows){
        // builds magic square using r-permutations
        for(int i = 0; i < n; i++){
            ArrayList<Integer> currentRow = rows.get(i);
            for(int j = 0; j < n; j++){
                int cur = currentRow.get(j);
                square[i][j] = cur;
                rowSum[i] += cur;
                colSum[j] += cur;
                if(i - j == 0){
                    diagSum[0] += cur;
                }
                if(i + j == n - 1){
                    diagSum[1] += cur;
                }
            }
        }

    }

    public void nbruteBuild(ArrayList<Integer> p){
        // builds magic square using full permutation of 1,2,...,n^2
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                square[i][j] = p.get(count);
                count++;
            }
        }

    }

    public boolean testSquare(){
        for(int i = 0; i < n; i++){
            if(rowSum[i] != magicSum || colSum[i] != magicSum){
                return false;
            }
        }
        if(diagSum[0] != magicSum || diagSum[1] != magicSum){
            return false;
        }
        return true;
    }


    public void printSquare(){
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
