#include<stdio.h>
int math(int x) {
	if(x <= 0) {
		return 0;
	}
	return x + math(--x);
}
void main() {
	int i;
	scanf("%d", &i);
	printf("%d\n", math(i));
}
