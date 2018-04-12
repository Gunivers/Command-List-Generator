package net.gunivers.listgenerator.util;

public class Calculator {
	
	private int index = 0;
	private String text;
	private String lexeme = "";
	private String lexemeType = "";
	
	
	public Calculator(String text) {
		this.text = text;
	}

	public  void read() {
		lexeme = "";
		lexemeType = "";
		while(index < text.length() && text.charAt(index) == ' ')
			index++;
		if(index >= text.length())
			lexemeType = "EOL";
		else {
			if(Character.toString(text.charAt(index)).matches("\\d")) {
				lexemeType = "NUMBER";
				while(index < text.length() && Character.toString(text.charAt(index)).matches("\\d")) {
					lexeme += text.charAt(index);
					index++;
				}
			} else if(Character.toString(text.charAt(index)).matches("[\\(\\)\\-\\+\\*\\/\\%]")) {
				lexemeType = "SYMBOL";
				lexeme = Character.toString(text.charAt(index));
				index++;
			} else throw new UnsupportedOperationException("Caractère invalide");
		}
	}
	
	
	public void disp() {
		read();
		while(!lexemeType.equals("EOL")) {
			read();
		}
	}


	public  int readNumber() {
		if(!lexemeType.equals("NUMBER")) {
			throw new UnsupportedOperationException("Pas un nombre");
		}
		int value = Integer.parseInt(lexeme);
		read();
		return value;
	}
	
	public int readExpression() {
		int value = readTerm();
		while(lexemeType.equals("SYMBOL") && (lexeme.equals("+") || lexeme.equals("-"))) {
			String op = lexeme;
			read();
			int value2 = readTerm();
			if(op.equals("+"))
				value += value2;
			if(op.equals("-"))
				value -= value2;
		}
		return value;
	}
	
	public int readFactor() {
		int value;
		if(lexemeType.equals("SYMBOL") && lexeme.equals("(")) {
			read();
			value = (int) readExpression();
			if(lexemeType.equals("SYMBOL") && lexeme.equals(")"))
				read();
		} else value = readNumber();
		return value;
	}
	
	public int readTerm() {
		int value = readFactor();
		while(lexemeType.equals("SYMBOL") && (lexeme.equals("*") || lexeme.equals("/") || lexeme.equals("%"))) {
			String op = lexeme;
			read();
			int value2 = readFactor();
			if(op.equals("*"))
				value *= value2;
			if(op.equals("/"))
				value /= value2;
			if(op.equals("%"))
				value %= value2;
		}
		return value;
	}
	
	public int calculate() {
		while(true) {
			int value;
			read();
			if(!lexemeType.equals("EOL")) {
				value = readExpression();
				if(lexemeType.equals("EOL"))
					return value;
			}
		}
	
	}

}
