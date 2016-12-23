package controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import VO.LaporanKurirVO;
import VO.ReportVO;
import entity.TrPerwakilan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import service.MasterPerwakilanService;
import util.DtoListener;
import util.ExportToExcell;
import util.ManagedFormHelper;
import util.MessageBox;
import util.WindowsHelper;

public class KPICabangKurirController implements Initializable {
	
	@FXML
	private DatePicker dpAwal, dpAkhir;
	
	@FXML
	private CheckBox chkAllPerwakilan;
	
	@FXML
	private ComboBox cmbPerwakilan;

	@FXML
	private TableView<ReportVO> tvLapKurirCabang;
	
	@FXML
	private TextField txtCari, txtAu, txtBa, txtNth, txtCoda, txtMiss, txtCnee, txtJne, txtTotal;
	
	@FXML
	private TableColumn<ReportVO, String> colNo, colTanggal, colKdPerwakilan, colSales, colKirim, colTerima, colSisa, colTotal;
	
	private ObservableList<LaporanKurirVO> masterHeader = FXCollections.observableArrayList();
	private ObservableList<LaporanKurirVO> masterDetail = FXCollections.observableArrayList();
	private ObservableList<LaporanKurirVO> masterFooter = FXCollections.observableArrayList();
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		 //Set Objek kelas ini
        ManagedFormHelper.instanceController = this;
        dpAwal.setValue(LocalDate.now());
		dpAkhir.setValue(LocalDate.now());
		
