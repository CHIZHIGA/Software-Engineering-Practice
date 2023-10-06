package Ass7;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculation4 extends JFrame {
	private static final long serialVersionUID = 1L;//实现序列化,serialVersionUID 用来表明实现序列化类的不同版本间的兼容性

	Container c = getContentPane(); // 用于固定宽度并支持响应式布局的容器
	StringBuilder number1 = new StringBuilder(""); // 储存第1个数字字符串
	StringBuilder number2 = new StringBuilder(""); // 储存第2个数字字符串
	StringBuilder operator = new StringBuilder(""); // 储存运算符
	StringBuilder result = new StringBuilder(""); // 储存运算结果
	JTextField numbershow = new JTextField("0"); // 数字显示区域，初始显示为"0"

	public static void main(String[] args) { // 主方法
		// 除键(C):在计算中按下此键将清除除存储器内容外的所有数值和计算符号，即可以重新开始输入计算.
		// 清除键(CE):在计算中按下此键将清除除存储器内容外的上一步内容，即可以重新输入按CE前输入的数字（含多位数）或计算符号.
		calculation4 c1 = new calculation4();
		c1.setVisible(true);
	}

	public calculation4() { // 计算器构造方法
		setTitle("计算器");
		setBounds(100, 100, 260, 245);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createNumberShow(); // 创建数字显示区域
		createClearButtons();// 创建Backspace,CE,C等三个按钮
		createButtonArea(); // 创建数字按钮区域
	}

	private void createNumberShow() { // 创建数字显示区域的方法

		numbershow.setHorizontalAlignment(JTextField.RIGHT);
		numbershow.setBounds(5, 0, 245, 22);
		numbershow.setEnabled(false);
		numbershow.setDisabledTextColor(Color.BLACK);
		c.add(numbershow);
	}

	private void createClearButtons() { // 创建Backspace,CE,C等三个按钮的方法

		JButton[] clearbutton = new JButton[3];
		String[] clearbuttontext = { "Backspace", "CE", "C" };
		for (int i = 0; i < 3; i++) {
			clearbutton[i] = new JButton();
			clearbutton[i].setText(clearbuttontext[i]);
			clearbutton[i].setHorizontalAlignment(SwingConstants.CENTER);
			clearbutton[i].setMargin(new Insets(0, 0, 0, 0));
			clearbutton[i].setFont(new java.awt.Font("Arial", 0, 9));
			clearbutton[i].setForeground(Color.blue);
			clearbutton[i].setBounds(48 + i * 68, 30, 63, 22);
			clearbutton[i].setForeground(Color.red);
			c.add(clearbutton[i]);
		}

		clearbutton[0].addActionListener(new ActionListener() {// 为Backspace按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				if (number1.toString().equals("")) { // 如果未做任何输入
					showNumber(numbershow, number1); // 显示number1
				} else if (operator.toString().equals("")) { // 如果只输入了number1

					number1.deleteCharAt(number1.toString().length() - 1);// 将number1的最后一个字符去掉
					showNumber(numbershow, number1); // 显示number1

				} else if (number2.toString().equals("")) { // 如果只输入了number1和operator
					showNumber(numbershow, number1); // 不作任何处理，显示number1

				} else { // 如果输入了number1、operator、number2

					number2.deleteCharAt(number2.toString().length() - 1);// 将number2的最后一个字符去掉
					showNumber(numbershow, number2); // 显示number2
				}
			}
		});

		clearbutton[1].addActionListener(new ActionListener() {// 为CE按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				if (number1.toString().equals("")) { // 如果未做任何输入
					showNumber(numbershow, number1); // 显示number1
				} else if (operator.toString().equals("")) { // 如果只输入了number1

					number1.setLength(0); // 清除number1
					showNumber(numbershow, number1); // 显示number1

				} else if (number2.toString().equals("")) { // 如果输入了number1和operator

					showNumber(numbershow, number2); // 不作任何处理，显示number2

				} else { // 如果输入了number1、operator、number2
					number2.setLength(0); // 清除number2
					showNumber(numbershow, number2); // 显示number2
				}
			}
		});

		clearbutton[2].addActionListener(new ActionListener() {// 为C按钮添加监听器
			public void actionPerformed(ActionEvent e) { // 将所有储存清零
				number1.setLength(0);
				number2.setLength(0);
				operator.setLength(0);
				numbershow.setText("0.");
				result.setLength(0);
			}
		});
	}

	private void createButtonArea() { // 创建数字按钮区域的方法
		JPanel ButtonArea = new JPanel();
		ButtonArea.setBounds(5, 55, 245, 125);
		ButtonArea.setLayout(new GridLayout(4, 5, 5, 5));
		c.add(ButtonArea);
		JButton[] numberbutton = new JButton[20];
		String[] numberbuttontext = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/X",
				"0", "+/-", ".", "+", "=" };

		for (int i = 0; i <= 19; i++) { // 使用循环为这20个按钮添加标识
			numberbutton[i] = new JButton(numberbuttontext[i]);
			ButtonArea.add(numberbutton[i]);
			if (i % 5 == 3 || i == 19) {// 操作符按钮设置为红色
				numberbutton[i].setHorizontalAlignment(SwingConstants.CENTER);// 文本居中
				numberbutton[i].setMargin(new Insets(0, 0, 0, 0));// 构件在显示区的上，左，下，右的空白区0
				numberbutton[i].setFont(new java.awt.Font("Arial", 0, 9));
				// name表示的是字体的样式，常用的字体有Times New Roman、Symbol、宋体、楷体等。
				// style表示的是字体的风格，默认是正常Font.PLAIN，有加粗Font.BLOD,有斜体Font.INTALIC
				// 可以加粗+斜体Font.BLOD + Font.INTALIC
				numberbutton[i].setForeground(Color.red);
			} else { // 其它设置为蓝色

				numberbutton[i].setHorizontalAlignment(SwingConstants.CENTER);
				numberbutton[i].setMargin(new Insets(0, 0, 0, 0));
				numberbutton[i].setFont(new java.awt.Font("Arial", 0, 9));
				numberbutton[i].setForeground(Color.blue);
			}
		}

		int[] numbers = { 15, 10, 11, 12, 5, 6, 7, 0, 1, 2 };// 该数组分别代表0-9等数字在numberbuttontext数组中序号
		for (int i = 0; i <= 9; i++) { // 使用循环为这0-9这十个数字按钮添加监听器
			final String str = String.valueOf(i); // 封装,将括号里面这个 i 的值转换成一个‘String’类型的值

			numberbutton[numbers[i]].addActionListener(new ActionListener() {// 为0-9按钮添加监听器

				public void actionPerformed(ActionEvent e) {
					if (operator.toString().equals("")) { // 没有输入operator之前
						add(number1, str); // 只设置number1的值
						showNumber(numbershow, number1); // 只显示number1的值
					} else { // 输入operator之后
						add(number2, str); // 只设置number2的值
						showNumber(numbershow, number2); // 只显示number2的值
					}
				}
			});
		}

		numberbutton[16].addActionListener(new ActionListener() { // 为"+/-"按钮添加监听器
			public void actionPerformed(ActionEvent e) {
				if (operator.toString().equals("")) { // 没有输入operator之前
					add(number1, "+/-"); // 只设置number1的值
					showNumber(numbershow, number1); // 只显示number1的值
				} else { // 输入operator之后
					add(number2, "+/-"); // 只设置number2的值
					showNumber(numbershow, number2); // 只显示number2的值
				}
			}
		});

		numberbutton[17].addActionListener(new ActionListener() { // 为"."按钮添加监听器
			public void actionPerformed(ActionEvent e) {
				if (operator.toString().equals("")) { // 在输入operator之前，只显示number1的值
					add(number1, ".");
					showNumber(numbershow, number1);
				} else { // 在输入operator之后，只显示number2的值
					add(number2, ".");
					showNumber(numbershow, number2);
				}
			}
		});

		numberbutton[18].addActionListener(new ActionListener() { // 为"+"按钮添加监听器
			public void actionPerformed(ActionEvent e) {
				operator.setLength(0);
				operator.append("+");
			}
		});

		numberbutton[13].addActionListener(new ActionListener() { // 为"-"按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				operator.setLength(0);
				operator.append("-");
			}
		});

		numberbutton[8].addActionListener(new ActionListener() { // 为"*"按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				operator.setLength(0);
				operator.append("*");
			}
		});

		numberbutton[3].addActionListener(new ActionListener() { // 为"/"按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				operator.setLength(0);
				operator.append("/");
			}
		});

		numberbutton[19].addActionListener(new ActionListener() { // 为"="按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				if (number1.toString().equals("")) {// 当number1为空时
					showNumber(numbershow, number1);
				} else if (operator.toString().equals("")) { // 当number1不为空，而operator为空时
					showNumber(numbershow, number1);
				} else if (number2.toString().equals("")) {

                    // 当number1、operator均不为空，而number2为空时        
					switch (operator.toString()) {
					case ("+"): {
						number2.append(number1.toString());// 1=2
						double d = Double.parseDouble(number1.toString()) + Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("-"): {
						number2.append(number1.toString());
						double d = Double.parseDouble(number1.toString()) - Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("*"): {
						number2.append(number1.toString());
						double d = Double.parseDouble(number1.toString()) * Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("/"): {
						number2.append(number1.toString());
						double d = Double.parseDouble(number1.toString()) / Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;
					}
				} else {

                    // 当number1、operator、number2均不为空时
					switch (operator.toString()) {
					case ("+"): {
						double d = Double.parseDouble(number1.toString()) + Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("-"): {
						double d = Double.parseDouble(number1.toString()) - Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("*"): {
						double d = Double.parseDouble(number1.toString()) * Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;

					case ("/"): {
						double d = Double.parseDouble(number1.toString()) / Double.parseDouble(number2.toString());
						result.setLength(0);
						result.append(d);
						showNumber(numbershow, result);
						number1.setLength(0);
						number1.append(d);
					}
						break;
					}
				}
			}
		});

		numberbutton[14].addActionListener(new ActionListener() { // 为"1/x"按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				if (number1.toString().equals("")) { // 没有输入number1时
					numbershow.setText("输入非法0");
				} else if (operator.toString().equals("")) { // 输入了number1，但没有输入operator

					if (Double.parseDouble(number1.toString()) == 0) { // 如果number1的值为零
						numbershow.setText("除数不能为零");
					} else { // 如果number1的值不为零

						double d = 1 / (Double.parseDouble(number1.toString()));
						number1.setLength(0);
						number1.append(d); // 将number1的值开放并存储
						showNumber(numbershow, number1);
					}
				} else if (number2.toString().equals("")) { // 输入了number1、operator，但没有输入number2

					double d = 1 / (Double.parseDouble(number1.toString()));
					number2.append(d);// 将number1的值开放并存储
					showNumber(numbershow, number2);
				} else { // 输入了number1、operator，number2

					double d = 1 / (Double.parseDouble(number2.toString()));
					number2.setLength(0);
					number2.append(d);// 将number2的值开放并存储
					showNumber(numbershow, number2);
				}
			}
		});

		numberbutton[9].addActionListener(new ActionListener() { // 为"%"按钮添加监听器

			public void actionPerformed(ActionEvent e) {
				if (number1.toString().equals("")) { // 没有输入number1时
					numbershow.setText("输入非法0");
				} else if (operator.toString().equals("")) { // 输入了number1，但没有输入operator

					if (Double.parseDouble(number1.toString()) == 0) { // 如果number1的值为零
						numbershow.setText("0");
					} else { // 如果number1的值不为零

						double d = (Double.parseDouble(number1.toString()) / 100);
						number1.setLength(0);
						number1.append(d); // 将number1的值开放并存储
						showNumber(numbershow, number1);
					}
				} else if (number2.toString().equals("")) { // 输入了number1、operator，但没有输入number2

					double d = (Double.parseDouble(number1.toString()) / 100);
					number2.append(d);// 将number1的值开放并存储
					showNumber(numbershow, number2);
				} else { // 输入了number1、operator，number2

					double d = (Double.parseDouble(number2.toString()) / 100);
					number2.setLength(0);
					number2.append(d);// 将number2的值开放并存储
					showNumber(numbershow, number2);
				}
			}
		});

		numberbutton[4].addActionListener(new ActionListener() { // 为"sqrt"按钮添加监听器

			public void actionPerformed(ActionEvent e) {

				if (number1.toString().equals("")) {// 没有输入number1时
					showNumber(numbershow, number1);
				} else if (operator.toString().equals("")) {// 输入了number1，但没有输入operator

					if (Double.parseDouble(number1.toString()) < 0) {// number1小于0
						numbershow.setText("函数输入无效");
					} else {// number1大于0

						double d = Math.sqrt(Double.parseDouble(number1.toString()));
						number1.setLength(0);
						number1.append(d);// 将number1的值开放并存储
						showNumber(numbershow, number1);
					}
				} else if (number2.toString().equals("")) {// 输入了number1、operator，但没有输入number2

					double d = Math.sqrt(Double.parseDouble(number1.toString()));
					number2.append(d);// 将number1的值开放并存储
					showNumber(numbershow, number2);
				} else {// 输入了number1、operator、number2

					double d = Math.sqrt(Double.parseDouble(number2.toString()));
					number2.setLength(0);
					number2.append(d);// 将number2的值开放并存储
					showNumber(numbershow, number2);
				}
			}
		});
	}

	public void add(StringBuilder s1, String s2) { // 定义按钮输入后数字字符串变化的方法
		if (s2.equals("+/-")) {// 定义输入"+/-"后数字字符串的变化
			if (s1.toString().equals("") || s1.toString().equals("0")) {// 如果数字字符串为空或者0，那么不发生变化
				s1.append("");
			} else {// 如果数字字符串不为空也不为0，那么在数字字符串前增加或删除"-"字符

				if (s1.toString().startsWith("-")) {
					s1.deleteCharAt(0);
				} else {
					s1.insert(0, "-");
				}
			}
		}

		if (s2.equals(".")) {// 定义输入"."后数字字符串的变化
			if (s1.toString().indexOf(".") == -1) {// 查找数字字符串中是否含有"."字符，如果没有则执行以下代码
				if (s1.toString().equals("")) {// 如果数字字符串为空，那么将数字字符串设置为"0."
					s1.setLength(0);
					s1.append("0");
				} else {
					s1.append(".");
				}
			} else {// 如果有，则不发生变化

				s1.append("");
			}
		}
		if (s2.equals("0")) {// 定义输入"0"后数字字符串的变化
			if (s1.toString().equals("0")) {// 当数字的字符串为"0"时，不发生变化
				s1.append("");
			} else {// 当数字的字符串吧为"0"时，在其字符串后增加"0"

				s1.append("0");
			}
		}
		for (int i = 1; i < 10; i++) {// 通过循环，定义输入1-9后数字字符串的变化
			String str = String.valueOf(i);

			if (s2.equals(str)) {// 定义输入1-9后数字字符串的变化
				if (s1.toString().equals("0")) {
					s1.setLength(0);
					s1.append(str);
				} else

					s1.append(str);
			}
		}
	}

	public void showNumber(JTextField j, StringBuilder s) {// 定义数字显示区域如何显示数字字符串的方法

		if (s.toString().equals("") == true || s.toString().equals("0") == true) {
			j.setText("0.");
		} else if (s.toString().indexOf(".") == -1) {

			j.setText(s.toString() + ".");
		} else {
			j.setText(s.toString());
		}
	}
}
