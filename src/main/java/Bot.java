import events.SendEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("OTAxMTA3Mjc4MDA1MDIyNzcw.YXLDjA._Gepnuvz6twFxdZh-gi7iTBxtCw").build();

        jda.addEventListener(new SendEvent());
    }

}
