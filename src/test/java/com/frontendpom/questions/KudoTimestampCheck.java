package com.frontendpom.questions;

import com.frontendpom.ui.KudosListPage;
import net.serenitybdd.screenplay.Question;

public class KudoTimestampCheck {

    public static Question<String> value() {
        return BaseKudoQuestion.forTarget(KudosListPage.LABEL_FECHA_KUDO);
    }
}
