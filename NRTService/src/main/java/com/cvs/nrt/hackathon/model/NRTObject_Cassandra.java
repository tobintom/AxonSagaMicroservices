package com.cvs.nrt.hackathon.model;

import java.util.ArrayList;
import java.util.UUID; 

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table; 

@Table(value = "trxclaim_claims")
public class NRTObject_Cassandra { 
	@PrimaryKey
	private UUID id;
	String RxClmNum;
	String claimtype;
	String TCD_Claim_Seq;
	String TCD_Claim_Status;
	String TCD_Date_Submitted;
	String TCD_Sbm_Date_of_Service;
	String TCD_Final_Plan_Cde;
	String TCD_Carrier_ID;
	String TCD_Account_ID;
	String TCD_Group_ID;
	String TCD_Member_ID;
	String TCD_Sbm_Days_Supply;
	String TCD_Sbm_Fill_Number;
	String TCD_Generic_Ind_MedSpan;
	String TCD_Claim_Origination_Flg;
	String TCD_Sbm_Rx_Nbr;
	String TCD_Mbr_Prior_Auth_Nbr;
	String TCD_Sbm_Bin_Nbr;
	String TCD_Sbm_Processor_Ctl_Nbr;
	String TCD_Sbm_Compound_Code;
	String TCD_Sbm_Group_Nbr;
	String TCD_Sbm_Prescriber_ID;
	String TCD_Sbm_Product_ID;
	String TCD_Sbm_Product_Name;
	String TCD_Sbm_Product_Sel_Code;
	String TCD_Sbm_Qty_Dispensed;
	String TCD_Sbm_Srv_Prov_ID;
	String TCD_CMS_Contract_Id;
	String TCD_COB_Claim_Indicator;
	String TCD_Sbm_PA_Number;
	String TCD_Prior_Auth_Reason_Cde;
	String TCD_Product_Key;
	String TCD_Reimbursement_Flag;
	String TCD_Rx_Network_ID;
	String ADD_Time;
	String CHG_Date;
	String CHG_Time;
	String CAR_Carrier_Name;
	String MBR_Date_of_Birth;
	String MBR_First_Name;
	String MBR_Last_Name;
	String MBR_Multiple_Birth_Code;
	String MBR_Sex;
	String Fam_Benefit_Amount;
	String Mbe_Benefit_Amount;
	String MPA_NDC_GPI_Code;
	String MPA_Use_Plan_Drug_Sts;
	String PA_Effective_Date;
	String PA_Term_date;	
	String NPI_ID;
	String PHA_Dispenser_Type;
	String PHA_Pharmacy_Address_1;
	String PHA_Pharmacy_City;
	String PHA_Pharmacy_Name_Full;
	String PHA_Pharmacy_NCPDP_Nbr;
	String PHA_Pharmacy_Phone;
	String PHA_Pharmacy_State;
	String PHA_Pharmacy_Zip;
	String NPI_Nbr;
	String Physician_First_Name;
	String Physician_Last_Name;
	String OPD_Fam_OOP_Max_Amount;
	String OPD_Mbr_OOP_Max_Amount;
	String PD3_Rbl_Copay_Prcnt_Amt;
	String PDT_App_Flat_Sls_Tax_Amt;
	String PDT_Fam_Accu_Deduct_PTD;
	String PDT_Ind_Accu_Deduct_PTD;
	String PDT_Rbl_Amt_Appl_Per_Dedu;
	String PDT_Rbl_Copay_Amount;
	String PDT_Rbl_Dispensing_Fee;
	String PDT_Rbl_Ingredient_Cost;
	String PDT_Rbl_Patient_Pay_Amt;
	String PDT_Rbl_Total_Amount;
	String PDT_Remaining_OOP_Max_Amt;
	String Tier;
	String clientCode;
	 
	ArrayList<String> CLM_MSG = new ArrayList<String>(); 
	ArrayList<String> rejectCodes = new ArrayList<String>(); 
	ArrayList<String> settlementCodes = new ArrayList<String>();
		 
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ArrayList<String> getRejectCodes() {
		return rejectCodes;
	}
	
	public void addSettlementCode(String c) {
		this.settlementCodes.add(c);
	}
	
	public void addRejectCode(String c) {
		this.rejectCodes.add(c);
	}
	
	public void addCLM_MSG(String c) {
		this.CLM_MSG.add(c);
	}

	public void setRejectCodes(ArrayList<String> rejectCodes) {
		this.rejectCodes = rejectCodes;
	}

