package entity;

// default package
// Generated Jun 22, 2016 9:21:28 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * TtMappingResiJne generated by hbm2java
 */
public class TtMappingResiJne implements java.io.Serializable {

	private String resiJne;
	private String penerima;
	private String pengirim;
	private String tujuan;
	private String service;
	private Integer harga;
	private Integer uploadFlag;
	private Date importAt;
	private Date tglCreate;
	private Date tglUpdate;
	private Integer flag;

	public TtMappingResiJne() {
	}

	public TtMappingResiJne(String resiJne) {
		this.resiJne = resiJne;
	}

	public TtMappingResiJne(String resiJne, String penerima, String pengirim, String tujuan, String service,
			Integer harga, Integer uploadFlag, Date importAt, Date tglCreate, Date tglUpdate, Integer flag) {
		this.resiJne = resiJne;
		this.penerima = penerima;
		this.pengirim = pengirim;
		this.tujuan = tujuan;
		this.service = service;
		this.harga = harga;
		this.uploadFlag = uploadFlag;
		this.importAt = importAt;
		this.tglCreate = tglCreate;
		this.tglUpdate = tglUpdate;
		this.flag = flag;
	}

	public String getResiJne() {
		return this.resiJne;
	}

	public void setResiJne(String resiJne) {
		this.resiJne = resiJne;
	}

	public String getPenerima() {
		return this.penerima;
	}

	public void setPenerima(String penerima) {
		this.penerima = penerima;
	}

	public String getPengirim() {
		return this.pengirim;
	}

	public void setPengirim(String pengirim) {
		this.pengirim = pengirim;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getHarga() {
		return this.harga;
	}

	public void setHarga(Integer harga) {
		this.harga = harga;
	}

	public Integer getUploadFlag() {
		return this.uploadFlag;
	}

	public void setUploadFlag(Integer uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public Date getImportAt() {
		return this.importAt;
	}

	public void setImportAt(Date importAt) {
		this.importAt = importAt;
	}

	public Date getTglCreate() {
		return this.tglCreate;
	}

	public void setTglCreate(Date tglCreate) {
		this.tglCreate = tglCreate;
	}

	public Date getTglUpdate() {
		return this.tglUpdate;
	}

	public void setTglUpdate(Date tglUpdate) {
		this.tglUpdate = tglUpdate;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
