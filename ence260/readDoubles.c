/*
Date: 7/8/2019
Author Ryo Nishikawa
*/
#include<stdio.h>

#define CRITICAL_HIGH 9.81
#define CRITICAL_LOW 5.0
#define MAX_SIZE 100000

int readDoubles(int n, double data[])
/*
reads the each value from the file until EOF or size of the array
* and appends to the array.
*/
{
    int i = 0;
    double read = 0;
    while (i < MAX_SIZE && scanf("%lf",&read) != EOF) {
        data[i] = read;
        ++i;
    }
    return i;
}

void smoothData(int n, double data[])
/*
recieves the date which is size of n and modifies each value.
* WITHOUT copying an array to save memory
*/
{
    int i = 0;
    double back = 0;
    double now = 0;
    while (i < n) {
        now = data[i];

        if (i == 0) {
            data[i] = (3 * now + data[1])/4;

        } else if(i==n-1) {
            data[i] = (back + 3 * data[i])/4;

        } else {
            data[i] = (back + 2 * data[i] + data[i+1])/4;
        }
        back = now;
        i++;

    }
}

void accelermometer_calculation(void)
{
    /*
        does the calculation what they want for you
    */
    double data[MAX_SIZE];
    int count = readDoubles(MAX_SIZE, data);
    smoothData(count, data);
    int its_been_low = 1;
    int exceeded_data[count];
    double maximum_speed = 0;
    double time_occured = 0;
    for(int i = 0; i <= count; i++) {
        if (data[i] < CRITICAL_LOW) {
            its_been_low = 1;
        }
        if (data[i] >= CRITICAL_HIGH && (its_been_low == 1) ) {
            its_been_low = 0;
            exceeded_data[i] = 1;
        }
        if (data[i] > maximum_speed) {
            maximum_speed = data[i];
            time_occured = i;
        }

    }
    for (int i = 1; i < count; i++) {
        if(exceeded_data[i] == 1) {
            printf("Acceleration of 9.81 m/sec^2 exceeded at t = %.2lf secs.\n", i * 0.01);
        }
    }

    printf("\nMaximum acceleration: %.2lf m/sec^2 at t = %.2lf secs.", maximum_speed, time_occured * 0.01);

}


int main(void)
{
    accelermometer_calculation();

}