	public ArrayList<String> getSettlementCodes() {
		return settlementCodes;
	}

	public void setSettlementCodes(ArrayList<String> settlementCodes) {
		this.settlementCodes = settlementCodes;
	}

	public String getRxClmNum() {
		return RxClmNum;
	}

	public void setRxClmNum(String rxClmNum) {
		RxClmNum = rxClmNum;
	}

	public String getTCD_Claim_Seq() {
		return TCD_Claim_Seq;
	}

	public void setTCD_Claim_Seq(String tCD_Claim_Seq) {
		TCD_Claim_Seq = tCD_Claim_Seq;
	}

	public String getTCD_Claim_Status() {
		return TCD_Claim_Status;
	}

	public void setTCD_Claim_Status(String tCD_Claim_Status) {
		TCD_Claim_Status = tCD_Claim_Status;
	}

	public String getTCD_Date_Submitted() {
		return TCD_Date_Submitted;
	}

	public void setTCD_Date_Submitted(String tCD_Date_Submitted) {
		TCD_Date_Submitted = tCD_Date_Submitted;
	}

	public String getTCD_Sbm_Date_of_Service() {
		return TCD_Sbm_Date_of_Service;
	}

	public void setTCD_Sbm_Date_of_Service(String tCD_Sbm_Date_of_Service) {
		TCD_Sbm_Date_of_Service = tCD_Sbm_Date_of_Service;
	}

	public String getTCD_Final_Plan_Cde() {
		return TCD_Final_Plan_Cde;
	}

	public void setTCD_Final_Plan_Cde(String tCD_Final_Plan_Cde) {
		TCD_Final_Plan_Cde = tCD_Final_Plan_Cde;
	}

	public String getTCD_Carrier_ID() {
		return TCD_Carrier_ID;
	}

	public void setTCD_Carrier_ID(String tCD_Carrier_ID) {
		TCD_Carrier_ID = tCD_Carrier_ID;
	}

	public String getTCD_Account_ID() {
		return TCD_Account_ID;
	}

	public void setTCD_Account_ID(String tCD_Account_ID) {
		TCD_Account_ID = tCD_Account_ID;
	}

	public String getTCD_Group_ID() {
		return TCD_Group_ID;
	}

	public void setTCD_Group_ID(String tCD_Group_ID) {
		TCD_Group_ID = tCD_Group_ID;
	}

	public String getTCD_Member_ID() {
		return TCD_Member_ID;
	}

	public void setTCD_Member_ID(String tCD_Member_ID) {
		TCD_Member_ID = tCD_Member_ID;
	}

	public String getTCD_Sbm_Days_Supply() {
		return TCD_Sbm_Days_Supply;
	}
	
	public double getTCD_Sbm_Days_SupplyDouble() {
		return Double.valueOf(TCD_Sbm_Days_Supply);
	}

	public void setTCD_Sbm_Days_Supply(String tCD_Sbm_Days_Supply) {
		TCD_Sbm_Days_Supply = tCD_Sbm_Days_Supply;
	}

	public String getTCD_Sbm_Fill_Number() {
		return TCD_Sbm_Fill_Number;
	}

	public void setTCD_Sbm_Fill_Number(String tCD_Sbm_Fill_Number) {
		TCD_Sbm_Fill_Number = tCD_Sbm_Fill_Number;
	}

	public String getTCD_Generic_Ind_MedSpan() {
		return TCD_Generic_Ind_MedSpan;
	}

	public void setTCD_Generic_Ind_MedSpan(String tCD_Generic_Ind_MedSpan) {
		TCD_Generic_Ind_MedSpan = tCD_Generic_Ind_MedSpan;
	}

	public String getTCD_Claim_Origination_Flg() {
		return TCD_Claim_Origination_Flg;
	}

	public void setTCD_Claim_Origination_Flg(String tCD_Claim_Origination_Flg) {
		TCD_Claim_Origination_Flg = tCD_Claim_Origination_Flg;
	}

	public String getTCD_Sbm_Rx_Nbr() {
		return TCD_Sbm_Rx_Nbr;
	}

	public void setTCD_Sbm_Rx_Nbr(String tCD_Sbm_Rx_Nbr) {
		TCD_Sbm_Rx_Nbr = tCD_Sbm_Rx_Nbr;
	}

	public String getTCD_Mbr_Prior_Auth_Nbr() {
		return TCD_Mbr_Prior_Auth_Nbr;
	}

