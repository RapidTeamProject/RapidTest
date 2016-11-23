package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import com.sun.prism.impl.Disposer.Record;

import VO.BrowseSemuaDataVO;
import entity.TrPelanggan;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;
import service.BrowseSemuaDataService;
import service.PelangganService;
import service.PotoTimbangService;
import util.DateUtil;
import util.DtoListener;
import util.ManagedFormHelper;
import util.MessageBox;
import util.WindowsHelper;
import utilfx.AutoCompleteComboBoxListener;

public class BrowseSemuaDataController implements Initializable {

	@FXML
	DatePicker txtTglMulai, txtTglAkhir;

	@FXML
	TableView<BrowseSemuaDataVO> listBrowse;

	@FXML
	private TextField txtCari;

	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colAwbData;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colCreated;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colLayanan;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colPengirim;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colTelp;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colAsalPaket;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colKdPerwakilan;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colTujuan;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colZona;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colPenerima;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colBFinal;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colBpFinal;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colBVolume;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colHarga;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colTotalBiaya;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colResiJNE;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colReseller;
	@FXML
	private TableColumn<BrowseSemuaDataVO, String> colIdKardus;

	@FXML
	private TableColumn colActionUpdate;

	private ObservableList<BrowseSemuaDataVO> masterData = FXCollections.observableArrayList();

	@FXML
	private Button btnMultiple;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ManagedFormHelper.instanceController = this;

