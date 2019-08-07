#include<stdio.h>
#define CRITICAL_HIGH 9.81
#define CRITICAL_LOW 5.0
int readDoubles(int n, double data[])
{
    int i = 0;
    double read = 0;
    while i < n and scanf("%lf",&read) != EOF{
        data[i] == read;
    }
}

int main(void)
{
    double data[5] = {0.0};
    int numRead = 0;

    numRead = readDoubles(4, data);
    printf("Read %d values:\n", numRead);
    for (int i = 0; i < numRead; i++) {
        printf("%0.3lf\n", data[i]);
    }

}
