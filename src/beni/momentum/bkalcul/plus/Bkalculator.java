package beni.momentum.bkalcul.plus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public final class Bkalculator {

	// All supported operations
	private final char POW = '^';
	private final char ROOT = 'r';
	private final char MULTI = '*';
	private final char DIV = '/';
	private final char ADD = '+';
	private final char SUB = '-';
	private final List<Character> listOperators = Arrays.asList(POW, ROOT, MULTI, DIV, ADD, SUB);

	private final String PI = "pi";
	private final String E = "e";
	private final String FACT = "!";

	private StringBuilder strBuilder = new StringBuilder();

	private static Bkalculator bKalculator;

	public static Bkalculator getCalculator() {
		if (bKalculator == null) {
			bKalculator = new Bkalculator();
		}
		return bKalculator;
	}

	public interface EvaluationListener {
		public void onResult(String request, String result, long time);
	}

	// Private constructor
	private Bkalculator() {

	}

	// Main entry to evaluate an expression
	public String evaluate(String exp, EvaluationListener result) {

		long startTime = System.nanoTime();
		String res;

		if (checkCorrect(exp)) {
			exp = exp.replaceAll(" ", "");
			res = calculate(exp);
		} else
			res = null;

		if (result != null) {
			result.onResult(exp, res, System.nanoTime() - startTime);
		}
		return res;
	}

	/**
	 * Calculate a basic expression with respect of precedence
	 * 
	 * @param exp
	 * @return
	 */
	private String calculate(String exp) {

		// fist Priority 0 : POW, ROOT
		exp = "(" + exp + ")";
		exp = calculateWithParenthesis(exp);

		return exp;
	}

	/**
	 * Calculate the value in parenthesis
	 * 
	 * @param exp
	 * @return the final result
	 */
	private String calculateWithParenthesis(String exp) {

		while (exp.contains("(")) {

			System.out.println(exp);

			int lP = exp.lastIndexOf('(');
			for (int rP = lP; rP < exp.length(); rP++) {
				if (exp.charAt(rP) == ')') {

					// Fix Issue with pi, e and !
					String subExp = exp.substring(lP + 1, rP);
					subExp = subExp.toLowerCase();
					if (subExp.contains(PI) || subExp.contains(E) || subExp.contains(FACT))
						subExp += "+0";

					subExp = calculatePriority0(subExp);
					subExp = calculatePriority1(subExp);
					subExp = calculatePriority2(subExp);
					/** */

					// We update the main expression
					strBuilder.delete(0, strBuilder.length());
					strBuilder.append(exp.substring(0, lP));

					// if n(a+b) then n*(a+b)
					if (lP > 0 && (!listOperators.contains(exp.charAt(lP - 1)) && exp.charAt(lP - 1) != '('))
						strBuilder.append("*");

					strBuilder.append(subExp);

					if (rP < exp.length() - 1
							&& (!listOperators.contains(exp.charAt(rP + 1)) && exp.charAt(rP + 1) != ')'))
						strBuilder.append("*");

					strBuilder.append(exp.substring(rP + 1, exp.length()));

					exp = strBuilder.toString();
					break;
				}
			}
		}
		return exp;
	}

	/**
	 * While the expression contains POW(^) or ROOT(r)
	 * 
	 * @param exp
	 * @return the expression without any POW nor ROOT
	 */
	private String calculatePriority0(String exp) {

		while (exp.contains(String.valueOf(POW)) || exp.contains(String.valueOf(ROOT))) {

			System.out.println("Exp " + exp);

			// Loop Right to Left
			for (int i = exp.length() - 1; i >= 0; i--) {
				char c = exp.charAt(i);
				if (c == POW || c == ROOT) {

					int pVL = i, pVR = i;
					String vL = "", vR = "";
					// First Left
					while (pVL > 0) {
						pVL--;
						char cVL = exp.charAt(pVL);
						// If it's not un operande then its a value
						if (!listOperators.contains(cVL)) {
							vL = cVL + vL;
						}
						else if(cVL == SUB)
							vL = cVL + vL;
						else
							break;
					}

					// Second Right
					boolean foundV = false;
					while (pVR < exp.length() - 1) {
						pVR++;
						char cVR = exp.charAt(pVR);

						// if found a operator and values was found
						if (listOperators.contains(cVR) && foundV) {
							break;
						}
						// if found a operator and no value found
						else if (listOperators.contains(cVR) && !foundV) {
							vR += cVR;
						}
						// if found a value
						else if (!listOperators.contains(cVR)) {
							vR += cVR;
							foundV = true;
						} else
							break;
					}
					// String evaluate the expression;
					String value = operate(vL, c, vR);
					String subExp = vL + (c == POW ? "\\" + c : c) + vR;

					exp = exp.replaceAll(subExp, value);
					break;
				}
			}
		}
		return exp;
	}

	/**
	 * While the expression contains MULTIPLY(*) or DIVIDE(/)
	 * 
	 * @param exp
	 * @return the expression without any MULTIPLY nor DIVIDE
	 */
	private String calculatePriority1(String exp) {

		while (exp.contains(String.valueOf(MULTI)) || exp.contains(String.valueOf(DIV))) {

			System.out.println("Exp " + exp);

			// Loop Right to Left
			for (int i = 0; i < exp.length(); i++) {
				char c = exp.charAt(i);
				if (c == MULTI || c == DIV) {

					int pVL = i, pVR = i;
					String vL = "", vR = "";
					// First Left
					while (pVL > 0) {
						pVL--;
						char cVL = exp.charAt(pVL);
						// If it's not un operande then its a value
						if (!listOperators.contains(cVL)) {
							vL = cVL + vL;
						} else
							break;
					}

					// Second Right
					boolean foundV = false;
					while (pVR < exp.length() - 1) {
						pVR++;
						char cVR = exp.charAt(pVR);

						// if found a operator and values was found
						if (listOperators.contains(cVR) && foundV) {
							break;
						}
						// if found a operator and no value found
						else if (listOperators.contains(cVR) && !foundV) {
							vR += cVR;
						}
						// if found a value
						else if (!listOperators.contains(cVR)) {
							vR += cVR;
							foundV = true;
						} else
							break;
					}
					// String evaluate the expression;
					String value = operate(vL, c, vR);
					String subExp = vL + (c == MULTI ? "\\" + c : c) + vR;

					System.out.println("Prior 1 = " + subExp + " " + value);

					exp = exp.replaceAll(subExp, value);
					break;
				}
			}
		}
		return exp;
	}

	/**
	 * While the expression contains ADD(+) or SUB(-)
	 * 
	 * @param exp
	 * @return the expression without any ADD nor SUB
	 */

	private String calculatePriority2(String exp) {

		if (exp.endsWith("-") || exp.endsWith("+")) {
			exp = exp + "0";
		}

		while (exp.contains(String.valueOf(ADD)) || exp.contains(String.valueOf(SUB))) {

			System.out.println("Exp = " + exp);

			// Loop Right to Left
			for (int i = exp.length() - 1; i > 0; i--) {
				char c = exp.charAt(i);

				boolean isBreak = false;
				
				if (c == ADD || c == SUB) {

					int pVL = i, pVR = i;
					String vL = "", vR = "";

					// First Left
					boolean foundV = false;
					while (pVL > 0) {
						pVL--;

						char cVL = exp.charAt(pVL);

						// if found a operator and values was no found
						if (listOperators.contains(cVL) && !foundV) {
							if(c == SUB) {
								exp = exp.substring(0, pVL)+exp.substring(pVL+1, exp.length());
								isBreak = true;
							}
							break;
							
						}
						// if found an operator and value found
						else if (listOperators.contains(cVL) && foundV) {
							vL = cVL + vL;
							break;
						}
						// if found a value
						else if (!listOperators.contains(cVL)) {
							vL = cVL + vL;
							foundV = true;
						} else
							break;
					}
					if(isBreak)
						break;
					// Second Right

					while (pVR < exp.length() - 1) {
						pVR++;

						char cVR = exp.charAt(pVR);
						// If it's not un operande then its a value
						if (!listOperators.contains(cVR)) {
							vR = vR + cVR;
						} else
							break;

					}
					// String evaluate the expression;
					
					System.out.println("Decoded "+vL+" "+c+" "+ vR);
					
					String value = operate(vL, c, vR);
					String subExp = vL + "\\" + c + vR;
					if (subExp.startsWith("+")) {
						subExp = subExp.substring(1);
					}
					exp = exp.replaceAll(subExp, value);
					break;
				}
			}

			try {
				Integer.parseInt(exp);
				break;
			} catch (Exception e) {
			}
		}
		return exp;
	}

	private String operate(String firstOp, char operat, String secondOp) {
		try {

			firstOp = getFactIfSo(firstOp);

			firstOp = getValueIfConst(firstOp, PI, Math.PI);
			firstOp = getValueIfConst(firstOp, E, Math.E);

			secondOp = getFactIfSo(secondOp);
			secondOp = getValueIfConst(secondOp, PI, Math.PI);
			secondOp = getValueIfConst(secondOp, E, Math.E);

			double a = Double.parseDouble(firstOp == null || firstOp.length() == 0 ? "0" : firstOp);
			double b = Double.parseDouble(secondOp == null || secondOp.length() == 0 ? "0" : secondOp);
			double res = 0;

			switch (operat) {
			case ADD:
				res = a + b;
				break;
			case SUB:
				res = a - b;
				break;
			case MULTI:
				res = a * b;
				break;
			case DIV:
				res = a / b;
				break;
			case POW:
				res = Math.pow(a, b);
				break;
			case ROOT:
				res = Math.pow(b, 1 / a);
				break;
			}
			// Convert to Int if its
			if ((long) res == res) {
				String result = String.valueOf((long) res);
				return result;
			} else {
				String result = String.valueOf(res);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * A function that check if a string contains a factorial expression an evaluate
	 * e.g 5! = 120, !5 = 120, 5!2 = (5!)*2= 240
	 * @param exp
	 * @return the factorial value
	 */
	private String getFactIfSo(String exp) {

		if (exp.contains(FACT)) {

			String[] nums = exp.split(FACT);
			
			if(nums == null || nums.length == 0) {
				return "";
			}
			
			if(nums.length == 1) {
				long n = Long.parseLong(nums[0]);
				long f = n;
				while (n > 1) {
					n--;
					f *= n;
				}
				return String.valueOf(f);
			}

			String num0 = nums[0];
			String num1 = nums[1];
			long f = 0;
			if(!Utils.isTxtEmpty(num0)) {
				long n = Long.parseLong(num0);
				f = n;
				while (n > 1) {
					n--;
					f *= n;
				}
				if(!Utils.isTxtEmpty(num1))
					f*= Double.parseDouble(num1);
			}
			else {
				long n = Long.parseLong(num1);
				f = n;
				while (n > 1) {
					n--;
					f *= n;
				}
			}
			return String.valueOf(f);
		} else
			return exp;
	}

	private String getValueIfConst(String exp, String cons, double mathCons) {
		exp = exp.toLowerCase();
		if (!exp.contains(cons))
			return exp;

		String[] nums = exp.split(cons);

		if (nums == null || nums.length == 0) {
			return String.valueOf(mathCons);
		}
		double value = 1;
		for (String num : nums) {
			if (!Utils.isTxtEmpty(num))
				value *= Double.parseDouble(num);
		}
		value *= mathCons;

		return String.valueOf(value);
	}

	/**
	 * Check is an expression is correct by check if all paranthesis opened is
	 * closed
	 * 
	 * @param exp
	 * @return boolean
	 */
	private boolean checkCorrect(String exp) {
		if (exp.contains("(") || exp.contains(")")) {

			Stack<Character> pars = new Stack<Character>();
			for (char c : exp.toCharArray()) {
				if (c == '(') {
					pars.add(c);
				} else if (c == ')') {

					if (pars.isEmpty() || pars.peek() != '(') {
						return false;
					} else if (pars.peek() == '(') {
						pars.pop();
					}
				}
			}
			return pars.isEmpty();
		} else
			return true;
	}

	public static void main(String[] args) {

		Bkalculator cal = Bkalculator.getCalculator();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Enter a expression");
			cal.evaluate(sc.nextLine(), (exp, res, time) -> {
				System.out.println(exp + " = " + res);
				System.out.println("Time executed " + time);
			});
		}
	}

}
