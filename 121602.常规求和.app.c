#include<stdio.h>
void main() {
	int i = 0;
	int sum = 0;
	scanf("%d", &i);
	for (;i > 0 ; i--) {
		sum += i;
	}
	printf("%d\n", sum);
}
