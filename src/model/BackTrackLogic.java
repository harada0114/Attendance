package model;

import java.util.List;

public class BackTrackLogic {
	
	// 配列の再起バックトラック
	// 入力された値の全パターンをListに詰め、返す。やばい、重い。
	public List make_perm(int n, String[] word, boolean[] flag, String[] c, List word_List) {
		
		if (n == word.length) {
			word_List.add(c.clone());
			//word_List.add(Arrays.toString(c));
			
		} else {
				  
			for (int i = 1; i <= word.length; i++) {
					  
				if (flag[i]) {
					continue;
				}
					  
				c[n] = word[i-1];
				//sq.append("%"+c[n]+"%");
				
				flag[i] = true;
					  				  
				make_perm(n + 1, word, flag, c, word_List);
					  
				flag[i] = false;
					  
			} // for
		}
		return word_List;
	}
}