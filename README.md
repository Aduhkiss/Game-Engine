# Game Engine Plugin for Minecraft

A core plugin designed for personal use on private Minecraft Survival Multiplayer (SMP) servers. This engine provides essential features and utilities to enhance server management and gameplay experience for trusted friends and family.

## Overview
- **Purpose:** Centralize and streamline custom server logic for private SMPs.
- **Scope:** Not intended for public distribution—tailored for small, trusted communities.

## Features
- Modular architecture for easy extension
- Custom gameplay mechanics (e.g., pet systems, anti-crop-trample, etc.)
- Core utilities for server moderation and quality of life
- Lightweight and performance-focused

## Installation
1. Build the plugin using your preferred Java build tool (e.g., Gradle or Maven).
2. Place the generated `.jar` file into your server's `plugins` directory.
3. Restart or reload your Minecraft server.

## Configuration
- Edit `plugin.yml` and any config files in `src/main/resources` as needed for your server setup.
- Most features are enabled by default but can be toggled or configured via config files.

## Usage
- Commands and features are designed for operators and trusted players.
- Refer to plugin help commands in-game or review command classes under `src/main/java/family/zambrana/engine` for available functionality.

## Development
- Java 17+
- Designed for Spigot/PaperMC APIs
- Modular codebase: add new features by extending core classes or adding new modules under the `engine` package.

## License
Personal/private use only. Not for redistribution or commercial use.

---

*Created for private SMP enjoyment. Happy crafting!*
