package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Form1Controller implements Initializable {

    public Label lbID;
    public TextField txtTen;
    public DatePicker date;
    public RadioButton rdNam;
    public RadioButton rdNu;
    public ToggleGroup groupGioiTinh;
    public ProgressBar progressBar;
    public CheckBox chkPhoThong;
    public CheckBox chkCaoDang;
    public CheckBox chkSauDaiHoc;
    public FlowPane flow;
    NhanVien nhanVien = new NhanVien();
    ArrayList<NhanVien> dsNv = new ArrayList<>();
    double f = 0f;
    int ma = nhanVien.getMa();
    String GioiTinh = "";

    public void okClick(ActionEvent actionEvent) {

        LocalDate ngSinh = date.getValue();
        dsNv.add(new NhanVien(ma,txtTen.getText(),ngSinh.toString(),GioiTinh,f));
        String ID = String.valueOf(ma);
        Button ma = new Button("IDNV:"+ID);
        flow.getChildren().add(ma);
        Form1Controller.this.ma = Form1Controller.this.ma+1;
        lbID.setText(String.valueOf("IDNV:" + Form1Controller.this.ma));
        txtTen.setText("");
        chkSauDaiHoc.setSelected(false);
        chkCaoDang.setSelected(false);
        chkPhoThong.setSelected(false);
        groupGioiTinh.getSelectedToggle().setSelected(false);

        date.setConverter(null);
        ma.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtTen.setText("");
                chkPhoThong.setSelected(false);
                chkCaoDang.setSelected(false);
                chkSauDaiHoc.setSelected(false);

                System.out.println(ma.getText().substring(5, ma.getText().length()));
                for (NhanVien nv : dsNv)
                {
                    if (nv.getMa()==
                            Integer.parseInt(ma.getText().substring(5, ma.getText().length()))) {
                        System.out.println(nv);
                        txtTen.setText(nv.getTen());
                        lbID.setText("IDNV:"+String.valueOf(nv.getMa()));
                        String nam = nv.getNgSinh().substring(0,4);
                        System.out.println(nam);
                        String thang = nv.getNgSinh().substring(6,7);
                        System.out.println(thang);
                        String ngay = nv.getNgSinh().substring(8,10);
                        System.out.println(ngay);
                        date.setValue(LocalDate.of(Integer.parseInt(nam),
                                Integer.parseInt(thang), Integer.parseInt(ngay)));
                        String gt=nv.getGioiTinh();
                        if (gt.equals("Nam")) {
                            rdNam.setSelected(true);
                        }else {
                            rdNu.setSelected(true);
                        }
                        Double f= nv.getTrinhDo();
                        progressBar.setProgress(f);
                        if (f==0.3333333432674408) {
                            chkPhoThong.setSelected(true);
                        }
                        if (f==0.6666666865348816) {
                            chkPhoThong.setSelected(true);
                            chkCaoDang.setSelected(true);
                        }
                        if (f==1.0000000298023224) {
                            chkPhoThong.setSelected(true);
                            chkCaoDang.setSelected(true);
                            chkSauDaiHoc.setSelected(true);
                        }
                    }
                }
            }
        });
    }


    public void cancelClick(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEvent();
    }

    private void addEvent() {
        chkPhoThong.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    f+=1/3f;
                    progressBar.setProgress(f);
                }if (oldValue) {
                    chkCaoDang.setSelected(false);
                    chkSauDaiHoc.setSelected(false);
                    f -= 1/3f;
                    progressBar.setProgress(f);
                }

            }
        });
        chkCaoDang.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkPhoThong.setSelected(true);
                if (newValue) {
                    f+=1/3f;
                    progressBar.setProgress(f);
                }if (oldValue) {
                    chkSauDaiHoc.setSelected(false);
                    f -= 1/3f;
                    progressBar.setProgress(f);
                }

            }
        });
        chkSauDaiHoc.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkPhoThong.setSelected(true);
                chkCaoDang.setSelected(true);
                if (newValue) {
                    f+=1/3f;
                    progressBar.setProgress(f);
                }if (oldValue) {
                    f -= 1/3f;
                    progressBar.setProgress(f);
                }

            }
        });

        groupGioiTinh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
                                Toggle newValue) {

                RadioButton rad = (RadioButton) groupGioiTinh.getSelectedToggle();
                if (rad == null) {

                } else {
                    GioiTinh = rad.getText();
                }


            }
        });


    }
}
