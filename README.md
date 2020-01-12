# Magic Squares

A magic square is an n x n dimensional grid filled with positive integers 1, 2, 3,..., n<sup>2</sup> so that every cell is distinct and the sum of every row, column, and diagonal is equivalent. This lab studies some of the underlying mathematical theory behind magic squares. The algorithms take a combinatoric approach by thinking of the problem through permutations. Several variations of the brute force algorithm were implemented to attempt at producing the largest scale of magic squares possible. This would prove to be an increasingly difficult problem. Time complexities are calculated to illustrate the difficulty of producing magic squares. Additionally, the number of recursive calls are recorded to analyze the efficiencies of the algorithms.

Defintions:
- **n-set:** A set with cardinality n. (n<sup>2</sup>-sets are used in the context of magic squares)
- **permutation:** An ordered arrangement of the n elements in an n-set.
- **r-permutation:** An ordered arrangement of r elements of an n-set.
- **r-combination:** A non-ordered arrangement of r elements of an n-set.

## Implementation

The implementation for each algorithm is easy. First, download the python or java file and change the package name at the top of the program. Second, change the global int n at the top of the class to the wanted magic square dimensions.

## Programs
### Brute Force Alorithms
**1. Brute Force #1:** Recursively computes all permutations of the n<sup>2</sup>-set fitting each into an n x n square to test for magic square properties. (Only works for 3 x 3 magic squares) 

3 x 3: \
Total Permutations: 362880 \
Magic Squares: 8 \
Elapsed Time: 477738600 \
Recursive Calls: 986410

**2. Brute Force #2:** Using two recursive methods, all r-permutations of the n<sup>2</sup>-set are computed. Every arrangement of r-permutations is found and tested in the buildSquares method to determine if a magic square is formed. (Only works for 3 x 3 magic squares)

3 x 3: \
Total Permutations: 504 \
Magic Squares: 8 \
Elapsed Time: 14210189300 \
Recursive Calls: 3061556

**3. Brute Force #3:** Using only r-permutations with the magic sum property, all row combinations are built and tested for a magic square. (Only works for 3 x 3 magic squares) 

3 x 3: \
Total Permutations: 48 \
Magic Squares: 8 \
Elapsed Time: 104913000 \
Recursive Calls: 22763

### Adapted Algorithms

**4. Adapted Alg #1:** Each square arrangement is built using r-permutations with the magic sum. During each iteration of the recursive buildSquares method, rows with numbers already used in a partially built square are removed from the current row list. Once a square is filled it is tested for the magic sum property. (Inefficient for 4 x 4 magic squares)

3 x 3: \
Total Permutations: 48 \
Magic Squares: 8 \
Elapsed Time: 53517500 \
Recursive Calls: 3659

**5. Adapted Alg #2:** Using the MagicSquare object class, partial column sums are updated as rows are added to the magic square. Remaining column sums are computed by subtracting partial sums from the magic sum. The narrowRows method is used to remove r-permutations containing the numbers already included in the square or numbers greater than the remaining sum. (Works for 4 x 4 magic squares)

3 x 3: \
Total Permutations: 48 \
Magic Squares: 8 \
Elapsed Time: 16388899 \
Recursive Calls: 1043

4 x 4: \
Total Permutations: 2064 \
Magic Squares: 7040 \
Elapsed Time: 463701448900 \
Recursive Calls: 62918610

**Adapted Alg #3:** Find all r-combinations of the n<sup>2</sup>-set with the magic sum. Each r-combination is permuted giving the r-permutations to be used as rows. Finally, all potential magic square arrangements are built and tested using the MagicSquare class and narrowRows method. (Works for 4 x 4 magic squares)

3 x 3: \
Total Combinations: 8 \
Total Permutations: 48 \
Magic Squares: 8 \
Elapsed Time: 13724000 \
Recursive Calls: 705

4 x 4: \
Total Combinations: 86 \
Total Permutations: 2064 \
Magic Squares: 7040 \
Elapsed Time: 464547829500 \
Recursive Calls: 62879283

## Brief Analysis

The effectiveness of each algorithm is determined by the number of recursive calls that are required to complete the program. For every approach, the time to find 3 x 3 magic squares was completely dependent on how many times each method called itself. It was simple. If more calls occured then the algorithm would have a longer runtime. The only program that disobeyed that trend was the final implementation of the adapted algorithm when tasked with finding all 4 x 4 magic squares. The algorithm took less calls than its predecessor but relatively the difference was small. The vast majority of time taken by the algorithm was taken by the buildSquares algorithm which the improvement did not affect. The variability in the equivalent buildSquares method is what led to a discontinuity in trend. However, the time to find all possible rows for a magic square is faster for the third adapted algorithm.