		cmbPerwakilan.getItems().add("All Cabang");
		List<TrPerwakilan> lstPerwakilan = MasterPerwakilanService.getAllPerwakilanCabangDistinct();
		for (TrPerwakilan trPerwakilan : lstPerwakilan) {
			cmbPerwakilan.getItems().add(trPerwakilan.getKodePerwakilan());
		}
		cmbPerwakilan.setValue("All Cabang");
	}
	
	@DtoListener(idDtoListener = "backTop")
    public void backDtoListener()
    {
        try {
            HBox bodyHBox = new HBox();
            FXMLLoader menu = new FXMLLoader(getClass().getResource("/controller/Menu.fxml"));
            ScrollPane menuPage = (ScrollPane) menu.load();
            menuPage.prefWidthProperty().bind(WindowsHelper.primaryStage.widthProperty());       //FullScreen, hilangin klo g mau fullscreen
            bodyHBox.getChildren().add(menuPage);
            bodyHBox.setAlignment(Pos.CENTER);
            
            if(WindowsHelper.rootLayout!=null)
            {
                WindowsHelper.rootLayout.setCenter(bodyHBox);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	@FXML
	public void onClikCari(){
		
	}
	
	
	
	@FXML
	public void onClikExcel(){
		try {
			setDataHeader();
			setDataDetail();
			setDataFooter();
			ExportToExcell.exportToReportKurir(masterHeader, masterDetail, masterFooter, "cetak KPI Kurir");
			
			MessageBox.alert("Export Berhasil Di Drive C:/DLL/REPORT/EXPORT/"
					+ " cetak KPI Kurir.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setDataHeader() {
		//format voHeader (Tanggal, Kirim, Terima, Sisa, Percentage) order by date
		LaporanKurirVO rowH1 = new LaporanKurirVO("21-11-2016", "517", "477", "40", "92.3");
		LaporanKurirVO rowH2 = new LaporanKurirVO("22-11-2016", "0", "0", "0", "0");
		LaporanKurirVO rowH3 = new LaporanKurirVO("23-11-2016", "400", "380", "20", "95.0");
//		LaporanKurirVO rowH4 = new LaporanKurirVO("24-11-2016", "469", "429", "40", "91.5");
//		LaporanKurirVO rowH5 = new LaporanKurirVO("25-11-2016", "407", "399", "8", "98.0");
//		LaporanKurirVO rowH6 = new LaporanKurirVO("26-11-2016", "422", "403", "19", "95.5");
//		LaporanKurirVO rowH7 = new LaporanKurirVO("27-11-2016", "358", "311", "47", "86.9");
//		LaporanKurirVO rowH8 = new LaporanKurirVO("28-11-2016", "295", "273", "22", "92.5");
//		LaporanKurirVO rowH9 = new LaporanKurirVO("29-11-2016", "0", "0", "0", "0");
//		LaporanKurirVO rowH10 = new LaporanKurirVO("30-11-2016", "476", "412", "64", "86.6");
		masterHeader.add(rowH1);
		masterHeader.add(rowH2);
		masterHeader.add(rowH3);
//		masterHeader.add(rowH4);
//		masterHeader.add(rowH5);
//		masterHeader.add(rowH6);
//		masterHeader.add(rowH7);
//		masterHeader.add(rowH8);
//		masterHeader.add(rowH9);
//		masterHeader.add(rowH10);
	}
	
	private void setDataDetail() {
		//format voDetail (Nama, Kirim, Terima, Sisa, Total) Order by Name
		//Abdul1
		LaporanKurirVO rowD1 = new LaporanKurirVO("Abdul", "23", "23", "0", "464");
		LaporanKurirVO rowD2 = new LaporanKurirVO("Abdul", "0", "0", "0", "464");
		LaporanKurirVO rowD3 = new LaporanKurirVO("Rochim", "56", "56", "0", "516");
		LaporanKurirVO rowD4 = new LaporanKurirVO("Rochim", "0", "0", "0", "167");
		LaporanKurirVO rowD5 = new LaporanKurirVO("Rochim", "133", "132", "1", "718");
		LaporanKurirVO rowD6 = new LaporanKurirVO("Marcel", "56", "55", "1", "567");
		LaporanKurirVO rowD7 = new LaporanKurirVO("Deni", "25", "24", "1", "479");
		LaporanKurirVO rowD8 = new LaporanKurirVO("Ara", "39", "39", "0", "410");
		LaporanKurirVO rowD9 = new LaporanKurirVO("Danang", "55", "51", "4", "536");
		LaporanKurirVO rowD10 = new LaporanKurirVO("Dedi", "79", "64", "15", "243");
		LaporanKurirVO rowD11 = new LaporanKurirVO("Ichwan", "44", "28", "16", "409");
		LaporanKurirVO rowD12 = new LaporanKurirVO("Eman", "7", "5", "2", "167");
		LaporanKurirVO rowD13 = new LaporanKurirVO("Boy", "7", "5", "2", "167");
		
		//Popo
//		LaporanKurirVO rowD1 = new LaporanKurirVO("Abdul", "23", "23", "0", "464");
//		LaporanKurirVO rowD2 = new LaporanKurirVO("Popo", "56", "56", "0", "516");
//		LaporanKurirVO rowD3 = new LaporanKurirVO("Rochim", "133", "132", "1", "718");
//		LaporanKurirVO rowD4 = new LaporanKurirVO("Marcel", "56", "55", "1", "567");
//		LaporanKurirVO rowD5 = new LaporanKurirVO("Deni", "25", "24", "1", "479");
//		LaporanKurirVO rowD6 = new LaporanKurirVO("Ara", "39", "39", "0", "410");
//		LaporanKurirVO rowD7 = new LaporanKurirVO("Danang", "55", "51", "4", "536");
//		LaporanKurirVO rowD8 = new LaporanKurirVO("Dedi", "79", "64", "15", "243");
//		LaporanKurirVO rowD9 = new LaporanKurirVO("Ichwan", "44", "28", "16", "409");
//		LaporanKurirVO rowD10 = new LaporanKurirVO("Eman", "7", "5", "2", "167");
				
		masterDetail.add(rowD1);
		masterDetail.add(rowD2);
		masterDetail.add(rowD3);
		masterDetail.add(rowD4);
		masterDetail.add(rowD5);
		masterDetail.add(rowD6);
		masterDetail.add(rowD7);
		masterDetail.add(rowD8);
		masterDetail.add(rowD9);
		masterDetail.add(rowD10);
		masterDetail.add(rowD11);
		masterDetail.add(rowD12);
		masterDetail.add(rowD13);
	}
	
	private void setDataFooter() {
		//format voFooter (Au, Ba, Coda, nth, cnee, miss_route, jne, total) Order by date
		LaporanKurirVO rowF1 = new LaporanKurirVO("100", "100", "0", "0", "100", "100", "0", "100");
		LaporanKurirVO rowF2 = new LaporanKurirVO("0", "100", "100", "100", "0", "100", "100", "0");
		
		masterFooter.add(rowF1);
		masterFooter.add(rowF2);
	}
}