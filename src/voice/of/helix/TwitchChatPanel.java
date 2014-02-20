package voice.of.helix;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Displays Connect button and twitch chat. Injects javascript to transfer chat to java.
 *
 * Created by Scraphead on 18.02.14.
 */
public class TwitchChatPanel extends JPanel {
    MessageListener listener;

    public TwitchChatPanel(MessageListener listener) {
        super(new BorderLayout());
        this.listener = listener;
        initTwitchChatPanel();
    }

    public void incomingChatMessage(String message) {
        listener.onMessage(message);
    }

    private void initTwitchChatPanel() {
        JPanel webBrowserPanel = new JPanel();

        final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setBarsVisible(false);
        webBrowser.setLocationBarVisible(true);
        webBrowser.setButtonBarVisible(true);
        webBrowser.setPreferredSize(new Dimension(360, 600));
        webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
            @Override
            public void commandReceived(WebBrowserCommandEvent e) {
                Object[] parameters = e.getParameters();
                if (parameters.length > 0 && parameters[0] != null) {
                    incomingChatMessage(parameters[0].toString());
                }

            }
        });

        webBrowser.navigate("http://www.twitch.tv/chat/embed?channel=twitchplayspokemon&popout_chat=true");
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);

        final String defaultJavascript = "CurrentChat.line_buffer = 400; \n" +
                "    $('#chat_line_list li:not(.sentToJava)').each(function () {\n" +
                "        var a = $(this);\n" +
                "        a.addClass('sentToJava');\n" +
                "    });\n" +
                "setInterval(function () {\n" +
                "    $('#chat_line_list li:not(.sentToJava)').each(function () {\n" +
                "        var a = $(this),\n" +
                "                cText = a.find('.chat_line').text();\n" +
                "        sendNSCommand('store',cText);\n" +
                "        a.addClass('sentToJava');\n" +
                "    });\n" +
                "}, 500);";
        JPanel connectPanel = new JPanel(new BorderLayout());
        //connectPanel.add(label, BorderLayout.EAST);
        JButton executeJavascriptButton = new JButton("CONNECT");
        try {
            executeJavascriptButton.setIcon(new ImageIcon(new URL("http://cdn.bulbagarden.net/upload/4/47/Bag_Helix_Fossil_Sprite.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        executeJavascriptButton.setFont(new Font(executeJavascriptButton.getFont().getFamily(), Font.BOLD, 30));
        executeJavascriptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                webBrowser.executeJavascript(defaultJavascript);
            }
        });
        connectPanel.add(executeJavascriptButton, BorderLayout.SOUTH);
        add(executeJavascriptButton, BorderLayout.NORTH);
    }


}
