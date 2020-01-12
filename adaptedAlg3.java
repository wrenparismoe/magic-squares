package finalLab;

// Find all r-combinations of the n<sup>2</sup>-set with the magic sum.
// Each r-combination is permuted giving the r-permutations to be used as rows.
// Finally, all potential magic square arrangements are built and tested using
// the MagicSquare class and narrowRows method. (Works for 4 x 4 magic squares)

import java.util.ArrayList;

public class adaptedAlg3 {

    static int n = 3;

    static int magicSum = ((int)Math.pow(n,3) + n) / 2;

    static ArrayList<ArrayList<Integer>> Permutations = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> Combinations = new ArrayList<>();

    static ArrayList<MagicSquare> msList = new ArrayList<>();

    static int[][] magicSquare = new int[n][n];

    public static void main(String[] args){
        ArrayList<Integer> emptySet = new ArrayList<>();
        int[] emptyArr = new int[n];
        ArrayList<Integer> nset = getNset();

        MagicSquare ms = new MagicSquare(n);

        long start = System.nanoTime();
        comb(emptyArr, nset, 0, nset.size()-1,0);
        for(ArrayList<Integer> combination : Combinations){
            permute(emptySet, combination);
        }
        buildSquares(ms, Permutations);
        long end = System.nanoTime();
        long elapsed = end - start;

        System.out.println("Total Combinations: " + Combinations.size());
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

    public static void permute(ArrayList<Integer> p,ArrayList<Integer> comb){
        calls++;
        if (p.size() == n){
            Permutations.add(p);
            return;
        }
        for(int i = 0; i < comb.size(); i++){
            ArrayList<Integer> tempSet = new ArrayList<>(comb);
            ArrayList<Integer> tempP = new ArrayList<>(p);
            tempSet.remove(i);
            tempP.add(comb.get(i));
            permute(tempP, tempSet);
        }
    }

    static void comb(int[] p, ArrayList<Integer> set, int start, int end, int index) {
        calls++;
        if (index == n) {
            int sum = 0;
            ArrayList<Integer> perm = new ArrayList<>();
            for(int i = 0; i < p.length; i++){
                perm.add(p[i]);
                sum += p[i];
            }
            if(sum != magicSum){
                return;
            }
            Combinations.add(perm);
            return;
        }

        for (int i=start; i<=end && end-i+1 >= n-index; i++){
            p[index] = set.get(i);
            comb(p, set, i+1, end, index+1);
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
