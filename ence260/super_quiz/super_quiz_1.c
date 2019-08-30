/* super_quiz4.c
 * A variant of structexample3.c from lab 5 with the following changes:
 *
 * 1. The student struct now contains a firstname, a lastname and a student ID
 *    instead of a name and an age.
 * 2. The input data file is again a CSV file but the fields are firstname,
 *    lastname and student ID (an int).
 * 3. Students will be put in the alphabetical order
 * Richard Lobb, August 2019.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LINE_LENGTH 500
#define MAX_NUM_STUDENTS 500
#define MAX_NAME_SIZE 50        // The maximum allowable name length

// The declaration of the student record (or struct). Note that
// the struct contains the first and last names as arrays of characters.

typedef struct student_s Student;

struct student_s {
    char fname[MAX_NAME_SIZE];
    char lname[MAX_NAME_SIZE];
    int studentId;
    Student* next;              // Pointer to next student in a list
};
//returns true if student1 should come before studnet2
//checks the first name, if first name were equal then
//checks the last name otherwise return false
bool precedes(const Student* student1, const Student* student2)
{
    if (strcmp(student1->fname, student2->fname) < 0) {
        //if student 1 needs to be front of student 2 by firstname
        return true;
    } else {
        if (strcmp(student1->fname, student2->fname) == 0) {
            if (strcmp(student1->lname, student2->lname) < 0) {
                // if student 1 needs to be front of student 2 by lastname
                return true;
            }
        }
        return false;
    }

}


// Create a pool of student records to be allocated on demand
Student studentPool[MAX_NUM_STUDENTS];  // The student pool
int firstFree = 0;

// Return a pointer to a new student record from the pool, after
// filling in the provided first and last name and student ID fields.
// Returns NULL if the student pool is exhausted.
Student* newStudent(const char* fname, const char* lname, int studentId)
{
    Student* student = NULL;
    if (firstFree < MAX_NUM_STUDENTS) {
        student = &studentPool[firstFree];
        firstFree += 1;
        strncpy(student->fname, fname, MAX_NAME_SIZE);
        student->fname[MAX_NAME_SIZE - 1] = '\0';
        strncpy(student->lname, lname, MAX_NAME_SIZE);
        student->lname[MAX_NAME_SIZE - 1] = '\0';
        student->studentId = studentId;
        student->next = NULL;
    }
    return student;
}

// Read a single student from a csv input file with
// student first name in first column,
// second name in the second column and studentId in the last (third) column.
// Returns: A pointer to a Student record, or NULL if EOF occurs or if
// a line with fewer than 2 commas is read.
Student* readOneStudent(FILE* file)
{
    Student* student = NULL;
    // Oops, a heap of code seems to have gone missing here.
    char buffer[MAX_LINE_LENGTH];
    char* inputLine = fgets(buffer, MAX_LINE_LENGTH, file);

    if (inputLine != NULL) {
        char* commaPos = strchr(buffer, ',');

        if (commaPos != NULL) {
            *commaPos = '\0';
            char* commaPos2 = strchr(commaPos+1, ',');

            char* firstname = buffer;

            if (commaPos2 != NULL) {
                *commaPos2 = '\0';
                int studentId = atoi(commaPos2+1);
                char* lastname = commaPos+1;

                student = newStudent(firstname, lastname, studentId);
            }
        }
    }
    return student;
}
//Return pointer to first student in linked list
//takes student and inserts in a right order
Student* insert(Student* student, Student* first)
{
    Student* current = first;
    if (precedes(student, current)) {
        student->next = current;
        return student;

    } else {

        while (current->next != NULL) {
            if (precedes(student, current->next)) {
                student->next = current->next;
                current->next = student;
                return first;

            } else {
                current = current->next;
            }
        }
        current->next = student;
    }

    return first;

}


//reads a file and creates a linked list of studnets
//returns a poiter to start of the linked list
Student* readStudents(FILE *file)
{
    Student* first = NULL;
    Student* last = NULL;
    Student* student = readOneStudent(file);
    while (student != NULL) {
        if (first == NULL) {
            first = last = student;
        } else {
            first = insert(student, first);
        }
        student = readOneStudent(file);
    }
    return first;
}


//prints first name, last name and student Id of each student
void printStudents(const Student* student)
{
    while (student != NULL) {
        printf("%s %s (%d)\n", student->fname,student->lname, student->studentId);
        student = student->next;
    }
}



int main(int argc, char* argv[])
{
    FILE* inputFile = NULL;

    if(argc != 2) {
        fprintf(stderr,"Usage: prog filename\n");
    } else {
        inputFile = fopen(argv[1], "r");
        if (inputFile == NULL) {
            fprintf(stderr, "File '%s' not found\n", argv[1]);
        } else {

            Student* studentList = readStudents(inputFile);
            printStudents(studentList);
        }
    }
}
