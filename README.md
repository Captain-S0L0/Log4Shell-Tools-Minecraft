# Log4Shell Tools For Minecraft

Some programs to generate and provide Log4Shell payloads working with Minecraft. Built to work with Java 8 or newer.

## Disclaimer

These resources are provided for educational purposes and to use on one's own systems only. Do not attempt to access other people's systems without their consent. You take full responsibility in ensuring the security of your own systems while using these programs. YOU HAVE BEEN WARNED!

## Usage

Minecraft, like innumerable other Java programs, has used the Log4J logging library since 2013. Many game versions are indeed vulnerable to CVE-2021-44228 (Log4Shell). All affected clients have had dependency updates pushed by Mojang, but all affected server jars are still vulnerable. The vulnerable versions are release 1.7 (13w39a) to release 1.18 (1.18.1 Release Candidate 2). You can also manually downgrade your client's manifest to use the vulnerable version of Log4J.

You also will need to use a vulnerable Java version. If your Java version is not Java 6u201 and below, Java 7u191 and below, Java 8u181 and below, a Java 9 version, a Java 10 version, or Java 11.0.0, you will need to specify the Java command line argument `-Dcom.sun.jndi.ldap.object.trustURLCodebase=true` on the client if you are attacking the integrated server, or the server if you are attacking the dedicated server. I recommend performing Log4Shell on a dedicated server, but there isn't anything stopping you from performing it on an integrated server.

To download the tools, download the latest release and unzip it.

To run the LDAP and HTTP server, ensure you have Java 8 or newer installed. Then run `java -cp log4shelltools.jar xyz.terriblefriends.log4shelltools.HTTPServer [<port>]` and `java -cp log4shelltools.jar xyz.terriblefriends.log4shelltools.LDAPServer <URL> [<port>]` to start the HTTP and LDAP servers, respectively. Alternatively, run either "run.bat" if you are on Windows or "run.sh" if you are on Linux to start HTTP on port 8000 and LDAP on port 1389.




I recommend the usage of MultiMC if you are going to be downgrading client manifests. You can download it [here](https://multimc.org/).

Most of the provided example payloads will only work on the '''vanilla 1.12 dedicated server'''. If you want to use Log4Shell on another version, you will need to create your own payloads. You will probably need to know some Java to do so.

To create your own payloads, I recommend the usage of the Mod Coder Pack (MCP). You can download it [here](https://minecraft.wiki/w/Tutorial:Programs_and_editors/Mod_Coder_Pack#Downloads). The following instructions are a high level overview of how to create a payload with MCP.

1) Download and extract MCP.
2) Install the Java 8 Development Kit (JDK), and add it to the PATH system environment variable. Make sure that if you start a new terminal and run `java -version`, it says Java 8.
2) Download the server jar for the corresponding Minecraft version. You can usually find the download link for it on the Minecraft Wiki page for the version. Place it into the "jars" folder and rename it to the name specified in "docs/README-MCP.TXT".
3) Run the decompile script (.bat if on Windows, .sh if on Linux).
4) Create your script in the "src/minecraft_server" folder. You may want to use an IDE for this. Both IntelliJ and Eclipse should work with the provided "eclipse" workspace folder.
5) Recompile with the recompile script.
6) If recompilation succeeds, use the reobfuscate script to obfuscate your class file.
7) Your generated class file will be in the "reobf/minecraft_server" folder.

You can also use MCP-Reborn for versions 1.13 and above. You can download it [here](https://github.com/Hexeption/MCP-Reborn). I don't have any instructions for it because I have never used it.

More info here:
https://mcdf.wiki.gg/wiki/User:Captain_S0L0/Log4Shell