	public void setTCD_Mbr_Prior_Auth_Nbr(String tCD_Mbr_Prior_Auth_Nbr) {
		TCD_Mbr_Prior_Auth_Nbr = tCD_Mbr_Prior_Auth_Nbr;
	}

	public String getTCD_Sbm_Bin_Nbr() {
		return TCD_Sbm_Bin_Nbr;
	}

	public void setTCD_Sbm_Bin_Nbr(String tCD_Sbm_Bin_Nbr) {
		TCD_Sbm_Bin_Nbr = tCD_Sbm_Bin_Nbr;
	}

	public String getTCD_Sbm_Processor_Ctl_Nbr() {
		return TCD_Sbm_Processor_Ctl_Nbr;
	}

	public void setTCD_Sbm_Processor_Ctl_Nbr(String tCD_Sbm_Processor_Ctl_Nbr) {
		TCD_Sbm_Processor_Ctl_Nbr = tCD_Sbm_Processor_Ctl_Nbr;
	}

	public String getTCD_Sbm_Compound_Code() {
		return TCD_Sbm_Compound_Code;
	}

	public void setTCD_Sbm_Compound_Code(String tCD_Sbm_Compound_Code) {
		TCD_Sbm_Compound_Code = tCD_Sbm_Compound_Code;
	}

	public String getTCD_Sbm_Group_Nbr() {
		return TCD_Sbm_Group_Nbr;
	}

	public void setTCD_Sbm_Group_Nbr(String tCD_Sbm_Group_Nbr) {
		TCD_Sbm_Group_Nbr = tCD_Sbm_Group_Nbr;
	}

	public String getTCD_Sbm_Prescriber_ID() {
		return TCD_Sbm_Prescriber_ID;
	}

	public void setTCD_Sbm_Prescriber_ID(String tCD_Sbm_Prescriber_ID) {
		TCD_Sbm_Prescriber_ID = tCD_Sbm_Prescriber_ID;
	}

	public String getTCD_Sbm_Product_ID() {
		return TCD_Sbm_Product_ID;
	}

	public void setTCD_Sbm_Product_ID(String tCD_Sbm_Product_ID) {
		TCD_Sbm_Product_ID = tCD_Sbm_Product_ID;
	}

	public String getTCD_Sbm_Product_Sel_Code() {
		return TCD_Sbm_Product_Sel_Code;
	}

	public void setTCD_Sbm_Product_Sel_Code(String tCD_Sbm_Product_Sel_Code) {
		TCD_Sbm_Product_Sel_Code = tCD_Sbm_Product_Sel_Code;
	}

	public String getTCD_Sbm_Qty_Dispensed() {
		return TCD_Sbm_Qty_Dispensed;
	}
	
	public double getTCD_Sbm_Qty_DispensedDouble() {
		return Double.valueOf(TCD_Sbm_Qty_Dispensed);
	}

	public void setTCD_Sbm_Qty_Dispensed(String tCD_Sbm_Qty_Dispensed) {
		TCD_Sbm_Qty_Dispensed = tCD_Sbm_Qty_Dispensed;
	}

	public String getTCD_Sbm_Srv_Prov_ID() {
		return TCD_Sbm_Srv_Prov_ID;
	}

	public void setTCD_Sbm_Srv_Prov_ID(String tCD_Sbm_Srv_Prov_ID) {
		TCD_Sbm_Srv_Prov_ID = tCD_Sbm_Srv_Prov_ID;
	}

	public String getTCD_CMS_Contract_Id() {
		return TCD_CMS_Contract_Id;
	}

	public void setTCD_CMS_Contract_Id(String tCD_CMS_Contract_Id) {
		TCD_CMS_Contract_Id = tCD_CMS_Contract_Id;
	}

	public String getTCD_COB_Claim_Indicator() {
		return TCD_COB_Claim_Indicator;
	}

	public void setTCD_COB_Claim_Indicator(String tCD_COB_Claim_Indicator) {
		TCD_COB_Claim_Indicator = tCD_COB_Claim_Indicator;
	}

	public String getTCD_Sbm_PA_Number() {
		return TCD_Sbm_PA_Number;
	}

	public void setTCD_Sbm_PA_Number(String tCD_Sbm_PA_Number) {
		TCD_Sbm_PA_Number = tCD_Sbm_PA_Number;
	}

	public String getTCD_Prior_Auth_Reason_Cde() {
		return TCD_Prior_Auth_Reason_Cde;
	}

