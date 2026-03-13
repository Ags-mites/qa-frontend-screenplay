package com.frontendpom.questions;

import com.frontendpom.ui.KudosListPage;
import net.serenitybdd.screenplay.Question;

public class KudoDisplayedMessage {

    public static Question<String> value() {
        return BaseKudoQuestion.forTarget(KudosListPage.LABEL_MENSAJE_KUDO);
    }
}
