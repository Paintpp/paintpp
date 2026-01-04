package com.purkynka.paintpp.ui.stage.about;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.Paintpp;
import com.purkynka.paintpp.logic.util.ResourceUtil;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;

public class AboutPopupStage extends PopupStage {
    public AboutPopupStage() {
        super();

        this.setPopupTitle("About");
        this.setShowTitle(true);
        this.addDefaultSubmitButton("Ok", this::close);
    }

    private TextFlow createTeamMemberName(String actualName, String githubName, String githubLink) {
        return new TextFlow(
                new Label(String.format("%s (", actualName)),
                this.createHyperLink(githubName, githubLink),
                new Label(")")
        );
    }

    private Hyperlink createHyperLink(String text, String link) {
        var hyperlink = new Hyperlink(text);
        hyperlink.setOnAction(_ -> {
            Paintpp.HOST_SERVICES.showDocument(link);
        });

        return hyperlink;
    }

    private Label createQuote(String quoteText) {
        var quote = new Label(String.format("„ %s ”", quoteText));
        quote.setPadding(new Insets(0, 0, 8, 0));
        quote.getStyleClass().add(Styles.TEXT_SUBTLE);

        return quote;
    }

    @Override
    protected Parent createRoot() {
        var logoImage = new Image(ResourceUtil.getResource("/image/logo.png"), 128, 128, true, true);
        var logoImageViewer = new ImageView(logoImage);

        var projectNameLabel = new Label("Paint++");
        projectNameLabel.getStyleClass().add("about-project-name");

        var projectDescriptionLabel = new Label("Where's the paint?");
        projectDescriptionLabel.getStyleClass().add("about-project-description");

        var projectInfoContainer = new VBox(logoImageViewer, projectNameLabel, projectDescriptionLabel);
        projectInfoContainer.setAlignment(Pos.CENTER);

        var createdBy = new Label("Created by:");
        createdBy.setPadding(new Insets(16, 0, 16, 0));
        createdBy.getStyleClass().add("about-created-by");

        var teamInfoContainer = new VBox(
                this.createTeamMemberName("Source available:", "https://github.com/paintpp/paintpp", "https://github.com/paintpp/paintpp"),
                createdBy,
                this.createTeamMemberName("David Dvořák", "@userNu11ified", "https://github.com/userNu11ified"),
                this.createQuote("JavaFX should burn."),
                this.createTeamMemberName("Daniel Malík", "@TheAfky", "https://github.com/TheAfky"),
                this.createQuote(":D"),
                this.createTeamMemberName("Ondřej Kutlvašr", "@Ondatraa380", "https://github.com/Ondatraa380"),
                this.createQuote("I did not use AI!"),
                this.createTeamMemberName("Jaroslav Dvořáček", "@Nejmicekk", "https://github.com/Nejmicekk"),
                this.createQuote("404 member not found.")
        );

        var testRatImage = new Image(ResourceUtil.getResource("/image/rat.jpg"), 192, 192, true, true);
        var testRatImageView = new HBox(new ImageView(testRatImage));
        testRatImageView.setPadding(new Insets(0, 0, 16, 0));

        var clipRectangle = new Rectangle(testRatImage.getWidth(), testRatImage.getHeight());
        clipRectangle.setArcHeight(16);
        clipRectangle.setArcWidth(16);
        testRatImageView.setClip(clipRectangle);

        var testRatName = new Label("Test Rat");
        testRatName.getStyleClass().add("about-test-rat-name");

        var testRatDescription = new Label("Best tester ever!");
        testRatDescription.getStyleClass().add("about-test-rat-description");

        var testRatContainer = new VBox(testRatImageView, testRatName, testRatDescription);
        testRatContainer.setAlignment(Pos.CENTER);

        var container = new HBox(64, projectInfoContainer, teamInfoContainer, testRatContainer);;
        container.setPadding(new Insets(0, 32, 0, 32));

        return container;
    }
}
