package com.cvs.nrt.hackathon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NRTKafkaObject {
	
	String rx_clm_nbr;  
	String clm_seq;
	String clm_stts;
	String dt_submitted;
	String sbm_dt_of_service;
	String final_plan_cde;
	String carrier_id;
	String account_id;
	String group_id;
	String member_id;
	String sbm_days_supply;
	String sbm_fill_nbr;
	String generic_indc_medSpan;
	String clm_origination_flg;
	String sbm_rx_nbr;
	String mbr_prior_auth_nbr;
	String sbm_bin_nbr;
	String sbm_processor_ctl_Nbr;
	String sbm_compound_cde;
	String sbm_group_nbr;
	String sbm_prescriber_id;
	String sbm_product_id;
	String prd_desc;
	String sbm_product_sel_cde;
	String sbm_qty_dispensed;
	String sbm_srv_prov_id;
	String cms_contract_id;
	String cob_clm_indc;
	String sbm_pa_nbr;
	String prior_auth_rsn_cde;
	String prd_key;
	String reimbursement_flag;
	String rx_ntwrk_id;
	String add_time;
	String chg_dt;
	String chg_time;
	String carrier_nm;
	String mbr_dt_of_birth;
	String mbr_first_nm;
	String mbr_last_nm;
	String mbr_multiple_birth_cde;
	String mbr_sex;
	String fmly_benefit_amt;
	String mbr_benefit_amt;
	String ndc_gpi_cde;
	String plan_drug_stts;
	String ignore_pa_stts;
	String pa_effective_dt;
	String pa_term_dt;	
	String clm_msg_1;
	String clm_msg_2;
	String clm_msg_3;
	
	@JsonProperty("Claim_type")
	String Claim_type;
	
	String npi_id;
	String dispenser_type;
	String phmcy_address_1;
	String phmcy_city;
	String phmcy_nm;
	String phmcy_ncpdp_nbr;
	String phmcy_phone;
	String phmcy_state;
	String phmcy_zip;
	String npi_nbr;
	String physician_first_nm;
	String physician_last_nm;
	String fmly_oop_max_amt;
	String mbr_oop_max_amt;
	String rbl_copay_prcnt_amt;
	String app_flat_sales_tax_amt;
	String fmly_accu_deduct;
	String ind_accu_deduct;
	String rbl_amt_appl_per_deduct;
	String rbl_copay_amt;
	String rbl_dispensing_fee;
	String rbl_ingredient_cost;
	String rbl_pat_pay_amt;
	String rbl_total_amt;
	String remaining_oop_max_amt;
	String tier;
	String plan_ndc_list_nm;
	String rej_reject_cde_1;
	String rej_reject_cde_1_desc;
	String rej_reject_cde_2;
	String rej_reject_cde_2_desc;
	String rej_reject_cde_3;
	String rej_reject_cde_3_desc;
	String stlmnt_cde_1;
	String stlmnt_cde_1_msg;
	String stlmnt_cde_2;
	String stlmnt_cde_2_msg;
	String stlmnt_cde_3;
	String stlmnt_cde_3_msg;
	String stlmnt_cde_4;
	String stlmnt_cde_4_msg;
	String stlmnt_cde_5;
	String stlmnt_cde_5_msg;
	String srx_indc;
	String paid_msg_1;
	String paid_msg_2;
	String paid_msg_3;
	String prd_dea_class;
	
	String prd_maintenance_drug;
	String otc_ct_indc;
	String smart_pa_indc;
	String lrd_pa_refil_lmt_ovrride_cde;
	String lrd_pa_type;
	String lrd_pa_note_txt;
	String lrd_pa_chg_usr_nm;
	String lrd_pa_chg_dt_time;
	 
	public String getRx_clm_nbr() {
		return rx_clm_nbr;
	}

	public void setRx_clm_nbr(String rx_clm_nbr) {
		this.rx_clm_nbr = rx_clm_nbr;
	} 

	public String getClm_seq() {
		return clm_seq;
	}

	public void setClm_seq(String clm_seq) {
		this.clm_seq = clm_seq;
	}

	public String getClm_stts() {
		return clm_stts;
	}

	public void setClm_stts(String clm_stts) {
		this.clm_stts = clm_stts;
	}

	public String getDt_submitted() {
		return dt_submitted;
	}

	public void setDt_submitted(String dt_submitted) {
		this.dt_submitted = dt_submitted;
	}

	public String getSbm_dt_of_service() {
		return sbm_dt_of_service;
	}

	public void setSbm_dt_of_service(String sbm_dt_of_service) {
		this.sbm_dt_of_service = sbm_dt_of_service;
	}

	public String getFinal_plan_cde() {
		return final_plan_cde;
	}

	public void setFinal_plan_cde(String final_plan_cde) {
		this.final_plan_cde = final_plan_cde;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getSbm_days_supply() {
		return sbm_days_supply;
	}

	public void setSbm_days_supply(String sbm_days_supply) {
		this.sbm_days_supply = sbm_days_supply;
	}

	public String getSbm_fill_nbr() {
		return sbm_fill_nbr;
	}

	public void setSbm_fill_nbr(String sbm_fill_nbr) {
		this.sbm_fill_nbr = sbm_fill_nbr;
	}

	public String getGeneric_indc_medSpan() {
		return generic_indc_medSpan;
	}

	public void setGeneric_indc_medSpan(String generic_indc_medSpan) {
		this.generic_indc_medSpan = generic_indc_medSpan;
	}

	public String getClm_origination_flg() {
		return clm_origination_flg;
	}

	public void setClm_origination_flg(String clm_origination_flg) {
		this.clm_origination_flg = clm_origination_flg;
	}

	public String getSbm_rx_nbr() {
		return sbm_rx_nbr;
	}

	public void setSbm_rx_nbr(String sbm_rx_nbr) {
		this.sbm_rx_nbr = sbm_rx_nbr;
	}

	public String getMbr_prior_auth_nbr() {
		return mbr_prior_auth_nbr;
	}

	public void setMbr_prior_auth_nbr(String mbr_prior_auth_nbr) {
		this.mbr_prior_auth_nbr = mbr_prior_auth_nbr;
	}

	public String getSbm_bin_nbr() {
		return sbm_bin_nbr;
	}

	public void setSbm_bin_nbr(String sbm_bin_nbr) {
		this.sbm_bin_nbr = sbm_bin_nbr;
	}

	public String getSbm_processor_ctl_Nbr() {
		return sbm_processor_ctl_Nbr;
	}

	public void setSbm_processor_ctl_Nbr(String sbm_processor_ctl_Nbr) {
		this.sbm_processor_ctl_Nbr = sbm_processor_ctl_Nbr;
	}

	public String getSbm_compound_cde() {
		return sbm_compound_cde;
	}

	public void setSbm_compound_cde(String sbm_compound_cde) {
		this.sbm_compound_cde = sbm_compound_cde;
	}

	public String getSbm_group_nbr() {
		return sbm_group_nbr;
	}

	public void setSbm_group_nbr(String sbm_group_nbr) {
		this.sbm_group_nbr = sbm_group_nbr;
	}

	public String getSbm_prescriber_id() {
		return sbm_prescriber_id;
	}

	public void setSbm_prescriber_id(String sbm_prescriber_id) {
		this.sbm_prescriber_id = sbm_prescriber_id;
	}

	public String getSbm_product_id() {
		return sbm_product_id;
	}

	public void setSbm_product_id(String sbm_product_id) {
		this.sbm_product_id = sbm_product_id;
	}

	public String getPrd_desc() {
		return prd_desc;
	}

	public void setPrd_desc(String prd_desc) {
		this.prd_desc = prd_desc;
	}

	public String getSbm_product_sel_cde() {
		return sbm_product_sel_cde;
	}

	public void setSbm_product_sel_cde(String sbm_product_sel_cde) {
		this.sbm_product_sel_cde = sbm_product_sel_cde;
	}

	public String getSbm_qty_dispensed() {
		return sbm_qty_dispensed;
	}

	public void setSbm_qty_dispensed(String sbm_qty_dispensed) {
		this.sbm_qty_dispensed = sbm_qty_dispensed;
	}

	public String getSbm_srv_prov_id() {
		return sbm_srv_prov_id;
	}

	public void setSbm_srv_prov_id(String sbm_srv_prov_id) {
		this.sbm_srv_prov_id = sbm_srv_prov_id;
	}

	public String getCms_contract_id() {
		return cms_contract_id;
	}

	public void setCms_contract_id(String cms_contract_id) {
		this.cms_contract_id = cms_contract_id;
	}

	public String getCob_clm_indc() {
		return cob_clm_indc;
	}

	public void setCob_clm_indc(String cob_clm_indc) {
		this.cob_clm_indc = cob_clm_indc;
	}

	public String getSbm_pa_nbr() {
		return sbm_pa_nbr;
	}

	public void setSbm_pa_nbr(String sbm_pa_nbr) {
		this.sbm_pa_nbr = sbm_pa_nbr;
	}

	public String getPrior_auth_rsn_cde() {
		return prior_auth_rsn_cde;
	}

	public void setPrior_auth_rsn_cde(String prior_auth_rsn_cde) {
		this.prior_auth_rsn_cde = prior_auth_rsn_cde;
	}

	public String getPrd_key() {
		return prd_key;
	}

	public void setPrd_key(String prd_key) {
		this.prd_key = prd_key;
	}

	public String getReimbursement_flag() {
		return reimbursement_flag;
	}

	public void setReimbursement_flag(String reimbursement_flag) {
		this.reimbursement_flag = reimbursement_flag;
	}

	public String getRx_ntwrk_id() {
		return rx_ntwrk_id;
	}

	public void setRx_ntwrk_id(String rx_ntwrk_id) {
		this.rx_ntwrk_id = rx_ntwrk_id;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

	public String getChg_time() {
		return chg_time;
	}

	public void setChg_time(String chg_time) {
		this.chg_time = chg_time;
	}

	public String getCarrier_nm() {
		return carrier_nm;
	}

	public void setCarrier_nm(String carrier_nm) {
		this.carrier_nm = carrier_nm;
	}

	public String getMbr_dt_of_birth() {
		return mbr_dt_of_birth;
	}

	public void setMbr_dt_of_birth(String mbr_dt_of_birth) {
		this.mbr_dt_of_birth = mbr_dt_of_birth;
	}

	public String getMbr_first_nm() {
		return mbr_first_nm;
	}

	public void setMbr_first_nm(String mbr_first_nm) {
		this.mbr_first_nm = mbr_first_nm;
	}

	public String getMbr_last_nm() {
		return mbr_last_nm;
	}

	public void setMbr_last_nm(String mbr_last_nm) {
		this.mbr_last_nm = mbr_last_nm;
	}

	public String getMbr_multiple_birth_cde() {
		return mbr_multiple_birth_cde;
	}

	public void setMbr_multiple_birth_cde(String mbr_multiple_birth_cde) {
		this.mbr_multiple_birth_cde = mbr_multiple_birth_cde;
	}

	public String getMbr_sex() {
		return mbr_sex;
	}

	public void setMbr_sex(String mbr_sex) {
		this.mbr_sex = mbr_sex;
	}

	public String getFmly_benefit_amt() {
		return fmly_benefit_amt;
	}

	public void setFmly_benefit_amt(String fmly_benefit_amt) {
		this.fmly_benefit_amt = fmly_benefit_amt;
	}

	public String getMbr_benefit_amt() {
		return mbr_benefit_amt;
	}

	public void setMbr_benefit_amt(String mbr_benefit_amt) {
		this.mbr_benefit_amt = mbr_benefit_amt;
	}

	public String getNdc_gpi_cde() {
		return ndc_gpi_cde;
	}

	public void setNdc_gpi_cde(String ndc_gpi_cde) {
		this.ndc_gpi_cde = ndc_gpi_cde;
	}

	public String getPlan_drug_stts() {
		return plan_drug_stts;
	}

	public void setPlan_drug_stts(String plan_drug_stts) {
		this.plan_drug_stts = plan_drug_stts;
	}

	public String getIgnore_pa_stts() {
		return ignore_pa_stts;
	}

	public void setIgnore_pa_stts(String ignore_pa_stts) {
		this.ignore_pa_stts = ignore_pa_stts;
	}

	public String getPa_effective_dt() {
		return pa_effective_dt;
	}

	public void setPa_effective_dt(String pa_effective_dt) {
		this.pa_effective_dt = pa_effective_dt;
	}

	public String getPa_term_dt() {
		return pa_term_dt;
	}

	public void setPa_term_dt(String pa_term_dt) {
		this.pa_term_dt = pa_term_dt;
	}

	public String getClm_msg_1() {
		return clm_msg_1;
	}

	public void setClm_msg_1(String clm_msg_1) {
		this.clm_msg_1 = clm_msg_1;
	}

	public String getClm_msg_2() {
		return clm_msg_2;
	}

	public void setClm_msg_2(String clm_msg_2) {
		this.clm_msg_2 = clm_msg_2;
	}

	public String getClm_msg_3() {
		return clm_msg_3;
	}

	public void setClm_msg_3(String clm_msg_3) {
		this.clm_msg_3 = clm_msg_3;
	}

	public String getNpi_id() {
		return npi_id;
	}

	public void setNpi_id(String npi_id) {
		this.npi_id = npi_id;
	}

	public String getDispenser_type() {
		return dispenser_type;
	}

	public void setDispenser_type(String dispenser_type) {
		this.dispenser_type = dispenser_type;
	}

	public String getPhmcy_address_1() {
		return phmcy_address_1;
	}

	public void setPhmcy_address_1(String phmcy_address_1) {
		this.phmcy_address_1 = phmcy_address_1;
	}

	public String getPhmcy_city() {
		return phmcy_city;
	}

	public void setPhmcy_city(String phmcy_city) {
		this.phmcy_city = phmcy_city;
	}

	public String getPhmcy_nm() {
		return phmcy_nm;
	}

	public void setPhmcy_nm(String phmcy_nm) {
		this.phmcy_nm = phmcy_nm;
	}

	public String getPhmcy_ncpdp_nbr() {
		return phmcy_ncpdp_nbr;
	}

	public void setPhmcy_ncpdp_nbr(String phmcy_ncpdp_nbr) {
		this.phmcy_ncpdp_nbr = phmcy_ncpdp_nbr;
	}

	public String getPhmcy_phone() {
		return phmcy_phone;
	}

	public void setPhmcy_phone(String phmcy_phone) {
		this.phmcy_phone = phmcy_phone;
	}

	public String getPhmcy_state() {
		return phmcy_state;
	}

	public void setPhmcy_state(String phmcy_state) {
		this.phmcy_state = phmcy_state;
	}

	public String getPhmcy_zip() {
		return phmcy_zip;
	}

	public void setPhmcy_zip(String phmcy_zip) {
		this.phmcy_zip = phmcy_zip;
	}

	public String getNpi_nbr() {
		return npi_nbr;
	}

	public void setNpi_nbr(String npi_nbr) {
		this.npi_nbr = npi_nbr;
	}

	public String getPhysician_first_nm() {
		return physician_first_nm;
	}

	public void setPhysician_first_nm(String physician_first_nm) {
		this.physician_first_nm = physician_first_nm;
	}

	public String getPhysician_last_nm() {
		return physician_last_nm;
	}

	public void setPhysician_last_nm(String physician_last_nm) {
		this.physician_last_nm = physician_last_nm;
	}

	public String getFmly_oop_max_amt() {
		return fmly_oop_max_amt;
	}

	public void setFmly_oop_max_amt(String fmly_oop_max_amt) {
		this.fmly_oop_max_amt = fmly_oop_max_amt;
	}

	public String getMbr_oop_max_amt() {
		return mbr_oop_max_amt;
	}

	public void setMbr_oop_max_amt(String mbr_oop_max_amt) {
		this.mbr_oop_max_amt = mbr_oop_max_amt;
	}

	public String getRbl_copay_prcnt_amt() {
		return rbl_copay_prcnt_amt;
	}

	public void setRbl_copay_prcnt_amt(String rbl_copay_prcnt_amt) {
		this.rbl_copay_prcnt_amt = rbl_copay_prcnt_amt;
	}

	public String getApp_flat_sales_tax_amt() {
		return app_flat_sales_tax_amt;
	}

	public void setApp_flat_sales_tax_amt(String app_flat_sales_tax_amt) {
		this.app_flat_sales_tax_amt = app_flat_sales_tax_amt;
	}

	public String getFmly_accu_deduct() {
		return fmly_accu_deduct;
	}

	public void setFmly_accu_deduct(String fmly_accu_deduct) {
		this.fmly_accu_deduct = fmly_accu_deduct;
	}

	public String getInd_accu_deduct() {
		return ind_accu_deduct;
	}

	public void setInd_accu_deduct(String ind_accu_deduct) {
		this.ind_accu_deduct = ind_accu_deduct;
	}

	public String getRbl_amt_appl_per_deduct() {
		return rbl_amt_appl_per_deduct;
	}

	public void setRbl_amt_appl_per_deduct(String rbl_amt_appl_per_deduct) {
		this.rbl_amt_appl_per_deduct = rbl_amt_appl_per_deduct;
	}

	public String getRbl_copay_amt() {
		return rbl_copay_amt;
	}

	public void setRbl_copay_amt(String rbl_copay_amt) {
		this.rbl_copay_amt = rbl_copay_amt;
	}

	public String getRbl_dispensing_fee() {
		return rbl_dispensing_fee;
	}

	public void setRbl_dispensing_fee(String rbl_dispensing_fee) {
		this.rbl_dispensing_fee = rbl_dispensing_fee;
	}

	public String getRbl_ingredient_cost() {
		return rbl_ingredient_cost;
	}

	public void setRbl_ingredient_cost(String rbl_ingredient_cost) {
		this.rbl_ingredient_cost = rbl_ingredient_cost;
	}

	public String getRbl_pat_pay_amt() {
		return rbl_pat_pay_amt;
	}

	public void setRbl_pat_pay_amt(String rbl_pat_pay_amt) {
		this.rbl_pat_pay_amt = rbl_pat_pay_amt;
	}

	public String getRbl_total_amt() {
		return rbl_total_amt;
	}

	public void setRbl_total_amt(String rbl_total_amt) {
		this.rbl_total_amt = rbl_total_amt;
	}

	public String getRemaining_oop_max_amt() {
		return remaining_oop_max_amt;
	}

	public void setRemaining_oop_max_amt(String remaining_oop_max_amt) {
		this.remaining_oop_max_amt = remaining_oop_max_amt;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getPlan_ndc_list_nm() {
		return plan_ndc_list_nm;
	}

	public void setPlan_ndc_list_nm(String plan_ndc_list_nm) {
		this.plan_ndc_list_nm = plan_ndc_list_nm;
	}

	public String getRej_reject_cde_1() {
		return rej_reject_cde_1;
	}

	public void setRej_reject_cde_1(String rej_reject_cde_1) {
		this.rej_reject_cde_1 = rej_reject_cde_1;
	}

	public String getRej_reject_cde_1_desc() {
		return rej_reject_cde_1_desc;
	}

	public void setRej_reject_cde_1_desc(String rej_reject_cde_1_desc) {
		this.rej_reject_cde_1_desc = rej_reject_cde_1_desc;
	}

	public String getRej_reject_cde_2() {
		return rej_reject_cde_2;
	}

	public void setRej_reject_cde_2(String rej_reject_cde_2) {
		this.rej_reject_cde_2 = rej_reject_cde_2;
	}

	public String getRej_reject_cde_2_desc() {
		return rej_reject_cde_2_desc;
	}

	public void setRej_reject_cde_2_desc(String rej_reject_cde_2_desc) {
		this.rej_reject_cde_2_desc = rej_reject_cde_2_desc;
	}

	public String getRej_reject_cde_3() {
		return rej_reject_cde_3;
	}

	public void setRej_reject_cde_3(String rej_reject_cde_3) {
		this.rej_reject_cde_3 = rej_reject_cde_3;
	}

	public String getRej_reject_cde_3_desc() {
		return rej_reject_cde_3_desc;
	}

	public void setRej_reject_cde_3_desc(String rej_reject_cde_3_desc) {
		this.rej_reject_cde_3_desc = rej_reject_cde_3_desc;
	}

	public String getStlmnt_cde_1() {
		return stlmnt_cde_1;
	}

	public void setStlmnt_cde_1(String stlmnt_cde_1) {
		this.stlmnt_cde_1 = stlmnt_cde_1;
	}

	public String getStlmnt_cde_1_msg() {
		return stlmnt_cde_1_msg;
	}

	public void setStlmnt_cde_1_msg(String stlmnt_cde_1_msg) {
		this.stlmnt_cde_1_msg = stlmnt_cde_1_msg;
	}

	public String getStlmnt_cde_2() {
		return stlmnt_cde_2;
	}

	public void setStlmnt_cde_2(String stlmnt_cde_2) {
		this.stlmnt_cde_2 = stlmnt_cde_2;
	}

	public String getStlmnt_cde_2_msg() {
		return stlmnt_cde_2_msg;
	}

	public void setStlmnt_cde_2_msg(String stlmnt_cde_2_msg) {
		this.stlmnt_cde_2_msg = stlmnt_cde_2_msg;
	}

	public String getStlmnt_cde_3() {
		return stlmnt_cde_3;
	}

	public void setStlmnt_cde_3(String stlmnt_cde_3) {
		this.stlmnt_cde_3 = stlmnt_cde_3;
	}

	public String getStlmnt_cde_3_msg() {
		return stlmnt_cde_3_msg;
	}

	public void setStlmnt_cde_3_msg(String stlmnt_cde_3_msg) {
		this.stlmnt_cde_3_msg = stlmnt_cde_3_msg;
	}

	public String getStlmnt_cde_4() {
		return stlmnt_cde_4;
	}

	public void setStlmnt_cde_4(String stlmnt_cde_4) {
		this.stlmnt_cde_4 = stlmnt_cde_4;
	}

	public String getStlmnt_cde_4_msg() {
		return stlmnt_cde_4_msg;
	}

	public void setStlmnt_cde_4_msg(String stlmnt_cde_4_msg) {
		this.stlmnt_cde_4_msg = stlmnt_cde_4_msg;
	}

	public String getStlmnt_cde_5() {
		return stlmnt_cde_5;
	}

	public void setStlmnt_cde_5(String stlmnt_cde_5) {
		this.stlmnt_cde_5 = stlmnt_cde_5;
	}

	public String getStlmnt_cde_5_msg() {
		return stlmnt_cde_5_msg;
	}

	public void setStlmnt_cde_5_msg(String stlmnt_cde_5_msg) {
		this.stlmnt_cde_5_msg = stlmnt_cde_5_msg;
	}

	public String getSrx_indc() {
		return srx_indc;
	}

	public void setSrx_indc(String srx_indc) {
		this.srx_indc = srx_indc;
	}

	public String getPaid_msg_1() {
		return paid_msg_1;
	}

	public void setPaid_msg_1(String paid_msg_1) {
		this.paid_msg_1 = paid_msg_1;
	}

	public String getPaid_msg_2() {
		return paid_msg_2;
	}

	public void setPaid_msg_2(String paid_msg_2) {
		this.paid_msg_2 = paid_msg_2;
	}

	public String getPaid_msg_3() {
		return paid_msg_3;
	}

	public void setPaid_msg_3(String paid_msg_3) {
		this.paid_msg_3 = paid_msg_3;
	}

	public String getPrd_dea_class() {
		return prd_dea_class;
	}

	public void setPrd_dea_class(String prd_dea_class) {
		this.prd_dea_class = prd_dea_class;
	}

	public String getPrd_maintenance_drug() {
		return prd_maintenance_drug;
	}

	public void setPrd_maintenance_drug(String prd_maintenance_drug) {
		this.prd_maintenance_drug = prd_maintenance_drug;
	}

	public String getOtc_ct_indc() {
		return otc_ct_indc;
	}

	public void setOtc_ct_indc(String otc_ct_indc) {
		this.otc_ct_indc = otc_ct_indc;
	}

	public String getSmart_pa_indc() {
		return smart_pa_indc;
	}

	public void setSmart_pa_indc(String smart_pa_indc) {
		this.smart_pa_indc = smart_pa_indc;
	}

	public String getLrd_pa_refil_lmt_ovrride_cde() {
		return lrd_pa_refil_lmt_ovrride_cde;
	}

	public void setLrd_pa_refil_lmt_ovrride_cde(String lrd_pa_refil_lmt_ovrride_cde) {
		this.lrd_pa_refil_lmt_ovrride_cde = lrd_pa_refil_lmt_ovrride_cde;
	}

	public String getLrd_pa_type() {
		return lrd_pa_type;
	}

	public void setLrd_pa_type(String lrd_pa_type) {
		this.lrd_pa_type = lrd_pa_type;
	}

	public String getLrd_pa_note_txt() {
		return lrd_pa_note_txt;
	}

	public void setLrd_pa_note_txt(String lrd_pa_note_txt) {
		this.lrd_pa_note_txt = lrd_pa_note_txt;
	}

	public String getLrd_pa_chg_usr_nm() {
		return lrd_pa_chg_usr_nm;
	}

	public void setLrd_pa_chg_usr_nm(String lrd_pa_chg_usr_nm) {
		this.lrd_pa_chg_usr_nm = lrd_pa_chg_usr_nm;
	}

	public String getLrd_pa_chg_dt_time() {
		return lrd_pa_chg_dt_time;
	}

	public void setLrd_pa_chg_dt_time(String lrd_pa_chg_dt_time) {
		this.lrd_pa_chg_dt_time = lrd_pa_chg_dt_time;
	}

	public String getClaim_type() {
		return Claim_type;
	}

	public void setClaim_type(String claim_type) {
		Claim_type = claim_type;
	}	
	
}
