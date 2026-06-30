package movie_ticket_booking_design.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Theatre {
    private final Integer id;
    private final String name;
    private final Map<Integer, Screen> screens;

    public Theatre(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.screens = new ConcurrentHashMap<Integer, Screen>();
    }

    public Integer getId() {
        return id;
    }

    public Map<Integer, Screen> getScreens() {
        return screens;
    }

    public String getName() {
        return name;
    }

    public void addScreen(Screen screen) {
        if (!screens.containsKey(screen.getId())) {
            screens.put(screen.getId(), screen);
        }
    }
}
