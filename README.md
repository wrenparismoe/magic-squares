# Magic Squares

A magic square is an n x n dimensional grid filled with positive integers 1, 2, 3, ... , n<sup>2</sup> so that every cell is distinct and the sum of every row, column, and diagonal is equivalent. This lab studies some of the underlying mathematical theory behind magic squares. The algorithms take a combinatoric approach by thinking of the problem through permutations. Several variations of the brute force algorithm were implemented to attempt at producing the largest scale of magic squares possible. This would prove to be an increasingly difficult problem. Time complexities are calculated to illustrate the difficulty of producing magic squares. 

Defintions:

## Implementation

The implementation is easy for each of the alorithms. First, download the python of java file and change the package name at the top of the proram. Second, change the global int n at the top of the class to the wanted dimensions for the magic squares.

## Algorithms

**1. Brute Force #1:** Recursively computes all permutations of the n<sup>2</sup>-set fitting each into an n x n square to test for magic square properties. (Only works for 3 x 3 magic squares) 

Total Permutations: 362880
Magic Squares: 8
Elapsed Time: 477738600
Recursive Calls: 986410

**2. Brute Force #2:** Using two recursive methods, all r-permutations of the n<sup>2</sup>-set are computed. Every combination of r-permutations is found and tested in the buildSquares method to determine if a magic square is formed. (Only works for 3 x 3 magic squares)

Total Permutations: 504
Magic Squares: 8
Elapsed Time: 14210189300
Recursive Calls: 3061556

**3. Brute Force #3:** Using only r-permutations with the magic sum property, all row combinations are built and tested for a magic square. (Only works for 3 x 3 magic squares) 

Total Permutations: 48
Magic Squares: 8
Elapsed Time: 104913000
Recursive Calls: 22763

**4. Adapted Algorithm #1:** All square combinations are built using only r-permutations with the magic sum. During each iteration of the recursive buildSquares method, rows including numbers already existing in the partially built square are removed from the current row list. Once a square is filled it is tested for the magic sum property. (Inefficient for 4 x 4 magic squares)

Total Permutations: 48
Magic Squares: 8
Elapsed Time: 53517500
Recursive Calls: 3659

**5. Adapted Algorithm #2:** Using the MagicSquare object class, partial column sums are updated as rows are added to the magic square. Remaining column sums are computed by subtracting partial sums from the magic sum. Rows containing numbers used in the square or numbers greater than the remaining sum are removed from the current row list. (Works for 4 x 4 magic squares)

Total Permutations: 48
Magic Squares: 8
Elapsed Time: 14516100
Recursive Calls: 1043

Total Permutations: 2064
Magic Squares: 7040
Elapsed Time: 463701448900
Recursive Calls: 62918610

Note: Additional algorithms included not in the lab report.

## Breif Analysis

One would assume that the second addition of the brute force algorithm would improve upon the first. However, this was incorrect. The second algorithm requires two similarly looking recursive methods meaning there are many more recursive calls needed in order for the algorithm to complete.