	public void setTCD_Prior_Auth_Reason_Cde(String tCD_Prior_Auth_Reason_Cde) {
		TCD_Prior_Auth_Reason_Cde = tCD_Prior_Auth_Reason_Cde;
	}

	public String getTCD_Product_Key() {
		return TCD_Product_Key;
	}

	public void setTCD_Product_Key(String tCD_Product_Key) {
		TCD_Product_Key = tCD_Product_Key;
	}

	public String getTCD_Reimbursement_Flag() {
		return TCD_Reimbursement_Flag;
	}

	public void setTCD_Reimbursement_Flag(String tCD_Reimbursement_Flag) {
		TCD_Reimbursement_Flag = tCD_Reimbursement_Flag;
	}

	public String getTCD_Rx_Network_ID() {
		return TCD_Rx_Network_ID;
	}

	public void setTCD_Rx_Network_ID(String tCD_Rx_Network_ID) {
		TCD_Rx_Network_ID = tCD_Rx_Network_ID;
	}

	public String getADD_Time() {
		return ADD_Time;
	}

	public void setADD_Time(String aDD_Time) {
		ADD_Time = aDD_Time;
	}

	public String getCHG_Date() {
		return CHG_Date;
	}

	public void setCHG_Date(String cHG_Date) {
		CHG_Date = cHG_Date;
	}

	public String getCHG_Time() {
		return CHG_Time;
	}

	public void setCHG_Time(String cHG_Time) {
		CHG_Time = cHG_Time;
	}

	public String getCAR_Carrier_Name() {
		return CAR_Carrier_Name;
	}

	public void setCAR_Carrier_Name(String cAR_Carrier_Name) {
		CAR_Carrier_Name = cAR_Carrier_Name;
	}

	public String getMBR_Date_of_Birth() {
		return MBR_Date_of_Birth;
	}

	public void setMBR_Date_of_Birth(String mBR_Date_of_Birth) {
		MBR_Date_of_Birth = mBR_Date_of_Birth;
	}

	public String getMBR_First_Name() {
		return MBR_First_Name;
	}

	public void setMBR_First_Name(String mBR_First_Name) {
		MBR_First_Name = mBR_First_Name;
	}

	public String getMBR_Last_Name() {
		return MBR_Last_Name;
	}

	public void setMBR_Last_Name(String mBR_Last_Name) {
		MBR_Last_Name = mBR_Last_Name;
	}

	public String getMBR_Multiple_Birth_Code() {
		return MBR_Multiple_Birth_Code;
	}

	public void setMBR_Multiple_Birth_Code(String mBR_Multiple_Birth_Code) {
		MBR_Multiple_Birth_Code = mBR_Multiple_Birth_Code;
	}

	public String getMBR_Sex() {
		return MBR_Sex;
	}

	public void setMBR_Sex(String mBR_Sex) {
		MBR_Sex = mBR_Sex;
	}

	public String getFam_Benefit_Amount() {
		return Fam_Benefit_Amount;
	}

	public void setFam_Benefit_Amount(String fam_Benefit_Amount) {
		Fam_Benefit_Amount = fam_Benefit_Amount;
	}

	public String getMbe_Benefit_Amount() {
		return Mbe_Benefit_Amount;
	}

	public void setMbe_Benefit_Amount(String mbe_Benefit_Amount) {
		Mbe_Benefit_Amount = mbe_Benefit_Amount;
	}

	public String getMPA_NDC_GPI_Code() {
		return MPA_NDC_GPI_Code;
	}

	public void setMPA_NDC_GPI_Code(String mPA_NDC_GPI_Code) {
		MPA_NDC_GPI_Code = mPA_NDC_GPI_Code;
	}

	public String getMPA_Use_Plan_Drug_Sts() {
		return MPA_Use_Plan_Drug_Sts;
	}

	public void setMPA_Use_Plan_Drug_Sts(String mPA_Use_Plan_Drug_Sts) {
		MPA_Use_Plan_Drug_Sts = mPA_Use_Plan_Drug_Sts;
	}

	public String getPA_Effective_Date() {
		return PA_Effective_Date;
	}

	public void setPA_Effective_Date(String pA_Effective_Date) {
		PA_Effective_Date = pA_Effective_Date;
	}

	public String getPA_Term_date() {
		return PA_Term_date;
	}

	public void setPA_Term_date(String pA_Term_date) {
		PA_Term_date = pA_Term_date;
	}

