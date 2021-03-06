package entity;

// default package
// Generated Jun 22, 2016 9:21:28 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * TrPelanggan generated by hbm2java
 */
public class TrPelanggan implements java.io.Serializable {

	private String kodePelanggan;
	private String namaAkun;
	private String namaPemilik;
	private String email;
	private String telp;
	private String alamat;
	private String line;
	private String instagram;
	private String keterangan;
	private Integer diskonRapid;
	private Integer diskonJne;
	private Date tglMulaiDiskon;
	private String sms;
	private String namaSales;
	private String referensi;
	private Date tglGabung;
	private Date tglCreate;
	private Date tglUpdate;
	private Integer flag;
	private String jabatan1;
	private String jabatan2;
	

	public TrPelanggan() {
	}

	public TrPelanggan(String kodePelanggan) {
		this.kodePelanggan = kodePelanggan;
	}

	
	public String getJabatan1() {
		return jabatan1;
	}

	public void setJabatan1(String jabatan1) {
		this.jabatan1 = jabatan1;
	}

	public String getJabatan2() {
		return jabatan2;
	}

	public void setJabatan2(String jabatan2) {
		this.jabatan2 = jabatan2;
	}

	public TrPelanggan(String kodePelanggan, String namaAkun, String namaPemilik, String email, String telp,
			String alamat, String line, String instagram, String keterangan, Integer diskonRapid, Integer diskonJne,
			Date tglMulaiDiskon, String sms, String namaSales, String referensi, Date tglGabung, Date tglCreate,
			Date tglUpdate, Integer flag) {
		this.kodePelanggan = kodePelanggan;
		this.namaAkun = namaAkun;
		this.namaPemilik = namaPemilik;
		this.email = email;
		this.telp = telp;
		this.alamat = alamat;
		this.line = line;
		this.instagram = instagram;
		this.keterangan = keterangan;
		this.diskonRapid = diskonRapid;
		this.diskonJne = diskonJne;
		this.tglMulaiDiskon = tglMulaiDiskon;
		this.sms = sms;
		this.namaSales = namaSales;
		this.referensi = referensi;
		this.tglGabung = tglGabung;
		this.tglCreate = tglCreate;
		this.tglUpdate = tglUpdate;
		this.flag = flag;
	}

	public String getKodePelanggan() {
		return this.kodePelanggan;
	}

	public void setKodePelanggan(String kodePelanggan) {
		this.kodePelanggan = kodePelanggan;
	}

	public String getNamaAkun() {
		return this.namaAkun;
	}

	public void setNamaAkun(String namaAkun) {
		this.namaAkun = namaAkun;
	}

	public String getNamaPemilik() {
		return this.namaPemilik;
	}

	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelp() {
		return this.telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getInstagram() {
		return this.instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Integer getDiskonRapid() {
		return this.diskonRapid;
	}

	public void setDiskonRapid(Integer diskonRapid) {
		this.diskonRapid = diskonRapid;
	}

	public Integer getDiskonJne() {
		return this.diskonJne;
	}

	public void setDiskonJne(Integer diskonJne) {
		this.diskonJne = diskonJne;
	}

	public Date getTglMulaiDiskon() {
		return this.tglMulaiDiskon;
	}

	public void setTglMulaiDiskon(Date tglMulaiDiskon) {
		this.tglMulaiDiskon = tglMulaiDiskon;
	}

	public String getSms() {
		return this.sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getNamaSales() {
		return this.namaSales;
	}

	public void setNamaSales(String namaSales) {
		this.namaSales = namaSales;
	}

	public String getReferensi() {
		return this.referensi;
	}

	public void setReferensi(String referensi) {
		this.referensi = referensi;
	}

	public Date getTglGabung() {
		return this.tglGabung;
	}

	public void setTglGabung(Date tglGabung) {
		this.tglGabung = tglGabung;
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