		txtTglMulai.setValue(LocalDate.now());
		txtTglAkhir.setValue(LocalDate.now());
//		txtTglAkhir.setOnAction(event -> {
//			masterData.clear();
//			settingListboksBrowseSemuaData(DateUtil.convertToDatabaseColumn(txtTglMulai.getValue()),
//					DateUtil.convertToDatabaseColumn(txtTglAkhir.getValue()));
//		});
		masterData.clear();
		settingListboksBrowseSemuaData(DateUtil.convertToDatabaseColumn(txtTglMulai.getValue()),
				DateUtil.convertToDatabaseColumn(txtTglAkhir.getValue()));
		btnMultiple.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Set<BrowseSemuaDataVO> selection = new HashSet<BrowseSemuaDataVO>(listBrowse.getSelectionModel().getSelectedItems());
				if(selection.size()==0){
					MessageBox.alert("Pilih minimal 1 nomor resi yang akan ingin di ubah sekaligus");
				}else{
					// Create the custom dialog.
					Dialog<Pair<String, String>> dialog = new Dialog<>();
					dialog.setTitle("Pilih Pelanggan");
					dialog.setHeaderText("Ada " + selection.size() + " item yang dipilih, silahkan pilih pelanggan yang akan diubah sekaligus");
	
					// Set the button types.
					ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
					dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
	
					// Create the username and password labels and fields.
					GridPane grid = new GridPane();
					grid.setHgap(10);
					grid.setVgap(10);
					grid.setPadding(new Insets(20, 150, 10, 10));
	
					ComboBox cmbPelanggan = new ComboBox();
					List<TrPelanggan> lstPelanggan = PelangganService.getDataPelanggan();
					for (TrPelanggan trPelanggan : lstPelanggan) {
						cmbPelanggan.getItems().add(trPelanggan.getNamaAkun());
					}
					new AutoCompleteComboBoxListener<TrPelanggan>(cmbPelanggan);
					
					TextField txtNoPickup = new TextField(); 
					cmbPelanggan.setPromptText("Pelanggan");
					
					grid.add(new Label("Pelanggan"), 0, 0);
					grid.add(cmbPelanggan, 1, 0);
					grid.add(new Label("No. Pickup"), 0, 1);
					grid.add(txtNoPickup, 1, 1);
	
	//				// Enable/Disable login button depending on whether a username was entered.
					Node loginButton = dialog.getDialogPane().lookupButton(okButtonType);
					loginButton.setDisable(true);
	
	//				// Do some validation (using the Java 8 lambda syntax).
					cmbPelanggan.valueProperty().addListener(new ChangeListener<String>() {
				        @Override public void changed(ObservableValue ov, String t, String t1) {
				        	loginButton.setDisable(true);
				        	TrPelanggan trPelanggan = PelangganService.getPelangganByName( (String) cmbPelanggan.getSelectionModel().getSelectedItem().toString());
				        	if(trPelanggan!=null){
				        		loginButton.setDisable(false);
				        	}else{
				        		loginButton.setDisable(true);
				        	}
				        }    
				      });
	
					dialog.getDialogPane().setContent(grid);
	
					// Request focus on the username field by default.
					Platform.runLater(() -> cmbPelanggan.requestFocus());
					
					Optional<Pair<String, String>> result = dialog.showAndWait();
					dialog.setResultConverter(dialogButton -> {
						System.out.println("--> dialogButton : " + dialogButton);
						if (dialogButton == okButtonType) {
							TrPelanggan trPelanggan = PelangganService.getPelangganByName( (String) cmbPelanggan.getSelectionModel().getSelectedItem().toString());
							for (BrowseSemuaDataVO each : selection) {
								System.out.println("--> " + each.getAwbData());
								System.out.println("--> " + each.getPengirim());
								BrowseSemuaDataService.updateResiPengirim(
										each.getAwbData(), 
										trPelanggan.getKodePelanggan(),
										txtNoPickup.getText()
								);
							}					
							refreshTable();
						}else{
							dialog.close();
						}
						return null;
					});
				}
			}
		});
	}

	// DATAKEMBALIAN DARI DATA ENTRY
	public void refreshTable() {
		masterData.clear();
		settingListboksBrowseSemuaData(DateUtil.convertToDatabaseColumn(txtTglMulai.getValue()),
				DateUtil.convertToDatabaseColumn(txtTglAkhir.getValue()));
	}

	@FXML
	public void onClikCari() {
		masterData.clear();
		settingListboksBrowseSemuaData(DateUtil.convertToDatabaseColumn(txtTglMulai.getValue()),
				DateUtil.convertToDatabaseColumn(txtTglAkhir.getValue()));
	}
	// TABLE VIEW
	// SETTING------------------------------------------------------------------------
	public void settingListboksBrowseSemuaData(java.sql.Date dateAwal, java.sql.Date dateAkhir) {

		List<BrowseSemuaDataVO> tt = BrowseSemuaDataService.getBrowseSemuaData(dateAwal, dateAkhir, true);
		for (BrowseSemuaDataVO t : tt) {
			masterData.add(new BrowseSemuaDataVO(t.getAwbData(), t.getCreated(), t.getLayanan(), t.getPengirim(),
					t.getTelp(), t.getAsalPaket(), t.getKodePerwakilan(), t.getTujuan(), t.getZona(), t.getPenerima(),
					t.getBFinal(), t.getBpFinal(), t.getBVolume(), t.getHarga(), t.getTotalBiaya(), t.getResiJNE(),
					t.getReseller(), t.getIdKardus()));
		}
		
		colResiJNE.setCellFactory(TextFieldTableCell.forTableColumn());
		colResiJNE.setOnEditCommit(new EventHandler<CellEditEvent<BrowseSemuaDataVO, String>>() {
			public void handle(CellEditEvent<BrowseSemuaDataVO, String> t) {
				BrowseSemuaDataVO bro = (t.getTableView().getItems().get(t.getTablePosition().getRow()));
				BrowseSemuaDataService.updateResiJne(bro.getAwbData(), t.getNewValue());
			}
		});
		colCreated.setCellValueFactory(cellData -> cellData.getValue().createdProperty());
		
		colAwbData.setCellValueFactory(cellData -> cellData.getValue().awbDataProperty());
		colAwbData.setCellFactory(TextFieldTableCell.forTableColumn());
		
		colLayanan.setCellValueFactory(cellData -> cellData.getValue().layananProperty());
		
		colPengirim.setCellValueFactory(cellData -> cellData.getValue().pengirimProperty());
				
		colTelp.setCellValueFactory(cellData -> cellData.getValue().telpProperty());
		colAsalPaket.setCellValueFactory(cellData -> cellData.getValue().asalPaketProperty());
		
		colKdPerwakilan.setCellValueFactory(cellData -> cellData.getValue().kdPerwakilanProperty());
//		colKdPerwakilan.setCellFactory(TextFieldTableCell.forTableColumn());
//		colKdPerwakilan.setOnEditCommit(new EventHandler<CellEditEvent<BrowseSemuaDataVO, String>>() {
//			public void handle(CellEditEvent<BrowseSemuaDataVO, String> t) {
////				BrowseSemuaDataVO bro = (t.getTableView().getItems().get(t.getTablePosition().getRow()));
////				BrowseSemuaDataService.updateResiJne(bro.getAwbData(), t.getNewValue());
//				
//				Stage modalWindow = new Stage();
//				Parent rootPilihPelanggan;
//				try {
//					rootPilihPelanggan = FXMLLoader
//							.load(PilihPelangganSubController.class.getResource("dialogpilihpelanggan.fxml"));
//					modalWindow.setScene(new Scene(rootPilihPelanggan));
//					modalWindow.setTitle("Pilih Pelanggan");
//					modalWindow.initModality(Modality.WINDOW_MODAL);
//					modalWindow.initOwner(WindowsHelper.primaryStage);
//					modalWindow.initStyle(StageStyle.UTILITY);
//					modalWindow.show();
//
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
		
		colTujuan.setCellValueFactory(cellData -> cellData.getValue().tujuanProperty());
		colZona.setCellValueFactory(cellData -> cellData.getValue().zonaProperty());
		colBFinal.setCellValueFactory(cellData -> cellData.getValue().bFinalProperty());
		colBpFinal.setCellValueFactory(cellData -> cellData.getValue().bpFinalProperty());
		colBVolume.setCellValueFactory(cellData -> cellData.getValue().bVolumeProperty());
		colHarga.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
		colPenerima.setCellValueFactory(cellData -> cellData.getValue().penerimaProperty());
		colTotalBiaya.setCellValueFactory(cellData -> cellData.getValue().totalBiayaProperty());
		colResiJNE.setCellValueFactory(cellData -> cellData.getValue().resiJNEProperty());
		colReseller.setCellValueFactory(cellData -> cellData.getValue().resellerProperty());
		colIdKardus.setCellValueFactory(cellData -> cellData.getValue().idKardusProperty());
		
		addButton();
		FilteredList<BrowseSemuaDataVO> filteredData = new FilteredList<>(masterData, p -> true);

		txtCari.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(data -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (data.getAwbData().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getLayanan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getPengirim().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getTujuan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getTelp().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getAsalPaket().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getKodePerwakilan().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getTujuan().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getZona().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getBFinal().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getBpFinal().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getBVolume().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getHarga().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getTotalBiaya().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getResiJNE().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getReseller().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getPenerima().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				} else if (data.getIdKardus().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					addButton();
					return true;
				}

				return false;
			});
		});
		
		SortedList<BrowseSemuaDataVO> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(listBrowse.comparatorProperty());
		listBrowse.setItems(sortedData);

		listBrowse.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE
		);
	}

	// DELETE
	// BUTTON-------------------------------------------------------------------------------------
	@SuppressWarnings("restriction")
	private class ButtonCell extends TableCell<Record, Boolean> {

		TableView<BrowseSemuaDataVO> tab;

		ButtonCell(final TableView<BrowseSemuaDataVO> tblView) {
			this.tab = tblView;
		}

		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				int selectdIndex = getTableRow().getIndex();
				BrowseSemuaDataVO boVo = (BrowseSemuaDataVO) tab.getItems().get(selectdIndex);
				final HBox hbox = new HBox(5);
				Button buttonHistory = new Button("History");
				buttonHistory.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader fxmlLoader = new FXMLLoader(
									getClass().getResource("/controller/AwbHistory.fxml"));
							Stage dialogStage = new Stage();
							dialogStage.setTitle("AWB History");
							dialogStage.initModality(Modality.WINDOW_MODAL);
							dialogStage.initOwner(WindowsHelper.primaryStage);
							dialogStage.initStyle(StageStyle.UTILITY);
							dialogStage.setResizable(false);
							Parent root = (Parent) fxmlLoader.load();
							AwbHistoryController controller = fxmlLoader.<AwbHistoryController> getController();							
							controller.set(boVo.getAwbData());							
							Scene scene = new Scene(root);
							dialogStage.setScene(scene);
							dialogStage.show();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				});
				hbox.getChildren().add(buttonHistory);
				
				Button button = new Button("Edit");
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader fxmlLoader = new FXMLLoader(
									getClass().getResource("/controller/DataEntryBrowse.fxml"));
							Stage dialogStage = new Stage();
							dialogStage.setTitle("Edit Entry Data");
							dialogStage.initModality(Modality.WINDOW_MODAL);
							dialogStage.initOwner(WindowsHelper.primaryStage);
							dialogStage.initStyle(StageStyle.UTILITY);
							dialogStage.setResizable(false);
							Parent root = (Parent) fxmlLoader.load();
							DataEntryBrowseController controller = fxmlLoader
									.<DataEntryBrowseController> getController();
							controller.set(boVo, BrowseSemuaDataController.this);
							Scene scene = new Scene(root);
							dialogStage.setScene(scene);
							dialogStage.show();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				});
				hbox.getChildren().add(button);
				
				Button buttonDelete = new Button("Delete");
				buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						int[] dataButtonMessageBox = new int[2];
						dataButtonMessageBox[0] = MessageBox.BUTTON_OK;
						dataButtonMessageBox[1] = MessageBox.BUTTON_CANCEL;
						int hasilMessageBox = MessageBox.confirm("Apakah Yakin akan Melakukan Delete?",
								dataButtonMessageBox);
						if (hasilMessageBox == 5) { // cancel
						} else if (hasilMessageBox == 6) {
							BrowseSemuaDataService.deleteAwb(boVo.getAwbData(), false);
							masterData.clear();
							settingListboksBrowseSemuaData(DateUtil.convertToDatabaseColumn(txtTglMulai.getValue()),
									DateUtil.convertToDatabaseColumn(txtTglAkhir.getValue()));
						}
					}
				});
				
				hbox.getChildren().add(buttonDelete);
				setGraphic(hbox);
			}
		}
	}

	// -------------------------------------------------------------------------------->>Add
	// Button
	public void addButton() {
		
		colActionUpdate.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		colActionUpdate.setCellFactory(
				new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

					@Override
					public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
						return new ButtonCell(listBrowse);
					}
				});
	}

	// BACK
	// TOP--------------------------------------------------------------------------------------
	@DtoListener(idDtoListener = "backTop")
	public void backDtoListener() {
		try {
			HBox bodyHBox = new HBox();
			FXMLLoader menu = new FXMLLoader(getClass().getResource("/controller/Menu.fxml"));
			ScrollPane menuPage = (ScrollPane) menu.load();
			bodyHBox.getChildren().add(menuPage);
			bodyHBox.setAlignment(Pos.CENTER);

			if (WindowsHelper.rootLayout != null) {
				WindowsHelper.rootLayout.setCenter(bodyHBox);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}