	public ArrayList<String> getCLM_MSG() {
		return CLM_MSG;
	}

	public void setCLM_MSG(ArrayList<String> cLM_MSG) {
		CLM_MSG = cLM_MSG;
	}

	public String getNPI_ID() {
		return NPI_ID;
	}

	public void setNPI_ID(String nPI_ID) {
		NPI_ID = nPI_ID;
	}

	public String getPHA_Dispenser_Type() {
		return PHA_Dispenser_Type;
	}

	public void setPHA_Dispenser_Type(String pHA_Dispenser_Type) {
		PHA_Dispenser_Type = pHA_Dispenser_Type;
	}

	public String getPHA_Pharmacy_Address_1() {
		return PHA_Pharmacy_Address_1;
	}

	public void setPHA_Pharmacy_Address_1(String pHA_Pharmacy_Address_1) {
		PHA_Pharmacy_Address_1 = pHA_Pharmacy_Address_1;
	}

	public String getPHA_Pharmacy_City() {
		return PHA_Pharmacy_City;
	}

	public void setPHA_Pharmacy_City(String pHA_Pharmacy_City) {
		PHA_Pharmacy_City = pHA_Pharmacy_City;
	}

	public String getPHA_Pharmacy_Name_Full() {
		return PHA_Pharmacy_Name_Full;
	}

	public void setPHA_Pharmacy_Name_Full(String pHA_Pharmacy_Name_Full) {
		PHA_Pharmacy_Name_Full = pHA_Pharmacy_Name_Full;
	}

	public String getPHA_Pharmacy_NCPDP_Nbr() {
		return PHA_Pharmacy_NCPDP_Nbr;
	}

	public void setPHA_Pharmacy_NCPDP_Nbr(String pHA_Pharmacy_NCPDP_Nbr) {
		PHA_Pharmacy_NCPDP_Nbr = pHA_Pharmacy_NCPDP_Nbr;
	}

	public String getPHA_Pharmacy_Phone() {
		return PHA_Pharmacy_Phone;
	}

	public void setPHA_Pharmacy_Phone(String pHA_Pharmacy_Phone) {
		PHA_Pharmacy_Phone = pHA_Pharmacy_Phone;
	}

	public String getPHA_Pharmacy_State() {
		return PHA_Pharmacy_State;
	}

	public void setPHA_Pharmacy_State(String pHA_Pharmacy_State) {
		PHA_Pharmacy_State = pHA_Pharmacy_State;
	}

	public String getPHA_Pharmacy_Zip() {
		return PHA_Pharmacy_Zip;
	}

	public void setPHA_Pharmacy_Zip(String pHA_Pharmacy_Zip) {
		PHA_Pharmacy_Zip = pHA_Pharmacy_Zip;
	}

	public String getNPI_Nbr() {
		return NPI_Nbr;
	}

	public void setNPI_Nbr(String nPI_Nbr) {
		NPI_Nbr = nPI_Nbr;
	}

	public String getPhysician_First_Name() {
		return Physician_First_Name;
	}

	public void setPhysician_First_Name(String physician_First_Name) {
		Physician_First_Name = physician_First_Name;
	}

	public String getPhysician_Last_Name() {
		return Physician_Last_Name;
	}

	public void setPhysician_Last_Name(String physician_Last_Name) {
		Physician_Last_Name = physician_Last_Name;
	}

	public String getOPD_Fam_OOP_Max_Amount() {
		return OPD_Fam_OOP_Max_Amount;
	}

	public void setOPD_Fam_OOP_Max_Amount(String oPD_Fam_OOP_Max_Amount) {
		OPD_Fam_OOP_Max_Amount = oPD_Fam_OOP_Max_Amount;
	}

	public String getOPD_Mbr_OOP_Max_Amount() {
		return OPD_Mbr_OOP_Max_Amount;
	}

	public void setOPD_Mbr_OOP_Max_Amount(String oPD_Mbr_OOP_Max_Amount) {
		OPD_Mbr_OOP_Max_Amount = oPD_Mbr_OOP_Max_Amount;
	}

	public String getPD3_Rbl_Copay_Prcnt_Amt() {
		return PD3_Rbl_Copay_Prcnt_Amt;
	}

	public void setPD3_Rbl_Copay_Prcnt_Amt(String pD3_Rbl_Copay_Prcnt_Amt) {
		PD3_Rbl_Copay_Prcnt_Amt = pD3_Rbl_Copay_Prcnt_Amt;
	}

