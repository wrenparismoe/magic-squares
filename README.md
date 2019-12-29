# Magic Squares

A magic square is an n x n dimensional grid filled with positive integers 1, 2, 3, ... , n<sup>2</sup> so that every cell is distinct and the sum of every row, column, and diagonal is equivalent. This lab studies some of the underlying mathematical theory behind magic squares. The algorithms take a combinatoric approach by thinking of the problem through permutations. Several variations of the brute force algorithm were implemented to attempt at producing the largest scale of magic squares possible. This would prove to be an increasingly difficult problem. Time complexities are calculated to illustrate the difficulty of producing magic squares. 

## Algorithms

**1. Brute Force #1:** Computes all permutations of the n<sup>2</sup>-set while simultaneously fitting each permutation into a magic square structure and testing if the permutation has magic square properties. (Only works for 3 x 3 magic squares) 

Total Permutations: 362880
Magic Squares: 8
Elapsed Time: 477738600
Recursive Calls: 986410


**2. Brute Force #2:** Finds all r-permutations of the n<sup>2</sup>-set and tests every combination of the r-permutations to see if they build a magic square. (Only works for 3 x 3 magic squares)

Total Permutations: 504
Magic Squares: 8
Elapsed Time: 14210189300
Recursive Calls: 3061556

**3. Brute Force #3:** Finds only the r-permutations that have the magic sum property. Then all combinations of the r-permutations are tested to check whether they build a magic square.

Total Permutations: 48
Magic Squares: 8
Elapsed Time: 104913000
Recursive Calls: 22763

**4. Adapted Algorithm #1:** Using only r-permutations with the magic sum, as the combinations of rows are built, all other r-permutations with numbers that have already been used are removed from the current row list.



**5. Adapted Algorithm #2:**

Note: Additional algorithms are included that are not in the lab report.

## Breif Analysis

One would assume that the second addition of the brute force algorithm would improve upon the first. However, this was incorrect. The second algorithm requires two similarly looking recursive methods meaning there are many more recursive calls needed in order for the algorithm to complete.
