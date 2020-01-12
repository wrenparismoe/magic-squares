package finalLab;

import java.util.ArrayList;

// Recursively computes all permutations of the n^2-set fitting each into an n x n square to test for magic square properties. (Only works for 3 x 3 magic squares)

public class bruteForce1 {

    static int n = 3;

    static int magicSum = ((int)Math.pow(n,3) + n) / 2;

    static ArrayList<ArrayList<Integer>> Permutations = new ArrayList<>();

    static ArrayList<int[][]> msList = new ArrayList<>();

    static int[][] magicSquare = new int[n][n];

    public static void main(String[] args){

        ArrayList<Integer> emptySet = new ArrayList<>();
        ArrayList<Integer> nset = getNset();

        long start = System.nanoTime();
        perm(emptySet, nset);
        long end = System.nanoTime();
        long elapsed = end - start;

        System.out.println("Total Permutations: " + Permutations.size());
        System.out.println("Magic Squares: " + msList.size());
        System.out.println("Elapsed Time: " + elapsed);
        System.out.println("Recursive Calls: " + calls);

    }

    static int calls = 0;

    public static void perm(ArrayList<Integer> p, ArrayList<Integer> set){
        calls++;
        if (p.size() == n*n){
            Permutations.add(p);
            buildSquare(p);
            if(testSquare()){
                printSquare();
                msList.add(magicSquare);
            }
            return;
        }
        for(int i = 0; i < set.size(); i++){
            ArrayList<Integer> tempSet = new ArrayList<>(set);
            tempSet.remove(i);
            ArrayList<Integer> tempP = new ArrayList<>(p);
            tempP.add(set.get(i));
            perm(tempP, tempSet);
        }
    }

    public static void buildSquare(ArrayList<Integer> p){
        // builds magic square using full permutation of 1,2,...,n^2
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                magicSquare[i][j] = p.get(count);
                count++;
            }
        }
    }

    public static boolean testSquare() {
        int[] rowSum = new int[n];
        int[] colSum = new int[n];
        int[] diagSum = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = magicSquare[i][j];

                rowSum[i] += cur;
                colSum[j] += cur;
                if (i - j == 0) {
                    diagSum[0] += cur;
                }
                if (i + j == n - 1) {
                    diagSum[1] += cur;
                }
            }
        }
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
