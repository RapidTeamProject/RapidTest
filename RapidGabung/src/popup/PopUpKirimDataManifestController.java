package popup;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import VO.EntryDataShowVO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import service.GenericService;
import service.MasterCabangService;
import util.DateUtil;
import util.EmailUtil;
import util.ExportToExcell;
import util.MessageBox;

public class PopUpKirimDataManifestController<T> implements Initializable {
	@FXML
	TableView tvTagihanPelanggan;

	@FXML
	private DatePicker dateAwal, dateAkhir;

	@FXML
	private Button btnCari, btnClear, btnSentEmail;

	public String kdTujuan;

	private ObservableList<EntryDataShowVO> olDokumen = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dateAwal.setValue(LocalDate.now());
		dateAkhir.setValue(LocalDate.now());

		dateAkhir.setOnAction(event -> {
			settingListboksMasterCabang();
		});
	}

	@FXML
	public void onClickSent() {
		if(olDokumen.size()==0){
			MessageBox.alert("DATA KOSONG");
		}else{
			for (EntryDataShowVO datEntry : olDokumen) {
				List<EntryDataShowVO> en = new ArrayList<EntryDataShowVO>();
				en.clear();
				System.out.println("start");
				
				en = MasterCabangService.getDataCabangExportExcell(
						DateUtil.convertToDatabaseColumn(dateAwal.getValue()),
						DateUtil.convertToDatabaseColumn(dateAkhir.getValue()), datEntry.getKdPerwakilan());
				ExportToExcell.exportToExcell3(en, datEntry.getKdPerwakilan(), "Data Manifest Master Cabang", DateUtil.dateToStdDateLiteral(DateUtil.getNow()));
//				String username = "rapidexpress.id@gmail.com";
//				String password = "rapid150515";
				
//				String username = "rapid.jakarta@gmail.com";
//				String password = "jktjktjkt";
				
				Map<String, Object> config = GenericService.getConfig();
				
				String username = config.get("email_username")==null?"":(String) config.get("email_username");
				String password = config.get("email_password")==null?"":(String) config.get("email_password");
				
				
				String toEmail = datEntry.getEmail();
				EmailUtil.kirimManifestWithEmail(
						username, 
						password, 
						toEmail, 
						"Data Manifest Master Cabang" + "-" + datEntry.getKdPerwakilan() + "-" + DateUtil.dateToStdDateLiteral(DateUtil.getNow()) + ".xls", 
						"C:/DLL/REPORT/EXPORTEXCEL/" + "Data Manifest Master Cabang" + "-" + datEntry.getKdPerwakilan() + "-" + DateUtil.dateToStdDateLiteral(DateUtil.getNow()) + ".xls");
				System.out.println("end");
			}
			MessageBox.alert("Export Berhasil DI Drive C:/DLL/REPORT/EXPORTEXCEL/");
		}
	}

	@FXML
	public void onClickCari() {
		olDokumen.clear();
		settingListboksMasterCabang();
	}

	@FXML
	public void onClickClear() {
		dateAwal.setValue(LocalDate.now());
		dateAkhir.setValue(LocalDate.now());
	}

	public void settingListboksMasterCabang() {
		tvTagihanPelanggan.getColumns().clear();
		olDokumen = FXCollections.observableArrayList(
				MasterCabangService.getDataCabangTagihan(DateUtil.convertToDatabaseColumn(dateAwal.getValue()),
						DateUtil.convertToDatabaseColumn(dateAkhir.getValue())));
		TableColumn col = new TableColumn("No");
		col.setPrefWidth(80.0);
		col.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<EntryDataShowVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<EntryDataShowVO, String> param) {
						return new ReadOnlyObjectWrapper(tvTagihanPelanggan.getItems().indexOf(param.getValue()) + 1);
					}

				});
		tvTagihanPelanggan.getColumns().addAll(col);

		col = new TableColumn("Kode Perwakilan");
		col.setPrefWidth(300.0);
		col.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<EntryDataShowVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<EntryDataShowVO, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getKdPerwakilan()));
					}
				});
		tvTagihanPelanggan.getColumns().addAll(col);

		col = new TableColumn("Jumlah AWB");
		col.setPrefWidth(350.0);
		col.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<EntryDataShowVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<EntryDataShowVO, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getCount()));
					}
				});
		tvTagihanPelanggan.getColumns().addAll(col);
		
		col = new TableColumn("Email");
		col.setPrefWidth(350.0);
		col.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<EntryDataShowVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<EntryDataShowVO, String> param) {
						return new SimpleStringProperty(String.valueOf(param.getValue().getEmail()));
					}
				});
		tvTagihanPelanggan.getColumns().addAll(col);

		tvTagihanPelanggan.setItems(olDokumen);
	}

}
