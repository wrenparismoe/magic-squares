package finalLab;

// All square combinations are built using only r-permutations with the magic sum.
// During each iteration of the recursive buildSquares method, rows including numbers
// already in the partially built square are removed from the current row list.
// Once a square is filled it is tested for the magic sum property. (Works slowly for 4 x 4 magic squares)

import java.util.ArrayList;

public class adaptedAlg1 {

    static int n = 4;

    static int[] rowSum = new int[n];
    static int[] colSum = new int[n];
    static int[] diagSum = new int[2];

    static int magicSum = ((int)Math.pow(n,3) + n) / 2;

    static ArrayList<ArrayList<Integer>> Permutations = new ArrayList<>();

    static ArrayList<int[][]> msList = new ArrayList<>();

    static int[][] magicSquare = new int[n][n];

    public static void main(String[] args){
        ArrayList<Integer> emptySet = new ArrayList<>();
        ArrayList<Integer> nset = getNset();

        long start = System.nanoTime();
        rperm(emptySet, nset);
        buildSquares(new ArrayList<>(), Permutations);
        long end = System.nanoTime();
        long elapsed = end - start;

        System.out.println("Total Permutations: " + Permutations.size());
        System.out.println("Magic Squares: " + msList.size());
        System.out.println("Elapsed Time: " + elapsed);
        System.out.println("Recursive Calls: " + calls);
    }

    static int calls = 0;

    public static void buildSquares(ArrayList<ArrayList<Integer>> rows, ArrayList<ArrayList<Integer>> perms){
        calls++;
        ArrayList<Integer> rowList = new ArrayList<>();
        for (ArrayList<Integer> r : rows) {
            for(int j : r){
                rowList.add(j);
            }
        }

        ArrayList<ArrayList<Integer>> possibleRows = new ArrayList<>(perms);
        for(ArrayList<Integer> p : perms){
            for(int x : p){
                if(rowList.contains(x)){
                    possibleRows.remove(p);
                }
            }
        }
        if(rows.size() == n){
            resetSums();
            bruteBuild(rows);
            if(testSquare()){
                printSquare();
                msList.add(magicSquare);
            }
            return;
        }
        for(int i = 0; i < possibleRows.size(); i++){
            ArrayList<ArrayList<Integer>> tempPerms = new ArrayList<>(possibleRows);
            ArrayList<ArrayList<Integer>> tempRows = new ArrayList<>(rows);
            ArrayList<Integer> current = possibleRows.get(i);

            tempPerms.remove(i);
            tempRows.add(current);
            buildSquares(tempRows, tempPerms);

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

    public static void bruteBuild(ArrayList<ArrayList<Integer>> rows){
        // builds magic square using r-permutations
        for(int i = 0; i < n; i++){
            ArrayList<Integer> currentRow = rows.get(i);
            for(int j = 0; j < n; j++){
                int cur = currentRow.get(j);
                magicSquare[i][j] = cur;
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

    public static boolean testSquare() {
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

    public static void resetSums(){
        rowSum = new int[n];
        colSum = new int[n];
        diagSum = new int[2];
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
