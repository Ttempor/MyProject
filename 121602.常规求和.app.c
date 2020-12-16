#include<stdio.h>
void main() {
	int i = 0;
	int sum = 0;
	scanf("%d", &i);
	for (; i > 0; i--) {
		sum += i;
	}
	printf("%d\n", sum);
}


//等差数列公式，公差为1
#include<stdio.h>
void main() {
	int n = 0;
	scanf("%d", &n);
	printf("%d\n", (n * (1 + n)) / 2);
}
