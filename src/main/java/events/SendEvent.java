package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.math.NumberUtils;
import parsers.ParsePictures;
import java.util.List;

public class SendEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){



        String mess = event.getMessage().getContentRaw();

        if(mess.startsWith("!help")){
            String sending = "to send 1 picture write: (#) \"!send meme\" " +
                    " to send more use \";\" after request: (#) \"!send meme ; 10\" ";
            event.getChannel().sendMessage(sending).queue();
        }

        if(mess.startsWith("!send")){

            String query;
            Integer num;

            if(event.getAuthor().isBot()) event.getChannel().sendMessage("u can't catch me").queue();
            else if(mess.equals("!send") || mess.equals("!send ")) event.getChannel().sendMessage("empty query").queue();
            else {
                if (mess.contains(";")) {

                    if (mess.substring(mess.indexOf(";") - 1, mess.indexOf(";")).contains(" "))
                        query = mess.substring(mess.indexOf(" ") + 1, mess.indexOf(";") - 1);
                    else query = mess.substring(mess.indexOf(" ") + 1, mess.indexOf(";"));

                    if (mess.substring(mess.indexOf(";")).length() > 1) {
                        String count = mess.substring(mess.indexOf(";") + 2);
                        if (NumberUtils.isDigits(count)) num = Integer.valueOf(count);
                        else num = 1;
                    } else num = 1;
                } else {
                    query = mess.substring(mess.indexOf(" ") + 1);
                    num = 1;
                }

                var parser = new ParsePictures();
                parser.setUrl(query);

                List<String> urls = parser.parsing(num);

                for (String url : urls) System.out.println(url);

                if (num > 1) {
                    for (int i = 0; i < num + num; i++) {
                        if (i >= urls.size()) {
                            event.getChannel().sendMessage("выведено: " + urls.size() / 2 + " ").queue();
                            break;
                        }
                        event.getChannel().sendMessage(urls.get(i)).queue();
                    }
                } else {
                    event.getChannel().sendMessage(urls.get(0)).queue();
                    if(urls.size() > 1 )event.getChannel().sendMessage(urls.get(1)).queue();
                }
            }
        }

    }

}

