package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    private CountDownTimer timer;

    @FXML private Text text;
    @FXML private Button buttonFocus;
    @FXML private Button buttonStop;


    public void handleButtonFocus(ActionEvent actionEvent) {

        text.setText(Utils.fromMillis(30000));
        buttonFocus.setText(Utils.toString(".focus", 30000));

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onFinish() {
                if (timer != null) {
                    timer.cancel();
                }
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

    public void handleButtonStop(ActionEvent actionEvent) {
        if (timer != null) {
            timer.cancel();
        }
        text.setText(Utils.fromMillis(0));
    }
}
