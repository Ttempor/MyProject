#include<stdio.h>
#include<string.h>
void main() {
	char s1[] = "喊出我的名字吧";
	printf("吃货的日程总是被安排，请问现在是什么节？\n");	
	scanf("%s", &s1);
	if (!strcmp(s1, "春节")) {
		printf("给我安排饺子\n");
	} else if(!strcmp(s1, "端午节")) {
		printf("给我安排粽子\n");
	} else if(!strcmp(s1, "中秋节")) {
		printf("给我安排月饼\n");
	} else if(!strcmp(s1, "元宵节")) {
		printf("给我安排汤圆\n");
	}
} 
