package yes1206;

import java.util.Scanner;
class FirstProject 
{ 
	public static int tempMoney = 0;
	static int allMoney = 10000;
	static String plan = "";
	static int mySelectIs = 0;
	static Scanner scannerSelect = new Scanner(System.in);
//	readKeyBoard();
	static boolean isExit = true;
	public static void main(String[] args) 
	{  

		Begin();	
	}
	static void mean(){
		System.out.println('\n'+"-----------------家庭收支记账软件-----------------");
		System.out.println("		 1 收支明细");//要输入四位数以内的数字，其次是字符
		System.out.println("		 2 登记收入");//要输入四位数以内的数字，其次是字符
		System.out.println("		 3 登记支出");
		System.out.println("		 4 退 出");//只读y和n
		System.out.print("		 请选择(1-4)：");
	}


	static String readKeyBoard(int max){
		Scanner scanner = new Scanner(System.in);
		String input = "";
//		int i = 1;
		while(scanner.hasNext()){
			input = scanner.nextLine();					 //与&&逻辑符，有false则为fasle
			if(input.length() < 1||input.length() > max){//或||逻辑符，有true则为true
				System.out.println("输入错误，请重新输入：");
				continue;
			}
			else{
				return input;
			}		
/*			System.out.println(input);
			i ++ ; 
			if(i>10)break;		*/
		}
		System.out.print("debug readKeyBoard未进入循环 55行");
		return input;
	}
	static char CaseFour(){
		String stringTemp = "";	
		boolean boolTemp = true;
		while(boolTemp){
			stringTemp = readKeyBoard(1).toUpperCase();
			if(stringTemp.charAt(0) == 'Y'){
//				System.out.println("debug 63行 CaseFour获得了" + stringTemp);
				return 'y';
//				break;
			}
			else if(stringTemp.charAt(0) == 'N'){
//				System.out.println("debug 67行 CaseFour获得了" + stringTemp);
				return 'n';
//				break;
			}
			else{
			System.out.println("输入错误，请输入Y/N");
//				System.out.println("debug 72行 CaseFour获得了" + stringTemp);
//				break;
				continue;
			}
		}
		System.out.println("未进入循环，默认返回y");
		return 'y';
	}
	static int SelcetInt(){
		String selectIntTemp = "";
		while(true){
			selectIntTemp = readKeyBoard(1);
			char selectCharTemp = selectIntTemp.charAt(0);
			switch(selectCharTemp){
				case '1':
					return 1;
				case '2':
					return 2;
				case '3':
					return 3;
				case '4':
					return 4;
				default:
					System.out.print("		请输入1-4:");
					continue;
			}
		}		
	}
	static int IntCaseThree(){
		int caseThreeTemp = 0;
		String temp = "";
		//for(;;){
		while(true){
			try{
				temp = readKeyBoard(4);
				caseThreeTemp = Integer.parseInt(temp);
				return caseThreeTemp;
			}
			catch(Exception e){
				System.out.println("输入错误，请输入4位以内数字");
				continue;
			}
		}
	}
	static String Instruction(){
		String temp = "";
		temp = readKeyBoard(8);
		return temp;
	}
	static void Record(){
		System.out.println("-----------------当前收支明细记录-----------------");
		System.out.println("收支	账户金额		收支金额		说明");
	}
	static void Begin(){
		while(isExit){
			mean();
			mySelectIs = SelcetInt();
			switch(mySelectIs){
				case 1:
					System.out.println('\n');
					Record();
//					System.out.println("debug 成功进入1");
					System.out.println(plan+'\n');
					continue;
				case 2:
//					System.out.println("debug 成功进入2");
					System.out.print("本次收入金额: ");
					FirstProject.tempMoney = IntCaseThree();
					allMoney += FirstProject.tempMoney;
					plan += ("收入"+'\t'+allMoney+'\t'+'\t'+FirstProject.tempMoney+'\t'+'\t');
					System.out.print("本次收入说明: " );
					plan += (Instruction() + '\n');
					continue;
				case 3:
//					System.out.print("debug 成功进入3");
					System.out.print("本次支出金额: ");			
					while(true){
						FirstProject.tempMoney = IntCaseThree();
						if(FirstProject.tempMoney > allMoney){
							System.out.print('\t'+"已超支，请重输");
							continue;
						}
						else break;
					}
					allMoney -= FirstProject.tempMoney;
					plan += ("支出"+'\t'+allMoney+'\t'+'\t'+FirstProject.tempMoney+'\t'+'\t');
					System.out.print("本次支出说明: ");
					plan += (Instruction() + '\n');
					continue;
				case 4:
					System.out.print("确认是否退出(Y/N)：");
					char tempChar = CaseFour();
					if (tempChar == 'y'){
						System.out.println("退出成功");
						return;
					}
					else if (tempChar == 'n'){
						continue;
					}
				default:{System.out.print("debug 执行了defalut 21行 执行失败");break;}//执行失败
			}
			break;
		}
	}
}


//总结：首次使用java完成的项目，首次使用try catch ，首次使用Integer.parseInt(),首次使用toUpperCase(),
//首次使用String.length(),首次使用Scanner.hasNext(),