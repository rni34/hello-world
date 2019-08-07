#include <stdio.h>
#include <stdint.h>
#include <math.h>
int main(void)
{
    int inputs, result = 0;
    scanf("%d", &inputs);
    while(inputs != 42) {
        scanf("%d", &inputs);
        result ++;
    }
    printf("%d",result );

    return 0;
}
