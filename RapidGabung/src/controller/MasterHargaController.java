package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;

import VO.BrowseSemuaDataVO;
import controller.JadwalPickupController.JadwalTV;
import entity.TrHarga;
import entity.TrPelanggan;
import entity.TrPerwakilan;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import service.BrowseSemuaDataService;
import service.GabungPaketService;
import service.GenericService;
import service.MasterHargaService;
import service.PelangganService;
import util.DtoBroadcaster;
import util.DtoListener;
import util.ManagedFormHelper;
import util.MessageBox;
import util.WindowsHelper;
import utilfx.AutoCompleteComboBoxListener;

public class MasterHargaController implements Initializable {
	
	@FXML
	TextField txtKdAsal, txtKdPropinsi, txtKdKabupaten, txtKdkecamatan, txtZona
				, txtKdZona, txtKdPerwakilan, txtCGKReg, txtCGKRegEtd, txtCGKBest, txtCari;
	
	@FXML
	Button btnSimpan, btnBatal;
	
	@FXML
	TableView<MasterHargaTV> listBoxMasterHarga;
	
	@FXML
	private TableColumn<MasterHargaTV, String>
			colKodeZona,
		    colKodeAsal,
		    colPropinsi,
		    colKabupaten,
		    colKecamatan,
		    colPerwakilan,
		    colZona,
		    colReg,
		    colEtd,
		    colOne;
	
	private ObservableList<MasterHargaTV> masterData = FXCollections.observableArrayList();
	
	@FXML
	private Button btnUbahSekaligus;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
//		setListenerEnterTampil();
		// Set Objek kelas ini
		ManagedFormHelper.instanceController = this;
		masterData.clear();
		settingListboksMasterHarga();
		
		Platform.runLater(new Runnable() {
		     @Override
		     public void run() {
		         txtKdZona.requestFocus();
		     }
		});
		
