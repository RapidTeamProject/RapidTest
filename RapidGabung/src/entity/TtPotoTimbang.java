package entity;

// default package
// Generated Jun 22, 2016 9:21:28 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * TtPotoTimbang generated by hbm2java
 */
public class TtPotoTimbang implements java.io.Serializable {

	private String awbPotoTimbang;
	private String asalPaket;
	private String kodePickup;
	private String kodePelanggan;
	private String awbData;
	private String vol;
	private String bvol;
	private String bpvol;
	private String timb;
	private String berattimb;
	private String bclose;
	private String bpclose;
	private String layanan;
	private String kodePerwakilan;
	private Integer jneFlag;
	private String gambar;
	private Date tglGambar;
	private Integer koli;
	private String user;
	private Date tglCreate;
	private Date tglUpdate;
	private Integer flag;
	private Integer flagLunas;

	public TtPotoTimbang() {
	}

	public TtPotoTimbang(String awbPotoTimbang) {
		this.awbPotoTimbang = awbPotoTimbang;
	}

	public TtPotoTimbang(String awbPotoTimbang, String asalPaket, String kodePickup, String kodePelanggan,
			String awbData, String vol, String bvol, String bpvol, String timb, String berattimb, String bclose,
			String bpclose, String layanan, String kodePerwakilan, Integer jneFlag, String gambar, Date tglGambar,
			Integer koli, String user, Date tglCreate, Date tglUpdate, Integer flag, Integer flagLunas) {
		this.awbPotoTimbang = awbPotoTimbang;
		this.asalPaket = asalPaket;
		this.kodePickup = kodePickup;
		this.kodePelanggan = kodePelanggan;
		this.awbData = awbData;
		this.vol = vol;
		this.bvol = bvol;
		this.bpvol = bpvol;
		this.timb = timb;
		this.berattimb = berattimb;
		this.bclose = bclose;
		this.bpclose = bpclose;
		this.layanan = layanan;
		this.kodePerwakilan = kodePerwakilan;
		this.jneFlag = jneFlag;
		this.gambar = gambar;
		this.tglGambar = tglGambar;
		this.koli = koli;
		this.user = user;
		this.tglCreate = tglCreate;
		this.tglUpdate = tglUpdate;
		this.flag = flag;
		this.flagLunas=flagLunas;
	}

	public Integer getFlagLunas() {
		return flagLunas;
	}

	public void setFlagLunas(Integer flagLunas) {
		this.flagLunas = flagLunas;
	}

	public String getAwbPotoTimbang() {
		return this.awbPotoTimbang;
	}

	public void setAwbPotoTimbang(String awbPotoTimbang) {
		this.awbPotoTimbang = awbPotoTimbang;
	}

	public String getAsalPaket() {
		return this.asalPaket;
	}

	public void setAsalPaket(String asalPaket) {
		this.asalPaket = asalPaket;
	}

	public String getKodePickup() {
		return this.kodePickup;
	}

	public void setKodePickup(String kodePickup) {
		this.kodePickup = kodePickup;
	}

	public String getKodePelanggan() {
		return this.kodePelanggan;
	}

	public void setKodePelanggan(String kodePelanggan) {
		this.kodePelanggan = kodePelanggan;
	}

	public String getAwbData() {
		return this.awbData;
	}

	public void setAwbData(String awbData) {
		this.awbData = awbData;
	}

	public String getVol() {
		return this.vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getBvol() {
		return this.bvol;
	}

	public void setBvol(String bvol) {
		this.bvol = bvol;
	}

	public String getBpvol() {
		return this.bpvol;
	}

	public void setBpvol(String bpvol) {
		this.bpvol = bpvol;
	}

	public String getTimb() {
		return this.timb;
	}

	public void setTimb(String timb) {
		this.timb = timb;
	}

	public String getBerattimb() {
		return this.berattimb;
	}

	public void setBerattimb(String berattimb) {
		this.berattimb = berattimb;
	}

	public String getBclose() {
		return this.bclose;
	}

	public void setBclose(String bclose) {
		this.bclose = bclose;
	}

	public String getBpclose() {
		return this.bpclose;
	}

	public void setBpclose(String bpclose) {
		this.bpclose = bpclose;
	}

	public String getLayanan() {
		return this.layanan;
	}

	public void setLayanan(String layanan) {
		this.layanan = layanan;
	}

	public String getKodePerwakilan() {
		return this.kodePerwakilan;
	}

	public void setKodePerwakilan(String kodePerwakilan) {
		this.kodePerwakilan = kodePerwakilan;
	}

	public Integer getJneFlag() {
		return this.jneFlag;
	}

	public void setJneFlag(Integer jneFlag) {
		this.jneFlag = jneFlag;
	}

	public String getGambar() {
		return this.gambar;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public Date getTglGambar() {
		return this.tglGambar;
	}

	public void setTglGambar(Date tglGambar) {
		this.tglGambar = tglGambar;
	}

	public Integer getKoli() {
		return this.koli;
	}

	public void setKoli(Integer koli) {
		this.koli = koli;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
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
