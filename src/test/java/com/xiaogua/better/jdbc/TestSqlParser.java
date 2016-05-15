package com.xiaogua.better.jdbc;

import org.junit.Test;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

public class TestSqlParser {
	@Test
	public void testAddWhereCondition() throws Exception {
		Update update = (Update) CCJSqlParserUtil.parse("update user set name='test_1'");
		Expression cdt = update.getWhere();
		if (cdt == null) {
			EqualsTo equalsTo = new EqualsTo();
			equalsTo.setLeftExpression(new Column("1"));
			equalsTo.setRightExpression(new LongValue(2));
			update.setWhere(equalsTo);
		}
		System.out.println(update);
	}

	@Test
	public void testAddNotNullCondition() throws Exception {
		Select select = (Select) CCJSqlParserUtil.parse("select name from user where id = 1");
		PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
		Expression where = plainSelect.getWhere();
		IsNullExpression isNullExpression = new IsNullExpression();
		isNullExpression.setLeftExpression(new Column("name"));
		isNullExpression.setNot(true);
		AndExpression and = new AndExpression(isNullExpression,where);
		plainSelect.setWhere(and);
		System.out.println(select);
	}
	
}