		btnUbahSekaligus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Set<MasterHargaTV> selection = new HashSet<MasterHargaTV>(listBoxMasterHarga.getSelectionModel().getSelectedItems());
				if(selection.size()==0){
					MessageBox.alert("Pilih minimal 1 daftar harga yang akan ingin di ubah perwakilan");
				}else{
					// Create the custom dialog.
					Dialog<Pair<String, String>> dialog = new Dialog<>();
					dialog.setTitle("Ubah Perwakilan");
					dialog.setHeaderText("Ada " + selection.size() + " item yang dipilih");
	
					// Set the button types.
					ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
					dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
	
					// Create the username and password labels and fields.
					GridPane grid = new GridPane();
					grid.setHgap(10);
					grid.setVgap(10);
					grid.setPadding(new Insets(20, 150, 10, 10));
	
					TextField txtReg = new TextField("0");
					TextField txtOne = new TextField("0");
					TextField txtPerwakilan = new TextField("");
					
//					grid.add(new Label("Harga Reg"), 0, 0);
//					grid.add(txtReg, 1, 0);
//					grid.add(new Label("Harga ONE"), 0, 1);
//					grid.add(txtOne, 1, 1);
					grid.add(new Label("Perwakilan"), 0, 0);
					grid.add(txtPerwakilan, 1, 0);
					
	
	//				// Enable/Disable login button depending on whether a username was entered.
//					Node loginButton = dialog.getDialogPane().lookupButton(okButtonType);
//					loginButton.setDisable(true);
	
					dialog.getDialogPane().setContent(grid);
	
					// Request focus on the username field by default.
					Platform.runLater(() -> txtReg.requestFocus());
	
					// Convert the result to a username-password-pair when the login button is clicked.
					dialog.setResultConverter(dialogButton -> {
						if(dialogButton == okButtonType){
							for (MasterHargaTV sel : selection) {
								MasterHargaService.updateDataHarga2(sel.getKodeZona(), sel.getKodeAsal(), txtReg.getText(), txtOne.getText(), txtPerwakilan.getText());
							}
							masterData.clear();
							settingListboksMasterHarga();
							MessageBox.alert(selection.size() + " row, sukses di update");
						}

//						TrPelanggan trPelanggan = PelangganService.getPelangganByName( (String) cmbPelanggan.getSelectionModel().getSelectedItem().toString());
//						for (BrowseSemuaDataVO each : selection) {
//							System.out.println("--> " + each.getAwbData());
//							System.out.println("--> " + each.getPengirim());
//							BrowseSemuaDataService.updateResiPengirim(
//									each.getAwbData(), 
//									trPelanggan.getKodePelanggan(),
//									txtNoPickup.getText()
//							);
//						}					
//						refreshTable();
						
						return null;
					});
	
					Optional<Pair<String, String>> result = dialog.showAndWait();
	
					result.ifPresent(usernamePassword -> {
					    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
					});				
				}

			}
		});

	}

	public void settingListboksMasterHarga() {
		List<TrHarga> olDokumen = MasterHargaService.getDataHarga();
		for (TrHarga tr : olDokumen) {
			masterData.add(
					new MasterHargaTV(
							tr.getKodeZona()==null?"":tr.getKodeZona(), 
							tr.getKodeAsal()==null?"":tr.getKodeAsal(), 
							tr.getPropinsi()==null?"":tr.getPropinsi(), 
							tr.getKabupaten()==null?"":tr.getKabupaten(), 
							tr.getKecamatan()==null?"":tr.getKecamatan(), 
							tr.getKodePerwakilan()==null?"":tr.getKodePerwakilan(), 
							tr.getZona()==null?"":tr.getZona(), 
							tr.getReg().toString()==null?"":tr.getReg().toString(), 
							tr.getEtd()==null?"":tr.getEtd(), 
							tr.getOne().toString()==null?"":tr.getOne().toString()
							)
					);
		}
		
		colKodeZona.setCellValueFactory(cellData -> cellData.getValue().kodeZonaProperty());
	    colKodeAsal.setCellValueFactory(cellData -> cellData.getValue().kodeAsalProperty());
	    colPropinsi.setCellValueFactory(cellData -> cellData.getValue().propinsiProperty());
	    colKabupaten.setCellValueFactory(cellData -> cellData.getValue().kabupatenProperty());
	    colKecamatan.setCellValueFactory(cellData -> cellData.getValue().kecamatanProperty());
	    colPerwakilan.setCellValueFactory(cellData -> cellData.getValue().perwakilanProperty());
	    colZona.setCellValueFactory(cellData -> cellData.getValue().zonaProperty());
	    colReg.setCellValueFactory(cellData -> cellData.getValue().regProperty());
	    colEtd.setCellValueFactory(cellData -> cellData.getValue().etdProperty());
	    colOne.setCellValueFactory(cellData -> cellData.getValue().oneProperty());
		
	    FilteredList<MasterHargaTV> filteredData = new FilteredList<>(masterData, p -> true);

		txtCari.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(data -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (data.getKodeZona().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getKodeAsal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getPropinsi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getKabupaten().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getKecamatan().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getPerwakilan().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getZona().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getReg().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getZona().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getEtd().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (data.getOne().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;
			});
		});
		listBoxMasterHarga.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		SortedList<MasterHargaTV> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(listBoxMasterHarga.comparatorProperty());
		listBoxMasterHarga.setItems(sortedData);

	}
	
	public class MasterHargaTV{
		StringProperty kodeZona;
		StringProperty kodeAsal;
		StringProperty propinsi;
		StringProperty kabupaten;
		StringProperty kecamatan;
		StringProperty perwakilan;
		StringProperty zona;
		StringProperty reg;
		StringProperty etd;
		StringProperty one;
		
		public MasterHargaTV(
				String kodeZona, 
				String kodeAsal, 
				String propinsi, 
				String kabupaten, 
				String kecamatan,
				String perwakilan,
				String zona, 
				String reg, 
				String etd,
				String one){
			this.kodeZona = new SimpleStringProperty(kodeZona);
			this.kodeAsal = new SimpleStringProperty(kodeAsal);
			this.propinsi = new SimpleStringProperty(propinsi);
			this.kabupaten = new SimpleStringProperty(kabupaten);
			this.kecamatan = new SimpleStringProperty(kecamatan);
			this.perwakilan = new SimpleStringProperty(perwakilan);
			this.zona = new SimpleStringProperty(zona);
			this.reg = new SimpleStringProperty(reg);
			this.etd = new SimpleStringProperty(etd);
			this.one = new SimpleStringProperty(one);
		}

		public String getKodeZona() {
			return kodeZona.get();
		}

		public void setKodeZona(String kodeZona) {
			this.kodeZona.set(kodeZona);
		}

		public String getKodeAsal() {
			return kodeAsal.get();
		}

		public void setKodeAsal(String kodeAsal) {
			this.kodeAsal.set(kodeAsal);
		}

		public String getPropinsi() {
			return propinsi.get();
		}

		public void setPropinsi(String propinsi) {
			this.propinsi.set(propinsi);
		}

		public String getKabupaten() {
			return kabupaten.get();
		}

		public void setKabupaten(String kabupaten) {
			this.kabupaten.set(kabupaten);
		}

		public String getKecamatan() {
			return kecamatan.get();
		}

		public void setKecamatan(String kecamatan) {
			this.kecamatan.set(kecamatan);
		}

		public String getPerwakilan() {
			return perwakilan.get();
		}

		public void setPerwakilan(String perwakilan) {
			this.perwakilan.set(perwakilan);
		}

		public String getZona() {
			return zona.get();
		}

		public void setZona(String zona) {
			this.zona.set(zona);
		}

		public String getReg() {
			return reg.get();
		}

		public void setReg(String reg) {
			this.reg.set(reg);
		}

		public String getEtd() {
			return etd.get();
		}

		public void setEtd(String etd) {
			this.etd.set(etd);
		}

		public String getOne() {
			return one.get();
		}

		public void setOne(String one) {
			this.one.set(one);
		}
		
		public StringProperty kodeZonaProperty(){return kodeZona;};
		public StringProperty kodeAsalProperty(){return kodeAsal;};
		public StringProperty propinsiProperty(){return propinsi;};
		public StringProperty kabupatenProperty(){return kabupaten;};
		public StringProperty kecamatanProperty(){return kecamatan;};
		public StringProperty perwakilanProperty(){return perwakilan;};
		public StringProperty zonaProperty(){return zona;};
		public StringProperty regProperty(){return reg;};
		public StringProperty etdProperty(){return etd;};
		public StringProperty oneProperty(){return one;};
	}
	
