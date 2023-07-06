package com.ridsys.rib.comm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class InvoiceNumberGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Serializable result = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = session.connection();
			statement = connection.createStatement();
			try {
				resultSet = statement.executeQuery(
						"SELECT MAX(invoice_no) AS invoice_no FROM invoice WHERE IF(MONTH(NOW())<'4',YEAR(creationdate)>=YEAR(NOW())-1 AND (YEAR(creationdate)<=YEAR(NOW()) AND MONTH(creationdate)<'4'),(YEAR(creationdate)>=YEAR(NOW()) AND MONTH(creationdate)>='4'))");
			} catch (Exception ex) {
				System.out.println("GEN_VAL_EX:" + ex.getMessage());
			}

			if (resultSet.next()) {
				result = resultSet.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
//	@Override
//	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//		Serializable result = null;
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try {
////			String prefix = "";
//
////			DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyyMM");
//
////			LocalDate ldObj = LocalDate.now();
////			String yyyymm = newPattern.format(ldObj).toString();
////			prefix = "R-NET";
//			connection = session.connection();
//			statement = connection.createStatement();
//
//			try {
//				resultSet = statement.executeQuery(
//						"SELECT MAX(invoice_no) AS invoice_no FROM invoice WHERE IF(MONTH(NOW())<'4',YEAR(creationdate)>=YEAR(NOW())-1 AND (YEAR(creationdate)<=YEAR(NOW()) AND MONTH(creationdate)<'4'),(YEAR(creationdate)>=YEAR(NOW()) AND MONTH(creationdate)>='4'))");
//			} catch (Exception ex) {
//				System.out.println("EX:" + ex.getMessage());
//			}
//
//			if (resultSet.next()) {
////				String suffix = resultSet.getString(1);
////				String suffix = String.valueOf(nextValue + 1);// String.format("%05d", nextValue);
//				result = resultSet.getInt(1) + 1;// prefix.concat(suffix);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
