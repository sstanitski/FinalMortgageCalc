package MainView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import MainApp.MainClass;

public class CalculatorControl extends MainClass{
	
	@FXML
	private Label housePayment;
	@FXML
	private Label housePaymentPlus;
	@FXML
	private Label maxPayment;
	@FXML
	private Label amountFinanced;
	@FXML
	private TextField grossIncome;
	@FXML
	private TextField monthlyDebt;
	@FXML
	private TextField interestRate;
	@FXML
	private TextField downPayment;
	@FXML
	private ComboBox<Integer> mortgageTerm;
	
	double numberOfPayments = mortgageTerm.getValue();

	public double interest(){
		double interest = (Double.parseDouble(interestRate.getText()));
		return ((interest /100) / 12);
	}
	public void loadTerms(){
		ObservableList<Integer> length = FXCollections.observableArrayList(10, 15, 30);
		mortgageTerm.getItems().addAll(length);
	}
	public double housingPaymentCalc(){
		double income = (Double.parseDouble(grossIncome.getText()));
		return (income / 12 * 0.18);
	}
	public double housingPaymentPlusCalc(){
		double income = (Double.parseDouble(grossIncome.getText()));
		return ((income / 12 * 0.36) - Double.parseDouble(monthlyDebt.getText()));
	}
	public double maxPaymentAllowed(){
		if (housingPaymentCalc() > housingPaymentPlusCalc()){
			return housingPaymentPlusCalc();
		}else{
			return housingPaymentCalc();
		}
	}
	public double mortgageTermFinance(){
		return (maxPaymentAllowed() *(1+Math.pow(interest(), numberOfPayments)-1) / (interest() * Math.pow(1+interest(), numberOfPayments)));
	}
	
}
