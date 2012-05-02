#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

struct good_struct{
    double d;
    int i;
    short s;
    char c;
};

struct bad_struct{
    char c;
    double d;
    short s;
    int i;
};


int main(){
    printf("sizeof(char) = %d\n", (int)sizeof(char));
    printf("sizeof(double) = %d\n", (int)sizeof(double));
    printf("sizeof(short) = %d\n", (int)sizeof(short));
    printf("sizeof(int) = %d\n", (int)sizeof(int));
    printf("sizeof(char, double, short, int) = %d\n", (int)sizeof(struct bad_struct));
    printf("sizeof(double, int, short, char) = %d\n", (int)sizeof(struct good_struct));
    
    return EXIT_SUCCESS;
}
