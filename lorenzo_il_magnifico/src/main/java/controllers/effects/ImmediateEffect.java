package controllers.effects;

import controllers.game_course.phases.Action;
import models.Points;
import models.Resources;

/**
 * Created by starivore on 5/7/17.
 */
public class ImmediateEffect {
    private Resources resources;
    private Points points;
    private Action bonusAction;

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Action getBonusAction() {
        return bonusAction;
    }

    public void setBonusAction(Action bonusAction) {
        this.bonusAction = bonusAction;
    }
}
