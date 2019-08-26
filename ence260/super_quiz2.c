/* prog.c
 * A variant of structexample3.c from lab 5 with the following changes:
 *
 * 1. The student struct now contains a firstname, a lastname and a student ID
 *    instead of a name and an age.
 * 2. The input data file is again a CSV file but the fields are firstname,
 *    lastname and student ID (an int).
 *
 * Richard Lobb, August 2019.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LINE_LENGTH 500      // The longest line this program will accept
#define MAX_NUM_STUDENTS 500    // The maximum number of students this program can handle
#define MAX_NAME_SIZE 50        // The maximum allowable name length

// The declaration of the student record (or struct). Note that
// the struct contains the first and last names as arrays of characters.

typedef struct student_s Student;

struct student_s {
    char firstname[MAX_NAME_SIZE];
    char lastname[MAX_NAME_SIZE];
    int studentId;
    Student* next;              // Pointer to next student in a list
};

bool precedes(const Student* student1, const Student* student2)
{
    if (strcmp(student1->firstname, student2->firstname) < 0) {
        //if student 1 needs to be front of student 2 by firstname
        return true;
    } else {
        if (strcmp(student1->firstname, student2->firstname) == 0) {
            if (strcmp(student1->lastname, student2->lastname) < 0) {
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
Student* newStudent(const char* firstname, const char* lastname, int studentId)
{
    Student* student = NULL;
    if (firstFree < MAX_NUM_STUDENTS) {
        student = &studentPool[firstFree];
        firstFree += 1;
        strncpy(student->firstname, firstname, MAX_NAME_SIZE);
        student->firstname[MAX_NAME_SIZE - 1] = '\0';  // Make sure it's terminated
        strncpy(student->lastname, lastname, MAX_NAME_SIZE);
        student->lastname[MAX_NAME_SIZE - 1] = '\0';  // Make sure it's terminated
        student->studentId = studentId;
        student->next = NULL;
    }
    return student;
}

// Read a single student from a csv input file with student first name in first column,
// second name in the second column and studentId in the last (third) column.
// Returns: A pointer to a Student record, or NULL if EOF occurs or if
// a line with fewer than 2 commas is read.
Student* readOneStudent(FILE* file)
{
    Student* student = NULL;       // Pointer to a student record from the pool
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


void printOneStudent(Student student)
{
    printf("%s %s (%d)\n", student.firstname, student.lastname, student.studentId);
}


void printStudents(const Student* student)
{
    while (student != NULL) {
        printOneStudent(*student);
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
