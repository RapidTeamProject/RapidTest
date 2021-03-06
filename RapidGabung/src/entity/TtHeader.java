package entity;

// default package
// Generated Jun 22, 2016 9:21:28 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * TtHeader generated by hbm2java
 */
public class TtHeader implements java.io.Serializable {

	private String awbHeader;
	private String idKeranjang;
	private Integer idUpload;
	private Date wkSortir;
	private Integer sortirFlag;
	private Integer gabungPaketFlag;
	private Date wkGabungPaket;
	private String idKardus;
	private Integer inboundFlag;
	private Date wkInbound;
	private Integer barangTerkirimFlag;
	private String idKurir;
	private Integer submitFlag;
	private Integer waitingPendingFlag;
	private String userCreate;
	private String userUpdate;
	private String resiJne;
	private Date tglCreate;
	private Date tglUpdate;
	private Integer flag;
	private String userInbound;
	
	public TtHeader() {
	}

	public TtHeader(String awbHeader) {
		this.awbHeader = awbHeader;
	}

	public TtHeader(String awbHeader, String idKeranjang, Integer idUpload, Date wkSortir, Integer sortirFlag,
			Integer gabungPaketFlag, Date wkGabungPaket, String idKardus, Integer inboundFlag, Date wkInbound,
			Integer barangTerkirimFlag, String idKurir, Integer submitFlag, Integer waitingPendingFlag,
			String userCreate, String userUpdate, String resiJne, Date tglCreate, Date tglUpdate, Integer flag, String userInbound) {
		this.awbHeader = awbHeader;
		this.idKeranjang = idKeranjang;
		this.idUpload = idUpload;
		this.wkSortir = wkSortir;
		this.sortirFlag = sortirFlag;
		this.gabungPaketFlag = gabungPaketFlag;
		this.wkGabungPaket = wkGabungPaket;
		this.idKardus = idKardus;
		this.inboundFlag = inboundFlag;
		this.wkInbound = wkInbound;
		this.barangTerkirimFlag = barangTerkirimFlag;
		this.idKurir = idKurir;
		this.submitFlag = submitFlag;
		this.waitingPendingFlag = waitingPendingFlag;
		this.userCreate = userCreate;
		this.userUpdate = userUpdate;
		this.resiJne = resiJne;
		this.tglCreate = tglCreate;
		this.tglUpdate = tglUpdate;
		this.flag = flag;
		this.userInbound = userInbound;
	}

	public String getAwbHeader() {
		return this.awbHeader;
	}

	public void setAwbHeader(String awbHeader) {
		this.awbHeader = awbHeader;
	}

	public String getIdKeranjang() {
		return this.idKeranjang;
	}

	public void setIdKeranjang(String idKeranjang) {
		this.idKeranjang = idKeranjang;
	}

	public Integer getIdUpload() {
		return this.idUpload;
	}

	public void setIdUpload(Integer idUpload) {
		this.idUpload = idUpload;
	}

	public Date getWkSortir() {
		return this.wkSortir;
	}

	public void setWkSortir(Date wkSortir) {
		this.wkSortir = wkSortir;
	}

	public Integer getSortirFlag() {
		return this.sortirFlag;
	}

	public void setSortirFlag(Integer sortirFlag) {
		this.sortirFlag = sortirFlag;
	}

	public Integer getGabungPaketFlag() {
		return this.gabungPaketFlag;
	}

	public void setGabungPaketFlag(Integer gabungPaketFlag) {
		this.gabungPaketFlag = gabungPaketFlag;
	}

	public Date getWkGabungPaket() {
		return this.wkGabungPaket;
	}

	public void setWkGabungPaket(Date wkGabungPaket) {
		this.wkGabungPaket = wkGabungPaket;
	}

	public String getIdKardus() {
		return this.idKardus;
	}

	public void setIdKardus(String idKardus) {
		this.idKardus = idKardus;
	}

	public Integer getInboundFlag() {
		return this.inboundFlag;
	}

	public void setInboundFlag(Integer inboundFlag) {
		this.inboundFlag = inboundFlag;
	}

	public Date getWkInbound() {
		return this.wkInbound;
	}

	public void setWkInbound(Date wkInbound) {
		this.wkInbound = wkInbound;
	}

	public Integer getBarangTerkirimFlag() {
		return this.barangTerkirimFlag;
	}

	public void setBarangTerkirimFlag(Integer barangTerkirimFlag) {
		this.barangTerkirimFlag = barangTerkirimFlag;
	}

	public String getIdKurir() {
		return this.idKurir;
	}

	public void setIdKurir(String idKurir) {
		this.idKurir = idKurir;
	}

	public Integer getSubmitFlag() {
		return this.submitFlag;
	}

	public void setSubmitFlag(Integer submitFlag) {
		this.submitFlag = submitFlag;
	}

	public Integer getWaitingPendingFlag() {
		return this.waitingPendingFlag;
	}

	public void setWaitingPendingFlag(Integer waitingPendingFlag) {
		this.waitingPendingFlag = waitingPendingFlag;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getResiJne() {
		return this.resiJne;
	}

	public void setResiJne(String resiJne) {
		this.resiJne = resiJne;
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
	
	public String getUserInbound() {
		return this.userInbound;
	}

	public void setUserInbound(String userInbound) {
		this.userInbound = userInbound;
	}

}
