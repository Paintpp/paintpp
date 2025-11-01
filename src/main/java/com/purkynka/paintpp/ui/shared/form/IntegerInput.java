package com.purkynka.paintpp.ui.shared.form;

import atlantafx.base.theme.Styles;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.Duration;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class IntegerInput extends TextField {
    private final Tooltip tooltip;
    private final int maxValue;
    private final int minValue;
    
    private Timer tooltipWarningTimer;
    private boolean tooltipWarningTimerScheduled = false;
    
    /**
     * Constructs a new {@link IntegerInput}.
     * @param defaultValue The default value of the text field
     */
    public IntegerInput(int minValue, int maxValue, int defaultValue) {
        super();
        this.maxValue = maxValue;
        this.minValue = minValue;
        setText(Integer.toString(defaultValue));
        
        this.tooltip = new Tooltip(String.format("Enter a number between %d and %d", minValue, maxValue));
        setTooltip(tooltip);
        
        setOnKeyReleased(_ -> {
            if (!isInputValid())
                showTooltipFor(Duration.ofSeconds(3));
        });
        focusedProperty().addListener((_, _, newValue) -> {
            if (!newValue) 
                isInputValid();
        });
        setOnMouseExited(_ -> isInputValid());
        
        setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getText().matches("^[0-9]*$")) {
                return change;
            }
            return null;
        }));
    }
    
    private boolean isInputValid() {
        if (getText().isEmpty()) {
            setError("Invalid input can't be empty");
            return false;
        }
        
        if (Integer.parseInt(getText()) < minValue) {
            setError(String.format("Invalid input can't be less than %d", minValue));
            return false;
        } 
        
        if (Integer.parseInt(getText()) > maxValue) {
            setError(String.format("Invalid input can't be more than %d", maxValue));
            return false;
        } 
        
        clearError();
        if (tooltipWarningTimer != null & tooltipWarningTimerScheduled) {
            tooltipWarningTimer.cancel();
            tooltipWarningTimerScheduled = false;
            tooltip.hide();
        }
            
        // Remove leading zeros
        var formatedText = getText().replaceFirst("^0+", "");
        if (!getText().equals(formatedText)) {
            setText(formatedText);
            positionCaret(getText().length());
        }
        
        return true;
    }

    private void setError(String message) {
        tooltip.setText(message);
        pseudoClassStateChanged(Styles.STATE_DANGER, true);
    }

    private void clearError() {
        tooltip.setText(String.format("Enter a number between %d and %d", minValue, maxValue));
        pseudoClassStateChanged(Styles.STATE_DANGER, false);
    }

    private void showTooltipFor(Duration duration) {
        tooltip.show(
                this,
                localToScreen(getBoundsInLocal()).getMinX(),
                localToScreen(getBoundsInLocal()).getMaxY()
        );
        hideTooltipAfter(duration);
    }
    
    private void hideTooltipAfter(Duration duration) {
        if (tooltipWarningTimer != null) tooltipWarningTimer.cancel();
        
        tooltipWarningTimer = new Timer();
        tooltipWarningTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(tooltip::hide);
                tooltipWarningTimerScheduled = false;
            }
        }, duration.toMillis());
        tooltipWarningTimerScheduled = true;
    }

    public void checkInput() {
        if (!isInputValid())
            showTooltipFor(Duration.ofSeconds(3));
    }
    
    /**
     * Returns the currently written number or 0 for none input.
     * @return The written number
     */
    public int getValue() {
        var value = getText();
        return Integer.parseInt(Objects.equals(value, "") ? "0" : value);
    }
    
    public void setValue(int value) {
        setText(String.valueOf(value));
    }
}
