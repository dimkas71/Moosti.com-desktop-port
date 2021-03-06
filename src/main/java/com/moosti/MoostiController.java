package com.moosti;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


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

        buttonFocus.textProperty().bind(Bindings.format(Config.getInstance().getButtonFocusText() + "(%2.0f)",
                sliderFocus.valueProperty()));
        buttonShortBreak.textProperty().bind(Bindings.format(Config.getInstance().getButtonShortBreakText() + "(%2.0f)",
                sliderShortBreak.valueProperty()));
        buttonLongBreak.textProperty().bind(Bindings.format(Config.getInstance().getButtonLongBreakText() + "(%2.0f)",
                sliderLongBreak.valueProperty()));

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
                Platform.runLater(() -> text.setText(Utils.fromMillis(0)));
                play();
            }
            @Override
            public void onTick(long millisUntilFinished) {
                Platform.runLater(() -> text.setText(Utils.fromMillis(millisUntilFinished)));
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

    private void play() {
        try {
            Player player = new Player(getClass().getResourceAsStream("ring_end.mp3"));
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }

}