	public String getPDT_App_Flat_Sls_Tax_Amt() {
		return PDT_App_Flat_Sls_Tax_Amt;
	}

	public void setPDT_App_Flat_Sls_Tax_Amt(String pDT_App_Flat_Sls_Tax_Amt) {
		PDT_App_Flat_Sls_Tax_Amt = pDT_App_Flat_Sls_Tax_Amt;
	}

	public String getPDT_Fam_Accu_Deduct_PTD() {
		return PDT_Fam_Accu_Deduct_PTD;
	}

	public void setPDT_Fam_Accu_Deduct_PTD(String pDT_Fam_Accu_Deduct_PTD) {
		PDT_Fam_Accu_Deduct_PTD = pDT_Fam_Accu_Deduct_PTD;
	}

	public String getPDT_Ind_Accu_Deduct_PTD() {
		return PDT_Ind_Accu_Deduct_PTD;
	}

	public void setPDT_Ind_Accu_Deduct_PTD(String pDT_Ind_Accu_Deduct_PTD) {
		PDT_Ind_Accu_Deduct_PTD = pDT_Ind_Accu_Deduct_PTD;
	}

	public String getPDT_Rbl_Amt_Appl_Per_Dedu() {
		return PDT_Rbl_Amt_Appl_Per_Dedu;
	}

	public void setPDT_Rbl_Amt_Appl_Per_Dedu(String pDT_Rbl_Amt_Appl_Per_Dedu) {
		PDT_Rbl_Amt_Appl_Per_Dedu = pDT_Rbl_Amt_Appl_Per_Dedu;
	}

	public String getPDT_Rbl_Copay_Amount() {
		return PDT_Rbl_Copay_Amount;
	}

	public void setPDT_Rbl_Copay_Amount(String pDT_Rbl_Copay_Amount) {
		PDT_Rbl_Copay_Amount = pDT_Rbl_Copay_Amount;
	}

	public String getPDT_Rbl_Dispensing_Fee() {
		return PDT_Rbl_Dispensing_Fee;
	}

	public void setPDT_Rbl_Dispensing_Fee(String pDT_Rbl_Dispensing_Fee) {
		PDT_Rbl_Dispensing_Fee = pDT_Rbl_Dispensing_Fee;
	}

	public String getPDT_Rbl_Ingredient_Cost() {
		return PDT_Rbl_Ingredient_Cost;
	}

	public void setPDT_Rbl_Ingredient_Cost(String pDT_Rbl_Ingredient_Cost) {
		PDT_Rbl_Ingredient_Cost = pDT_Rbl_Ingredient_Cost;
	}

	public String getPDT_Rbl_Patient_Pay_Amt() {
		return PDT_Rbl_Patient_Pay_Amt;
	}
	
	public double getPDT_Rbl_Patient_Pay_AmtDouble() {
		return Double.valueOf(PDT_Rbl_Patient_Pay_Amt);
	}

	public void setPDT_Rbl_Patient_Pay_Amt(String pDT_Rbl_Patient_Pay_Amt) {
		PDT_Rbl_Patient_Pay_Amt = pDT_Rbl_Patient_Pay_Amt;
	}

	public String getPDT_Rbl_Total_Amount() {
		return PDT_Rbl_Total_Amount;
	}
	
	public double getPDT_Rbl_Total_AmountDuouble() {
		return Double.valueOf(PDT_Rbl_Total_Amount);
	}

	public void setPDT_Rbl_Total_Amount(String pDT_Rbl_Total_Amount) {
		PDT_Rbl_Total_Amount = pDT_Rbl_Total_Amount;
	}

	public String getPDT_Remaining_OOP_Max_Amt() {
		return PDT_Remaining_OOP_Max_Amt;
	}

	public void setPDT_Remaining_OOP_Max_Amt(String pDT_Remaining_OOP_Max_Amt) {
		PDT_Remaining_OOP_Max_Amt = pDT_Remaining_OOP_Max_Amt;
	}

	public String getTier() {
		return Tier;
	}

	public void setTier(String tier) {
		Tier = tier;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClaimType() {
		return claimtype;
	}

	public void setClaimType(String claimType) {
		this.claimtype = claimType;
	}

	public String getTCD_Sbm_Product_Name() {
		return TCD_Sbm_Product_Name;
	}

	public void setTCD_Sbm_Product_Name(String tCD_Sbm_Product_Name) {
		TCD_Sbm_Product_Name = tCD_Sbm_Product_Name;
	}

	

}
