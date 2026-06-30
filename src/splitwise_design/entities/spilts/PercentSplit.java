package splitwise_design.entities.spilts;

import splitwise_design.entities.User;

public class PercentSplit extends Split {
    private final double percent;
    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
