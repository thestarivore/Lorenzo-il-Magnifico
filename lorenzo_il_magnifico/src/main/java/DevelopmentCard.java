/**
 * Created by starivore on 5/7/17.
 */
public class DevelopmentCard extends Card{
    private Period period;
    private Resources cost;
    private ImmediateEffect immediateEffect;
    private PermanentEffect permanentEffect;

    public Period getPeriod() {
        return period;
    }

    public Resources getCost() {
        return cost;
    }

    public ImmediateEffect getImmediateEffect() {
        return immediateEffect;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }
}
