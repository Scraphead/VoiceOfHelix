package voice.of.helix;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;

import static voice.of.helix.PokemonInput.KeyCommand.*;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Creates JFrame with program and adds panels.
 * Converts user input to PokemonInput Arraylist that can be analysed.
 */
public class VoiceOfHelix extends JPanel implements MessageListener {

    public LinkedList<PokemonInput> inputList = new LinkedList<>();

    TwitchChatPanel twitchChatPanel;
    LineGraphPanel lineGraphPanel;

    public VoiceOfHelix() {
        setLayout(new BorderLayout(0, 0));
        init();

        add(twitchChatPanel, BorderLayout.EAST);
        add(lineGraphPanel, BorderLayout.WEST);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateData();
            }
        }, 500, 500);

    }

    private void updateData() {
        lineGraphPanel.updateGraph();
        long time = System.currentTimeMillis();
        synchronized (inputList) {
            //Remove items older then 60 sec
            while (inputList.size() > 0 && inputList.getFirst().timeInput + 60000 < time) {
                inputList.removeFirst();
            }

        }
    }

    private void init() {
        twitchChatPanel = new TwitchChatPanel(this);
        lineGraphPanel = new LineGraphPanel(inputList);
    }

    @Override
    public void onMessage(String chatMessage) {
        String message = chatMessage.toLowerCase().trim();
        switch (message) {
            case "a":
                addInput(A);
                break;
            case "b":
                addInput(B);
                break;
            case "up":
                addInput(UP);
                break;
            case "down":
                addInput(DOWN);
                break;
            case "left":
                addInput(LEFT);
                break;
            case "right":
                addInput(RIGHT);
                break;
            case "start":
                addInput(START);
                break;
            case "select":
                addInput(SELECT);
                break;
            case "anarchy":
                addInput(ANARCHY);
                break;
            case "democracy":
                addInput(DEMOCRACY);
                break;
            default:
                if (message.contains("helix") && message.length() < 60) {
                    lineGraphPanel.prayersToHelix(chatMessage);
                }
                break;
        }
    }

    synchronized void addInput(PokemonInput.KeyCommand keyCommand) {
        synchronized (inputList) {
            PokemonInput input = new PokemonInput();
            input.keyCommand = keyCommand;
            input.timeInput = System.currentTimeMillis();
            inputList.add(input);
        }
    }

    public static void main(String[] args) {
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VoiceOfHelix helix = new VoiceOfHelix();
                JFrame frame = new JFrame();
                frame.setTitle("Voice of Helix");
                try {
                    frame.setIconImage(ImageIO.read(new URL("http://cdn.bulbagarden.net/upload/4/47/Bag_Helix_Fossil_Sprite.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.getContentPane().add(helix);
                frame.setVisible(true);
                frame.pack();
            }
        });
        NativeInterface.runEventPump();
    }

}