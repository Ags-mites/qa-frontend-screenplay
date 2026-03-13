package com.frontendpom.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public abstract class BaseKudoQuestion implements Question<String> {

    protected abstract Target getTarget();

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(getTarget(), isVisible()).forNoMoreThan(10).seconds()
        );
        return Text.of(getTarget()).answeredBy(actor);
    }

    public static Question<String> forTarget(Target target) {
        return new Question<String>() {
            @Override
            public String answeredBy(Actor actor) {
                actor.attemptsTo(
                        WaitUntil.the(target, isVisible()).forNoMoreThan(10).seconds()
                );
                return Text.of(target).answeredBy(actor);
            }
        };
    }
}
