
                     MMMMMMMMMM
                  MMM     ZZ   ZZMM
                MM                 M
             MMZ          ZZZZZ    ZMM
          MMM  Z         ZZZ   ZZ   MM
        MM   ZZ   +++++  Z  ZZ ZZ   ZZMM     Voice of helix
        MM   ZZ+         Z  ZZZ     ZZMM
        MM   MM   +++++  Z            MM         https://github.com/Scraphead/VoiceOfHelix
        MM   MM ++        ZZ            M
        MM   MM+         ZMM            M
        MM     Z    ZZZ     MM          M
        MM+    M  ZZ   ZZZZZMM        ++M
        MM ++   MM  ZZZZZZ         +    M
          M  ++ MMZZZZZ          ++     M
          M    +              +++   MMMM
           MM  +            ++     M
           MM   ++        ++       M
             MM   +++++++      MMMM
               M              M
                MM     MMMMMMM
                  MMMMM

                                        
Voice of helix reads the chatlog from http://www.twitch.tv/twitchplayspokemon and display command given over time.<br>

It gives an insight of where the player is headed, and may give you an idea of how to improve (or reduce) gameplay.<br>

Press CONNECT button when chat has loaded, this injects the javascript needed to fetch messages from chat.<br>
The slider at top controls the black line in the graphs. With it you can estimate stream delay.<br>
By default it is set to 20 sec. You have to figure out yourself what your delay is with a stopwatch.<br>
Time the delay from when you enter a command until your name shows up on stream.<br>

This program will not read all statements when twitch chat chokes during spam.<br>

===============

Running program:<br>
Download correct jar file for your system at tinyurl.com/voiceOfHelix<br>
This program has only been tested at 64 bit windows and 64 bit linux. I can't guarantee the others will work.<br>
Please tell me if they don't work and show me the console output.<br>

Windows:<br>
Run by double clicking file or run from command prompt<br>
cd C:\Users\YOUR_DOWNLOAD_LOCATION<br>
java -jar voiceOfHelixWindows64.jar<br>

If you use 32bit java on 64 bit windows, I assume you need windows32. (Not tested)<br>

Linux:<br>
Make sure .jar file has permission to be run as executable, or type in terminal<br>
cd /home/YOUR_DOWNLOAD_LOCATION<br>
java -jar voiceOfHelixLinux64.jar<br>

Mac:<br>
May the helix guide you on your path, you are on your own.<br>

============

Compiling your own:<br>
I assume you have some experience in compiling java and adding libraries.<br>
To compile this program you need these libraries with the voice of helix source file<br>

jFreeChart Graphs: http://www.jfree.org/jfreechart/download.html<br>

djSwing Java browser: http://djproject.sourceforge.net/ns/<br>
From jfreechar add jcommons-1.0.xx.jar and jfreechart-1.0.xx.jar to path<br>

From djswing add DJNativeSwing.jar DJNativeSwing-SWT.jar<br>
Make sure you also add all libraries in subfolder /djSwing/lib<br>

The java browser also require a system specific swt package.<br>
Replace djSwing/lib/swt/swt-4.3-win32-win32-x86.jar<br>
with your operating system swt.jar from http://www.eclipse.org/swt/<br>
