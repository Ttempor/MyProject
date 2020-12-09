#include<stdio.h>
void main() {
	int a = 90;
	int array[9][10];
	for(int i = 0; i < 9; i++) {
		for(int j = 0; j < 10; j++) {
			array[i][j] = 0;
			printf("%d   ", array[i][j]);
		}
		printf("\n");
	}
	printf("\n");
	for(int i1 = 0; i1 < 9; i1++) {
		for(int j1 = 0; j1 < 10; j1++) {
			array[i1][j1] = a--;
			printf(".2%d   ", array[i1][j1]);
		}
		printf("\n");
	}
}
