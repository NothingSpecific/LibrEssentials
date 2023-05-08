# LibrEssentials
A drop-in replacement for EssentialsX, reimplemented from scratch.

EssentialsX has been warning server admins who try to install No Chat Reports and Essentials on the same server. Originally they tried to claim it was simple incompatibility, but in their code they [moved No Chat Reports from `serverUnsupportedDangerous` to `serverUnsupportedDumb`](https://github.com/EssentialsX/Essentials/blob/0936fe80bd7426b0e002485163d026d5134d0c65/Essentials/src/main/java/com/earth2me/essentials/commands/Commandessentials.java#L796) ([SupportStatus.STUPID_PLUGIN](https://github.com/EssentialsX/Essentials/blob/bf14b88600601019fb51dd6e6a1524e411e9b339/Essentials/src/main/java/com/earth2me/essentials/utils/VersionUtil.java#L66)). I took this as an admission that compatibility was never their concern.

**It's your server, you should be able to disable Draconian features like chat reporting.** The developers of a completely unrelated plugin shouldn't be able to tell you what plugins to use.

LibrEssentials blocks chat reporting by default, but this feature can be toggled in the config or through ingame commands. By rewriting player messages as system messages, users choosing to only show secure messages will still be able to see unsigned message content, and the game's built-in fearmongering around unverified messages will be toned down a bit.

This plugin will not force you to disable chat reports, but it does assume that chat reports should be opt-in for servers that want the protection, not opt-out for servers that don't. See [BetterNCR/README.md](https://github.com/NothingSpecific/BetterNCR/blob/main/README.md) for more information about global chat reporting and why you should consider keeping it disabled on your server.

---

## Building

This project is developed using Eclipse Java. Since the classpath contains system-specific file paths, I created an `init.py` script that rewrites system-specific classpath information.

Before opening this project in Eclipse, you should make sure to run `init.py`. After asking you for the location of your Spigot shaded API JAR file, it will automatically fix the project's classpath to work on your system.

### A note for Windows developers

I develop on a GNU+Linux system and have not tested the build process on a Windows development system. But I'm sure you can figure it out. If `init.py` needs to be updated to work on Windows systems, submit a PR and I'll look into officially adding support for development on Windows systems.

*Note: This plugin is licensed under the [GNU AGPL 3.0 license](https://www.gnu.org/licenses/agpl-3.0.en.html), but can also be licensed under [BSD 3-clause](https://opensource.org/license/BSD-3-clause/) upon request. For anyone considering contributing to this project, please understand that your contributions may be distributed under [AGPLv3](https://www.gnu.org/licenses/agpl-3.0.en.html) or [BSD 3-clause](https://opensource.org/license/BSD-3-clause/).*
