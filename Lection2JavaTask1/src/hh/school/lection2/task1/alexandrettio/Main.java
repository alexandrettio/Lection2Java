package hh.school.lection2.task1.alexandrettio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static java.lang.System.*;

class MyComp implements Comparator<User> {
    public int compare(User a, User b) {
        if (a.commonTimeInSystem > b.commonTimeInSystem)
            return -1;
        else
            return 1;
    }

}
class User {
    public int userId;
    public int currentLoginTime;
    public int commonTimeInSystem = 0;
    public boolean isInSystem = false;

    User(int new_userId) {
        userId = new_userId;
    }
    User(int new_userId, int new_time) {
        userId = new_userId;
        commonTimeInSystem = new_time;
    }

    public void print() {
        out.print("[" + userId + "] - " + commonTimeInSystem + "\n");
    }

    public void ProcessNewNote(int time, String action) {
        if (action.equals("login")) {
            currentLoginTime = time;
            isInSystem = true;
        } else {
            commonTimeInSystem += time - currentLoginTime;
            isInSystem = false;
        }
    }
}

public class Main {
    HashMap users = new HashMap();
    public static void readFile(String filename) throws IOException {
        BufferedReader tmp = new BufferedReader(new FileReader(filename)) {
            String note = tmp.readLine();
            while (note != null) {
                String[] splitednote = note.split(", ");
                Integer userId = Integer.parseInt(splitednote[1]);
                Integer time = Integer.parseInt(splitednote[0]);
                String action = splitednote[2];

                if(users.containsKey(userId)) {
                    User tmp_user = (User) users.get(userId);
                    tmp_user.ProcessNewNote(time, action);
                    users.put(userId, tmp_user);
                }
                else
                    users.put(userId, new User(userId));

                note = tmp.readLine();
            }
        }
    }

    public static void main(String[] args) {
        Main.readFile(filename);
        Collections.sort(users, new MyComp());

        for (User element : users)
            element.print();
    }

}
