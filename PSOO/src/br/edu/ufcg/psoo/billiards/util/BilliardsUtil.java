package br.edu.ufcg.psoo.billiards.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

public class BilliardsUtil {
	/***************************************************************************
	 * Shared methods ******************
	 * 
	 * 
	 * /**
	 * 
	 * @param a
	 * @return
	 */
	public static String upper(String a) {
		return a.substring(0, 1).toUpperCase() + a.substring(1);
	}

	/**
	 * 
	 * @param clz
	 * @param attribute
	 * @param ob
	 * @return
	 * @throws Exception
	 */
	public Object getField(Class clz, String attribute, Object ob)
			throws Exception {
		Field field = clz.getDeclaredField(attribute);
		if (field.getModifiers() == Field.PUBLIC + Field.DECLARED) {
			return field.get(ob);
		}
		String methodName = "get" + upper(field.getName());
		Method method = ob.getClass().getMethod(methodName);
		return method.invoke(ob);
	}

	/**
	 * 
	 * @param clz
	 * @param attribute
	 * @param ob
	 * @param args
	 * @throws Exception
	 */
	public void setField(Class clz, String attribute, Object ob, Object args)
			throws Exception {
		Field field = clz.getDeclaredField(attribute);
		if (field.getModifiers() == Field.PUBLIC + Field.DECLARED) {
			field.set(ob, args);
			return;
		}
		String methodName = "set" + upper(field.getName());
		ob.getClass().getMethod(methodName, args.getClass()).invoke(ob, args);
	}
	
	/**
	 * 
	 * @param sDate
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public boolean validateDate(String sDate, DateFormat dateFormat, Date date) {
		return dateFormat.format(date).equals(sDate);
	}
	
	/**
	 * 
	 * @param expression
	 * @param vars
	 * @return
	 * @throws Exception
	 */
	public String evaluateExpression(String expression,
			Map<String, Integer> vars) throws Exception {
		Interpreter interpreter = new Interpreter();
		for (String s : vars.keySet()) {
			try {
				interpreter.set(s, vars.get(s));
			} catch (EvalError e) {
				throw new Exception("Syntax error in standings expression");
			}
		}
		try {
			Object ret = interpreter.eval(expression).toString();
			if (ret==null) {
				throw new Exception("Unknown variable in standings expression");
			}
			return ret.toString();
		} catch (Exception e) {
			if (e.getMessage()==null||e.getMessage().contains("illegal use of undefined variable")) {
				throw new Exception("Unknown variable in standings expression");
			} else if (e.getMessage().contains("Arithemetic Exception")) {
				throw new Exception("Division by zero in standings expression");	
			}else
				throw new Exception("Syntax error in standings expression");
		}
	}
}
