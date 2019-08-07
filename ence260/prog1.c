/* An improved (but incomplete) version of twiddle.c that
 * doesn't use external (global) variables.
 * Written for ENCE260 June 2011/2013/2018
 * Author: Richard Lobb
 */

#include <stdio.h>
#include <ctype.h>

#define MAX_NAME_LENGTH 80

// Read a name (or any string) into the parameter array.
// Terminate it with null.
void readName(int maxLen, char name[])
{
    int c = 0;
    int i = 0;
    printf("Enter your name: ");
    while ((c = getchar()) != '\n' && c != EOF && i < maxLen - 1) {
        name[i++] = c;
    }
    name[i] = '\0';  /* terminator */
}

void convertStringToUpper(char name[])
{
    int i = 0;
    while (name[i] != '\0') {
        name[i] = toupper(name[i]);
        ++i;
    }
}

int countMatches(int n, int data[], int searchValue)
{
    int i = 0;
    int count = 0;
    while (data[i] != '\0' && i < n) {
        if(data[i] == searchValue) {
            ++count;
        }
        ++i;

    }
    return count;
}

int isWonRow(char player, char game[3][3], int rowNum)
{

    if (game[rowNum][0] == game[rowNum][1] && game[rowNum][1] == game[rowNum][2] && game[rowNum][0] == game[rowNum][2]) {
        if (game[rowNum][0] == player) {
            return 1;
        }
    }
    return 0;
}



int main(void)
{

char game[3][3] = {{'X', 'O', ' '},{'X', 'X', 'X'}, {' ', ' ', ' '}};
printf("%d\n", isWonRow('X', game, 1));

char game1[3][3] = {{'X', 'O', ' '},{' ', ' ', ' '}, {'X', 'X', 'O'}};
printf("%d\n", isWonRow('X', game1, 2));

char game2[3][3] = {{'X', 'O', ' '},{' ', ' ', ' '}, {'O', 'O', 'O'}};
printf("%d\n", isWonRow('O', game2, 2));

char game3[3][3] = {{'X', 'O', ' '},{' ', ' ', ' '}, {'O', 'O', 'O'}};
printf("%d\n", isWonRow('O', game3, 0));

char game4[3][3] = {{'X', 'X', 'X'},{' ', ' ', ' '}, {'O', 'O', 'O'}};
printf("%d\n", isWonRow('O', game4, 0));
}
