package kr.trip.domain;

import lombok.Data;

@Data
public class ContentVO {

	 /*
	  * "AREA_NM": "황리단길",
     "BSSH_NM": "여도가주",
     "ADRES": "경북 경주시 포석로 1057-6",
     "MBTLNUM": ""
	  * */
	
	private String area_nm;
	private String bssh_nm;
	private String adres;
	private String mbtlnum;
	
	public ContentVO(String area_nm, String bssh_nm, String adres, String mbtlnum) {
		this.area_nm = area_nm;
		this.bssh_nm = bssh_nm;
		this.adres = adres;
		this.mbtlnum = mbtlnum;
	}
	
	
}
