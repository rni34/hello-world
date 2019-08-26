
#include <stdio.h>
#include <string.h>
#include <math.h>
#include "confab.h"

#define MAX_LINE_SIZE 1000

void confab(const char inText[], int nRows, char outText[])
{
    int outIndex = 0;
    int n = strlen(inText);
    int nCols = ceil(n / (double) nRows);

    for (int row = 0; row < nRows; row += 1) {
        for (int col = 0; col < nCols; col += 1) {
            int index = nRows * col + row;
            if (index < n) {
                outText[outIndex++] = inText[index];
            }
        }
    }
    outText[outIndex++] = '\0';
}
