package net.gunivers.commandlistgenerator.util;

public class Calculator extends HelpFunctionality
{

	private int index = 0;
	private String text;
	private String lexeme = "";
	private String lexemeType = "";

	public Calculator(String text)
	{
		this.text = text;
	}

	public void read()
	{
		lexeme = "";
		lexemeType = "";

		while (index < text.length() && text.charAt(index) == ' ')
			index++;

		if (index >= text.length())
			lexemeType = "EOL";

		else
		{
			if (Character.toString(text.charAt(index)).matches("\\d(.\\d)?"))
			{
				lexemeType = "NUMBER";
				while (index < text.length() && Character.toString(text.charAt(index)).matches("[\\d\\.]"))
				{
					lexeme += text.charAt(index);
					index++;
				}

			} else if (Character.toString(text.charAt(index)).matches("[\\(\\)\\-\\+\\*\\/\\%]"))
			{
				lexemeType = "SYMBOL";
				lexeme = Character.toString(text.charAt(index));
				index++;

			} else
				throw new UnsupportedOperationException("Caractère invalide");
		}
	}

	public void disp()
	{
		read();

		while (!lexemeType.equals("EOL"))
		{
			read();
		}
	}

	public double readNumber()
	{
		if (!lexemeType.equals("NUMBER"))
		{
			throw new UnsupportedOperationException("lexemeType should be a Number");
		}

		double value = Double.parseDouble(lexeme);
		read();
		return value;
	}

	public double readExpression()
	{
		double value = readTerm();

		while (lexemeType.equals("SYMBOL") && (lexeme.equals("+") || lexeme.equals("-")))
		{
			String op = lexeme;
			read();
			double value2 = readTerm();

			if (op.equals("+"))
				value += value2;

			if (op.equals("-"))
				value -= value2;
		}

		return value;
	}

	public double readFactor()
	{
		double value;

		if (lexemeType.equals("SYMBOL") && lexeme.equals("("))
		{
			read();
			if (lexemeType.equals("SYMBOL") && (lexeme.equals("+") || lexeme.equals("-")))
			{
				read();
				value = (lexeme.equals("-")) ? -(double) readExpression() : (double) readExpression();
			} else
				value = (double) readExpression();

			if (lexemeType.equals("SYMBOL") && lexeme.equals(")"))
				read();

		} else
			value = readNumber();

		return value;
	}

	public double readTerm()
	{
		double value = readFactor();

		while (lexemeType.equals("SYMBOL") && (lexeme.equals("*") || lexeme.equals("/") || lexeme.equals("%")))
		{
			String op = lexeme;
			read();
			double value2 = readFactor();

			if (op.equals("*"))
				value *= value2;

			if (op.equals("/"))
				value /= value2;

			if (op.equals("%"))
				value %= value2;
		}

		return value;
	}

	public double calculate()
	{
		double value;
		read();

		if (!lexemeType.equals("EOL"))
		{
			value = readExpression();

			if (lexemeType.equals("EOL"))
				return value;
		}

		return 0;
	}

	@Override
	public String getHelp() {
		return "Calculate an equation"
				+ "\nIn the equation:"
				+ "\n X: the current value, default is 1"
				+ "\n U: the previous value, default is 0";
	}
}
