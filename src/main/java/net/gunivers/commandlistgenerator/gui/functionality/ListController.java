package net.gunivers.commandlistgenerator.gui.functionality;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.functionality.list.List;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.commandlistgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.commandlistgenerator.gui.util.FunctionalityController;
import net.gunivers.commandlistgenerator.util.FileIO;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.gui.ShakeEffect;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple1;

public class ListController extends FunctionalityController implements Initializable
{
    private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

    @FXML
    JFXTextArea TEXT_AREA;

    @FXML
    JFXComboBox<String> LIST;

    @FXML
    JFXComboBox<String> SUB_LIST;

    @FXML
    JFXButton LOAD;

    @FXML
    Label LABEL;

    @FXML
    JFXButton IMPORT;

    @FXML
    JFXButton EXPORT;

    private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setDialog(ButtonNextHandler.newDialog);

        getDoneButton().setOnAction(event -> saveAll());
        getDoneButton().setDefaultButton(true);

        getDoneButton().setText(l.get("gui.button.done"));
        LOAD.setText(l.get("gui.button.load"));
        LIST.setPromptText(l.get("gui.list.list.prompttext"));
        SUB_LIST.setPromptText(l.get("gui.list.sublist.prompttext"));
        TEXT_AREA.setPromptText(l.get("gui.list.textarea.prompttext"));
        LABEL.setText(l.get("gui.list.label"));
        IMPORT.setText(l.get("gui.list.button.import"));
        EXPORT.setText(l.get("gui.list.button.export"));

        LIST.setOnAction(event -> onChoose());
        LOAD.setOnAction(event -> load());
        IMPORT.setOnAction(event -> importList());
        EXPORT.setOnAction(event -> exportList());

        LIST.getItems().setAll(List.lists.stream().map(name -> name.getTag()).collect(Collectors.toList()));

        if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null &&
                !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem().getText().isEmpty())
        {
            setDialog(ButtonEditHandler.dialog);
            Tuple t = Tag.tags.get(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem().getText()).getParameters();
            Tuple1<String[]> tuple = Tuple.castTo(t, Tuple.newTuple(String[].class));
            String text = "";
            for(int i = 0; i < tuple._1.length; i++)
                text += tuple._1[i] + ((i == text.length() - 1) ? "" : "\n");

            TEXT_AREA.setText(text);
        }
    }

    private void onChoose() {
        List list = null;
        for(List li : List.lists)
            if(li.getTag().equals(LIST.getValue()))
                list = li;
        if(list != null && list.getSubLists() != null) {
            SUB_LIST.setDisable(false);
            SUB_LIST.getItems().setAll(list.getSubLists().stream().map(name -> l.get(name._1)).collect(Collectors.toList()));
            SUB_LIST.setValue(l.get("gui.list.item.all"));
        } else {
            SUB_LIST.setDisable(true);
            SUB_LIST.getItems().clear();
        }
    }

    private void load() {
        List list = null;
        for(List li : List.lists)
            if(li.getTag().equals(LIST.getValue()))
                list = li;
        if(list == null) {
            ShakeEffect.shake(LIST);
            bar.show(l.get("gui.list.error.nolist"), 5 * 1000);
        } else if(list.getSubLists() == null)
            TEXT_AREA.setText(String.join("\n", list.getContent()));
        else
            TEXT_AREA.setText(String.join("\n", Arrays.asList(list.getSubList(SUB_LIST.getValue())._2)));
    }

    private void importList() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("List file", "*.list"));
        File f = fc.showOpenDialog(null);
        try {
            String content = FileIO.get(f.getAbsolutePath());
            TEXT_AREA.setText(content);
            bar.show(l.get("gui.list.toast.importcomplet"), 5 * 1000);
        } catch (NullPointerException e) {
            bar.show(l.get("gui.list.error.importfail"), 5 * 1000);
        }
    }

    private void exportList() {
        FileChooser fc = new FileChooser();
        ExtensionFilter extFilter = new FileChooser.ExtensionFilter("List file", "*.list");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);
        if(file != null){
            try {
                FileIO.save(TEXT_AREA.getText(), file.getAbsolutePath());
                bar.show(l.get("gui.list.toast.exportcomplet"), 5 * 1000);
            } catch (NullPointerException e) {
                bar.show(l.get("gui.list.error.exportfail"), 5 * 1000);
            }
        }
    }

    @Override
    public void saveAll()
    {
        if(ShakeEffect.isFullOrElseShake(TEXT_AREA)) {
            Tag tag = Tag.tags.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX)).getText());
            tag.setType(Functionality.getFunctionalityByDefaultName("List"));

            tag.setParameters(Tuple.newTuple(TEXT_AREA.getText().split("\n")));

            CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, new Label(tag.getType().toString()), INDEX);
            getDialog().close();
        }
    }
}
