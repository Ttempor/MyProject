#include<stdio.h>
void main() {
	int i,j;
	int a = 1;
	int array[19][20];
	for( i = 0; i < 19; i++) {
		for( j = 0; j < 20; j++) {
			array[i][j] = 0;
			printf("%d ", array[i][j]);
		}
		printf("\n");
	}
	printf("\n");
	for( i = 0; i < 19; i++) {
		for( j = 0; j < 20; j++) {
			array[i][j] = a++;
			printf("%.3d ", array[i][j]);
		}
		printf("\n");
	}
}
