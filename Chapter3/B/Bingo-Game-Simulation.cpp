#include <iostream>
#include <stdio.h>
#include <time.h>
#include <unistd.h>

// Constants
const int MAX_SIZE = 5;

// Types

typedef struct 
{
int number;
bool isMarked;
} BingoCellType;


// Function Prototypes
void generateBingoCard(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE]);
void displayBingoCard(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE]);
void checkForBingoFirstRow(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE]);
void checkForDiagonalBingoLR(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE]);


// ------------------------------
int main (int argc, char *argv[])
{
const int MAX_ITERATIONS = 10;

BingoCellType bingoCard[MAX_SIZE][MAX_SIZE];
int choice;
pid_t childPID;

generateBingoCard(bingoCard);

displayBingoCard(bingoCard);

for (int i = 1; i <= MAX_ITERATIONS; i++)
   {
   childPID = fork();
   if (childPID == 0)
      {
      switch(i)
         {
         case 1: checkForBingoFirstRow(bingoCard);
                 break;
         case 2: checkForDiagonalBingoLR(bingoCard);
                 break;
         default: printf("\nUnknown choice: %d\n", i);        
         } // End switch
      return 0;   
      } // End if      
   else
      continue;
   } // end for
sleep(1);      
return 0;
} // End main


// -------------------------
// Note: This is a simplified approach to creating a randomly-generated
//       bingo card; consequently, one or more duplicate numbers may
//       appear in a column sometimes.
void generateBingoCard(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE])
{
int baseNbr;
int randomNbr;
int cellNbr;

srand(time(0));  // This seeds the random number generator with the current time

for (int row = 0; row < MAX_SIZE; row++)
   for (int column = 0; column < MAX_SIZE; column++)
      {
      baseNbr = (row + 1) + (column * 15);
      randomNbr = rand() % 11;  
      cellNbr = baseNbr + randomNbr;
      bingoCard[row][column].number = cellNbr;
      if (cellNbr % 3 == 0) 
         bingoCard[row][column].isMarked = true;
      else if (cellNbr % 5 == 0) 
         bingoCard[row][column].isMarked = true;
      else if (cellNbr % 7 == 0) 
         bingoCard[row][column].isMarked = true;
      else   
         bingoCard[row][column].isMarked = false;
      } // End for

} // End generateBingoCard


// -------------------------
void displayBingoCard(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE])
{
printf(" B    I    N    G    O\n");
printf(" --   --   --   --   --\n");

for (int row = 0; row < MAX_SIZE; row++)
   {
   for (int column = 0; column < MAX_SIZE; column++)
      {
      // Mark the numbers that have been called
      if (bingoCard[row][column].isMarked)
         printf("(");
      else 
         printf(" ");
         
      // Mark the free spot   
      if (row == 2 && column == 2)
         printf("**");
      else
         printf("%2d", bingoCard[row][column].number);

      // Mark the numbers that have been called
      if (bingoCard[row][column].isMarked)
         printf(") ");
      else 
         printf("  ");
      } // End for column
   printf("\n");
   } // End for row

} // End displayBingoCard


// ------------------------------------------------------
void checkForBingoFirstRow(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE])
{
int cellCount = 0;

for (int row = 0; row < 1; row++)
   {
   for (int column = 0; column < MAX_SIZE; column++)
      {
     if (bingoCard[0][column].isMarked)
         cellCount++;
        
      } // End for column
   } // End for row

if (cellCount == 5)
   printf("\n(%ld) !!!CONGRATULATIONS!!! There is a Bingo in the first row\n", getpid());
else
   printf("\n(%ld) There is no Bingo in the first row\n", getpid());
} // End checkForBingoFirstRow


// ------------------------------------------------------
void checkForDiagonalBingoLR(BingoCellType bingoCard[MAX_SIZE][MAX_SIZE])
{
int cellCount = 0;

if (bingoCard[0][0].isMarked) cellCount++;
if (bingoCard[1][1].isMarked) cellCount++;
if (bingoCard[2][2].isMarked) cellCount++;
if (bingoCard[3][3].isMarked) cellCount++;
if (bingoCard[4][4].isMarked) cellCount++;

if (cellCount == 5)
   printf("\n(%ld) !!!CONGRATULATIONS!!! There is a Bingo in the LR diagonal\n", getpid());
else
   printf("\n(%ld) There is no Bingo in the LR diagonal\n", getpid());
} // End checkFOrDiagonalBingoLR

