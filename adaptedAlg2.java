package finalLab;

// Using the MagicSquare object class, partial column sums are updated as rows are added to the magic square.
// Remaining column sums are computed by subtracting partial sums from the magic sum. Rows containing numbers
// used in the square or numbers greater than the remaining sum are removed from the current row list.

import java.util.ArrayList;

public class adaptedAlg2 {

    static int n = 3;

    static int magicSum = ((int)Math.pow(n,3) + n) / 2;

    static ArrayList<ArrayList<Integer>> Permutations = new ArrayList<>();

    static ArrayList<MagicSquare> msList = new ArrayList<>();

    static int[][] magicSquare = new int[n][n];

    public static void main(String[] args){
        ArrayList<Integer> emptySet = new ArrayList<>();
        int[] emptyArr = new int[n];
        ArrayList<Integer> nset = getNset();

        MagicSquare ms = new MagicSquare(n);

        long start = System.nanoTime();
        rperm(emptySet, nset);
        buildSquares(ms, Permutations);
        long end = System.nanoTime();
        long elapsed = end - start;

        System.out.println("Total Permutations: " + Permutations.size());
        System.out.println("Magic Squares: " + msList.size());
        System.out.println("Elapsed Time: " + elapsed);
        System.out.println("Recursive Calls: " + calls);

    }

    static int calls = 0;

    public static ArrayList<ArrayList<Integer>> narrowRows(MagicSquare ms, ArrayList<ArrayList<Integer>> perms){
        ArrayList<Integer> rowList = new ArrayList<>();
        for (int[] row : ms.square) {
            for(int j : row){
                if(j != 0){
                    rowList.add(j);
                }
            }
        }
        ArrayList<ArrayList<Integer>> possibleRows = new ArrayList<>(perms);
        for(ArrayList<Integer> p : perms){
            for(int j = 0; j < n; j++){
                int x = p.get(j);
                int sumRemainder = magicSum - ms.colSum[j];
                if(rowList.contains(x)){
                    possibleRows.remove(p);
                    break;
                }else if(x > sumRemainder){
                    possibleRows.remove(p);
                    break;
                }
            }
        }
        return possibleRows;
    }

    public static void buildSquares(MagicSquare ms, ArrayList<ArrayList<Integer>> perms){
        calls++;
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>(narrowRows(ms, perms));

        if(ms.isFull()){
            if(ms.testSquare()){
                ms.printSquare();
                msList.add(ms);
            }
            return;
        }

        for(int i = 0; i < rows.size(); i++){
            ArrayList<ArrayList<Integer>> tempPerms = new ArrayList<>(rows);
            MagicSquare tempMS = new MagicSquare(ms);
            ArrayList<Integer> current = rows.get(i);

            tempPerms.remove(i);
            tempMS.addRow(current);
            buildSquares(tempMS, tempPerms);

        }
    }

    public static void rperm(ArrayList<Integer> p, ArrayList<Integer> set){
        calls++;
        if (p.size() == n){
            int sum = 0;
            for(int i : p){
                sum += i;
            }
            if (sum == magicSum){

                Permutations.add(p);
            }
            return;
        }
        for(int i = 0; i < set.size(); i++){
            ArrayList<Integer> tempSet = new ArrayList<>(set);
            ArrayList<Integer> tempP = new ArrayList<>(p);
            tempSet.remove(i);
            tempP.add(set.get(i));
            rperm(tempP, tempSet);
        }
    }

    // Prints all possible permutations that could make up a magic square
    public static void printPerms(ArrayList<ArrayList<Integer>> perms){
        for (ArrayList < Integer > perm: perms) {
            for (int i : perm){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("number of arrangements: " + perms.size());
    }

    public static void printSquare(){
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static ArrayList<Integer> getNset(){
        ArrayList<Integer> nset = new ArrayList<>();
        for(int i = 1; i <= n*n; i++){
            nset.add(i);
        }
        return nset;
    }
}
