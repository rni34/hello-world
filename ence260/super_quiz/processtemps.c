//Ryo Nishikawa
//20th Augast

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LINE_LENGTH 200      // The longest line this program will accept
#define MAX_NUM_STUDENTS 500    // The maximum number of students this program can handle
#define MAX_NAME_SIZE 50        // The maximum allowable name length

//finds the nth position of the character
//returns a pointer to that character
//or if it does not exist then returns NULL
char* strchrn(char* s, int c, int n)
{
    int occurence = 0;
    if(n <= 0) {
        return NULL;
    }
    while(*s != '\0') {
        if (*s == c) {
            occurence ++;
        }
        if ( occurence == n) {
            return s;
        }
        s++;
    }
    return NULL;
}

//prints the name of the station and each site that
//exceeds the threshold
void printSite(FILE *file, double threshold)
{
    char buffer[MAX_LINE_LENGTH];
    fgets(buffer, MAX_LINE_LENGTH, file);
    fgets(buffer, MAX_LINE_LENGTH, file);
    fgets(buffer, MAX_LINE_LENGTH, file);
    char* commaPos = strchr(buffer, ',');
    *commaPos = '\0';
    printf("Dates when %.1f C was reached at %s\n\n",threshold, buffer);

    printf("   Date       MaxTemp\n");

    fgets(buffer, MAX_LINE_LENGTH, file);
    int c = fgetc(file);
    int done = false;
    while(!(done)) {
        c = fgetc(file);
        if(c == '\n') {
            c = fgetc(file);
            if(c == '\n') {
                done = true;
            }
        }
    }
    fgets(buffer, MAX_LINE_LENGTH, file);
    fgets(buffer, MAX_LINE_LENGTH, file);
    fgets(buffer, MAX_LINE_LENGTH, file);
    while (strcmp(buffer, "\n") != 0) {
        char* commaPos2 = NULL;
        double maxTemp = 0;

        int date = 0;
        int month = 0;
        int year = 0;

        commaPos2 = strchrn(buffer,',',2);
        maxTemp = atof(commaPos2+1);
        if(maxTemp >= threshold) {
            date = atoi(&buffer[11]);
            buffer[11] = '\0';
            month = atoi(&buffer[9]);
            buffer[9] = '\0';
            year = atoi(&buffer[5]);
            printf("%02d/%02d/%d%9.1lf C\n",date, month, year, maxTemp);
        }
        fgets(buffer, MAX_LINE_LENGTH, file);
    }
}




// Main program. Read a linked list of students from a csv file, then display
// the contents of that list.
int main(int argc, char* argv[])
{
    if(argc != 3) {
        fprintf(stderr,"Usage: processtemps filename threshold");
    } else {

        char* filename = argv[1];
        double threshold = atof(argv[2]);
        FILE* inputFile = fopen(filename, "r");
        if (inputFile == NULL) {
            fprintf(stderr, "File '%s' not found\n",filename);
        } else {

            printSite(inputFile, threshold);
        }
    }

\
}