//	@FXML
//	public void setListenerEnterTampil() {
//		EventHandler<KeyEvent> eH = new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				if (event.getCode() == KeyCode.ENTER) {
//					onClickTampilkan();
//				}
//			}
//		};
//	}
//  
//    @FXML
//	public void onClickTampilkan() {
//	  masterData.clear();
//	  settingListboksMasterHarga();
//	}
	
	@FXML
    public void onMouseClicked(MouseEvent evt){
        if (evt.getClickCount() > 1) {
        	MasterHargaTV select = (MasterHargaTV) listBoxMasterHarga.getSelectionModel().getSelectedItem();
            btnSimpan.setText("UPDATE");
            if(select!=null)
            {
           	 txtKdZona.setText(select.getKodeZona());
           	 txtKdAsal.setText(select.getKodeAsal());
           	 txtKdPropinsi.setText(select.getPropinsi());
           	 txtKdKabupaten.setText(select.getKabupaten());
           	 txtKdkecamatan.setText(select.getKecamatan());
           	 txtZona.setText(select.getZona());
           	 txtKdPerwakilan.setText(select.getPerwakilan());
           	 txtCGKReg.setText(select.getReg().toString());
           	 txtCGKRegEtd.setText(select.getEtd());
           	 txtCGKBest.setText(select.getOne().toString());
            }
        }
    }
	
	@FXML
    public void onSave(Event evt){
		List<TrHarga> test = MasterHargaService.getDataHargaByID(txtKdZona.getText(), txtKdAsal.getText());
		System.out.println(test.size());
		if(test.size()>0){
			// UPDATE TABLE
			MasterHargaService.updateDataHarga(txtKdZona.getText(), txtKdAsal.getText()
					, txtKdPropinsi.getText(), txtKdKabupaten.getText(), txtKdkecamatan.getText()
					, txtKdPerwakilan.getText(), txtZona.getText() 
					, Integer.parseInt(txtCGKReg.getText()), txtCGKRegEtd.getText(), Integer.parseInt(txtCGKBest.getText()));
		}else{
			
			//SAVE - TR_HARGA
			TrHarga trcab = new TrHarga();
			trcab.setKodeZona(txtKdZona.getText());
			trcab.setKodeAsal(txtKdAsal.getText());
			trcab.setPropinsi(txtKdPropinsi.getText());
			trcab.setKabupaten(txtKdKabupaten.getText());
			trcab.setKecamatan(txtKdkecamatan.getText());
			trcab.setZona(txtZona.getText());
			trcab.setKodePerwakilan(txtKdPerwakilan.getText());
			trcab.setReg(Integer.parseInt(txtCGKReg.getText()));
			trcab.setEtd(txtCGKRegEtd.getText());
			trcab.setOne(Integer.parseInt(txtCGKBest.getText()));
			
			Calendar cal = Calendar.getInstance();
			trcab.setTglCreate(cal.getTime());
			trcab.setTglUpdate(cal.getTime());
			trcab.setFlag(0);

			GenericService.save(TrHarga.class, trcab, true); 
			
			// SAVE TR_PERWAKILAN
			TrPerwakilan trPer = new TrPerwakilan();
			trPer.setKodeZona(txtKdZona.getText());
			trPer.setPropinsi(txtKdPropinsi.getText());
			trPer.setKabupaten(txtKdKabupaten.getText());
			trPer.setKecamatan(txtKdkecamatan.getText());
			trPer.setKodePerwakilan(txtKdPerwakilan.getText());
			trPer.setZona(txtZona.getText());
			trPer.setRegperwakilan(txtCGKReg.getText());
			trPer.setOneperwakilan(txtCGKBest.getText());
			trPer.setTglCreate(cal.getTime());
			trPer.setTglUpdate(cal.getTime());
			trPer.setFlag(0);
			
			GenericService.save(TrPerwakilan.class, trPer, true);
		}
		masterData.clear();
		settingListboksMasterHarga();
		clearForm();
		btnSimpan.setText("SIMPAN");
		
}
    
    @FXML
    public void onCancel(Event evt)
    {
        clearForm();
    }
    

	public void clearForm()

	{	
		txtKdZona.clear(); 
		txtKdAsal.clear();
		txtKdPropinsi.clear();
		txtKdKabupaten.clear();
		txtKdkecamatan.clear();	
		txtKdPerwakilan.clear();
		txtZona.clear();
		txtCGKReg.clear();
		txtCGKRegEtd.clear();
		txtCGKBest.clear();
		btnSimpan.setText("SIMPAN");
		
//		cbJabatan.getSelectionModel().clearSelection();
	
//		cbWilayah.getSelectionModel().clearSelection();
		

	}

	@DtoListener(idDtoListener = "backTop")
	public void backDtoListener() {
		try {
			HBox bodyHBox = new HBox();
			FXMLLoader menu = new FXMLLoader(getClass().getResource("/controller/Menu.fxml"));
			ScrollPane menuPage = (ScrollPane) menu.load();
			menuPage.prefWidthProperty().bind(WindowsHelper.primaryStage.widthProperty()); // FullScreen,
																							// hilangin
			bodyHBox.getChildren().add(menuPage);
			bodyHBox.setAlignment(Pos.CENTER);

			if (WindowsHelper.rootLayout != null) {
				WindowsHelper.rootLayout.setCenter(bodyHBox);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
		// Display button if the row is not empty
//		@Override
//		protected void updateItem(Boolean t, boolean empty) {
//			super.updateItem(t, empty);
//			if (!empty) {
//
//				setGraphic(cellButton);
//			}
//		}

	
}