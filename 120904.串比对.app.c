#include<stdio.h>
#include<string.h>
void main() {
	char s1[] = "喊出我的名字吧";
	char s2[] = "Utlra泽塔！";
	if(!strcmp(s1, s2)) {
		printf("同\n");
	} else {
		printf("不同\n");	
	}
}
