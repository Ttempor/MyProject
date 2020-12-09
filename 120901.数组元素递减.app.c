#include<stdio.h>
void main() {
	int i,j;
	int a = 90;
	int array[9][10];
	for( i = 0; i < 9; i++) {
		for( j = 0; j < 10; j++) {
			array[i][j] = 0;
			printf("%d   ", array[i][j]);
		}
		printf("\n");
	}
	printf("\n");
	for( i = 0; i < 9; i++) {
		for( j = 0; j < 10; j++) {
			array[i][j] = a--;
			printf("%.2d   ", array[i][j]);
		}
		printf("\n");
	}
}
