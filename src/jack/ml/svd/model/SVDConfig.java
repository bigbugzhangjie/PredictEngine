package jack.ml.svd.model;

import jack.utility.BinaryFileBuffer;

public class SVDConfig {
	int num_user;
	int num_item;
	int num_factor;
	int num_global;
	float u_init_sigma; // standard variance for user factor
	float i_init_sigma;
	float base_score;
	int no_user_bias;
	int num_ufeedback;
	float ufeedback_init_sigma;
	
	int num_randinit_ufactor;
	int num_randinit_ifactor;
	int common_latent_space;
	int user_nongegtive;
	int common_feedback_space;
	int extend_flag;
	int item_nonnegtive;
	
	static int skipLength(){
		return 4*247;
	}
	
	public static SVDConfig load(BinaryFileBuffer bfb){
		SVDConfig ret =  new SVDConfig();
		ret.num_user = bfb.getInt();
		ret.num_item = bfb.getInt();
		ret.num_factor = bfb.getInt();
		ret.num_global = bfb.getInt();
		ret.u_init_sigma = bfb.getFloat();
		ret.i_init_sigma = bfb.getFloat();
		ret.base_score = bfb.getFloat();
		ret.no_user_bias = bfb.getInt();
		ret.num_ufeedback = bfb.getInt();
		ret.ufeedback_init_sigma = bfb.getFloat();
		ret.num_randinit_ufactor = bfb.getInt();
		ret.num_randinit_ifactor = bfb.getInt();
		ret.common_latent_space = bfb.getInt();
		ret.user_nongegtive = bfb.getInt();
		ret.common_feedback_space = bfb.getInt();
		ret.extend_flag = bfb.getInt();
		ret.item_nonnegtive = bfb.getInt();
		bfb.skip(skipLength());
		return ret;
	}
	
	public String toString(){
		return String.format(
				 "|--------------------SVD Training Config----------------------|\n"
				+"| # of User Feature %8d     # of Item Feature %8d  |\n"
				+"| # of Global Feature %8d   # of Latent Stat %5d  |\n"
				+"|-------------------------------------------------------------|\n"
				+"| U_sigma %1.7f       I_sigma %1.7f      Base_score %1.7f|\n"
				+"| No_User_Bias %c       Common Latent %c         |\n"
				+"|-------------------------------------------------------------|\n",				 
				num_user,this.num_item,this.num_global,this.num_factor,
				this.u_init_sigma,this.i_init_sigma,this.base_score,
				this.no_user_bias==0?"N":"Y",
				this.common_latent_space==0?"N":"Y"
				);
		
	}
	
}
