#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
char* readLine(void)
{
    char* buff = NULL;
    int numBytes = 0;
    int c = 0;
    while ((c = getchar()) != EOF && c != '\n') {
        buff = realloc(buff, numBytes + 1);  // Get a new bigger block
        buff[numBytes++] = c;
    }
    if (buff != NULL) {
        buff[numBytes] = '\0';
    }
    return buff;  // NULL if no data read
}

int main(void)
{
    char* line = NULL;
    bool done = false;
    while(!done) {
        puts("Enter a line");
        line = readLine();
        if(line != NULL) {
            printf("I got: %s\n",line);
        } else {
            done = true;
        }
    }
}
