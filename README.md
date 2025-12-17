# intellij status plugin

This Plugin for Intellij adds a status marker icon and/or a text behind the class name in the project view based on comment tags.

## Features
- Adds a status marker icon to the class name in the project view based on comment tags.
- Adds a status text behind the class name in the project view based on comment tags.

## Usage
- Add a comment tag to the class you want to mark
- The plugin will automatically detect the comment tag and add the status marker icon and/or text to the class name in the project view.
- The plugin supports the following comment tags:
  - `// STATUS: DONE`
  - `// STATUS: WIP`
  - `// STATUS: TODO`
  - `// STATUS: INACTIVE`
  - `// STATUS: TEST`
  - `// STATUS: BUG`
- To show the icon you need to add `// ICON`
- To exclude a file from the plugin you can add `// EXCLUDE FROM STATUS` to the file

## Installation
1. Clone the repository
2. Build the plugin using the following command:
   ```bash
   ./gradlew buildPlugin
   ```
3. Install the plugin in Intellij by going to `File -> Settings -> Plugins -> Install Plugin from Disk` and selecting the built plugin file in build/discributions.