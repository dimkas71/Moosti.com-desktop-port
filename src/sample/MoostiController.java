package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * Created by Dimkas71 on 17.06.2015.
 */
public class MoostiController {

    private CountDownTimer timer;

    @FXML private Text text;
    @FXML private Button buttonFocus;
    @FXML private Button buttonShortBreak;
    @FXML private Button buttonLongBreak;
    @FXML private Button buttonStop;

    @FXML private Slider sliderFocus;
    @FXML private Slider sliderShortBreak;
    @FXML private Slider sliderLongBreak;

    /**
     * Setup bindings between a button's textProperty and a slider's valueProperty
     */

    @FXML
    public void initialize() {

        buttonFocus.textProperty().bindBidirectional(sliderFocus.valueProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number number) {

                return Config.getInstance().getButtonFocusText() + "("
                        +String.format("%2.0f", (Double)number).trim() + ")";
            }

            @Override
            public Number fromString(String s) {
                return null;
            }
        });

        buttonShortBreak.textProperty().bindBidirectional(sliderShortBreak.valueProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number number) {

                return Config.getInstance().getButtonShortBreakText() + "("
                        + String.format("%2.0f", (Double)number).trim() + ")";
            }

            @Override
            public Number fromString(String s) {
                return null;
            }
        });

        buttonLongBreak.textProperty().bindBidirectional(sliderLongBreak.valueProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number number) {

                return  Config.getInstance().getButtonLongBreakText() + "(" +
                        String.format("%2.0f", (Double)number).trim() + ")";
            }

            @Override
            public Number fromString(String s) {
                return null;
            }
        });


    }

    public void handleButtonFocus(ActionEvent actionEvent) {
        handleButtonAction(sliderFocus);
    }

    private void handleButtonAction(Slider slider) {
        stopTimer();

        int minutes = (int)Math.round(slider.getValue());

        text.setText(Utils.fromMillis(minutes * 60 * 1000));

        timer = new CountDownTimer(minutes * 60 * 1000, 1000) {
            @Override
            public void onFinish() {
                stopTimer();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(Utils.fromMillis(0));
                    }
                });
            }
            @Override
            public void onTick(long millisUntilFinished) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(Utils.fromMillis(millisUntilFinished));
                    }
                });
            }
        };
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void handleButtonShortBreak(ActionEvent actionEvent) {
        handleButtonAction(sliderShortBreak);
    }

    public void handleButtonLongBreak(ActionEvent actionEvent) {
        handleButtonAction(sliderLongBreak);
    }

    public void handleButtonStop(ActionEvent actionEvent) {

        stopTimer();
        text.setText(Utils.fromMillis(0));

    }

}
