/* processstudents.c: a program to process students read from a file.
 * In fact it does very little other then read the students and link
 * them into a list, which it then prints.
 * Written to demonstrate the use of structs, linked lists and separate
 * compilation for course ENCE260.
 *
 * Author: Richard Lobb
 * Date: August 2014.
 */

#include <stdio.h>
#include <stdlib.h>

#include "student.h"
#include "studentlist.h"


int main(void)
{
    FILE* inputFile = fopen("studlist.txt", "r");
    if (inputFile == NULL) {
        fprintf(stderr, "File not found\n");
    } else {
        Student* studentList = readStudents(inputFile);
        printStudents(studentList);

        // The program could now do various things that make use of
        // the linked list, like deleting students and adding new ones,
        // but the program is already quite long enough!
    }
}
