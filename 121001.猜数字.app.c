#include<stdio.h>
#include<string.h>
void hec(int v ,int u);
void main() {
	char temp;
	char secret[] = "一二三四五";
	int a;
	int b = 5;
	printf("想玩儿游戏吗，先回答下一句是什么？天王盖地虎，___________。\n");
	while(true) {
		scanf("%s", &secret);
		if(!strcmp(secret, "宝塔镇河妖")) {
			printf("答对了，开始吧.\n");
			break;
		}
		printf("答错了，请重答\n");
	}
	for(;;) {
		printf("输入在0   ~  20\n");
		if(!scanf("%d", &a)) {
			scanf("%c", &temp);
			printf("请输入数字\n");
			continue;
		}
		if (a == b) {
			printf("答对了,你太厉害了，答案是%d\n", b);
			break;
		} else if (a > b) {
			printf("数值大了\n");
		} else if (a < b) {
			printf("数值小了\n");
		}
	}

}

//1.内置一个中文口令，输入中文口令才能进入游戏。
//      例如“想玩儿游戏吗，先回答下一句是什么？天王盖地虎，___________。”，用户输入：宝塔镇河妖   才能开始游戏。
//2.内置一个整型数字答案，比如答案为15，答案数字为1-20之间的数字都可以。
//3.用户回答太大或者太小都会提示，使用循环语句，直到猜正确为止。
//4.如果回答正确，显示“你太厉害了”类似字样。

