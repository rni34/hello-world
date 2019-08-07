#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    float mile = 0;
    float kilo = 0;
    printf("How many miles? ");
    scanf("%f", &mile);
    kilo = mile * 1.609344;
    printf("That's %.2f km.", kilo);
    return EXIT_SUCCESS;
}
