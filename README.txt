=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: psuhani7
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. File I/O

  To facilitate pausing, resuming, and saving gameplay in the Snake game, I have implemented File I/O functionalities
  that enable players to pause, save and resume their progress seamlessly. The code contains implemented methods
  dedicated to reading and writing game state information from and to files. Users can easily save their current game
  state, pause, resume, quit, or ask for help in the game using buttons on the game window or keyboard shortcuts.
  Whenever a game is saved, a dialog box appears with a statement regarding whether the game was saved successfully.

  On the start of the game, the Main Menu window pops up and provides buttons using which the users can select to
  load a previously saved game or restart the game from start. During "load game" initialization, the system checks for
  existing game state files. If found, it will allow the player to load the previous session's game state,
  facilitating seamless continuation of the game.

  The game uses the JSON file format to comprehensively store critical game state details like the positions of the
  snake and fruit, and the direction that the snake was last moving in.

  2. 2D Arrays

  Incorporating 2D arrays to represent the snake's body segments brings efficiency and flexibility to the game.
  Key features include:

    1. Efficient Snake Representation: The 2D array acts as a grid, efficiently representing the snake's body segments
    in rows and columns within the game environment.
    2. Dynamic Movement: The game logic allows the snake to move within the 2D array by updating positions as the snake
    navigates through the grid. This enables smooth and responsive movement within the game.
    3. Growth Mechanism: Upon consuming a fruit, the snake's size increases by elongating in the grid, adhering to the
    game's rule of growth. New segments are intelligently added to the array to accommodate the snake's extended length.

  Why 2D Arrays are an Appropriate Concept:
    1. Efficient Data Structure: Utilizing 2D arrays optimizes memory usage and enhances performance for tracking the
    snake's position and movement throughout the game grid.
    2. Dynamic Size Management: Unlike linked structures, 2D arrays efficiently manage the snake's segments without the
    need for complex resizing operations. This makes the snake's movement and growth more manageable within the game
    logic.
    3. Simplified Movement Algorithm: The implementation of the snake's movement in the 2D array simplifies the
    algorithm, allowing for a clearer and more straightforward codebase for handling directional changes and collision
    detection.

  Feedback Incorporation:
    Following the feedback regarding the complexity of collections, the transition from LinkedList to 2D arrays was
    made to streamline the implementation while ensuring efficient management of the snake's body segments. The use of
    2D arrays simplifies the logic while maintaining the integrity of the game's mechanics, allowing for a more
    straightforward and optimized solution for representing the snake within the game grid.

  3.  Inheritance and Subtyping

  Incorporating inheritance and subtyping principles facilitates the management of game entities within the Snake
  Game. Key features include:

    1. Abstract Class GameEntity: Serves as the base for all entities (e.g., Snake, Fruit) encapsulating shared
    properties (position, collision handling, painting components) to avoid code redundancy.
    2. Interfaces Movable and Collidable: Offer a flexible design for entities. Movable defines movement behaviors,
    while Collidable defines collision behaviors, enabling entities to have varied behaviors without rigid inheritance.
    3. Specific Class Implementations:
      Snake class inherits from GameEntity, implementing both Movable and Collidable. It contains specific logic for
      snake movement and collision handling.
      Fruit class inherits from GameEntity, implementing Collidable with distinct collision detection logic.
      SnakeGameBorder class inherits from GameEntity, implementing Collidable with custom collision logic that impacts
      the game differently, potentially ending the game.
      Dynamic Dispatch: Leveraging interfaces and the GameEntity abstract class, method invocations like move() or
      willCollide() are executed with specific implementations corresponding to the object type (Snake, Fruit, or
      SnakeGameBorder).

  Why Inheritance and Subtyping are Appropriate Concepts:
    1. Reduced Code Redundancy: By centralizing shared functionality within the abstract class GameEntity, the
    codebase avoids redundancy and allows for a more manageable and organized structure for common behaviors among
    entities.
    2. Flexible Entity Behaviors: Implementing interfaces (Movable and Collidable) enables diverse behaviors without
    imposing strict inheritance, allowing entities to define specific behaviors and interactions.
    3. Polymorphic Behavior: Utilizing dynamic dispatch ensures that method invocations execute specific
    implementations based on the object type, enabling entities to handle movement and collisions differently based
    on their specific characteristics.

  4. JUnit Testable Component

  Implementing a JUnit testable component for collision detection in the Snake Game is crucial for ensuring gameplay
  accuracy and reliability. Key features include:

    1. Focused Testing on Collision Detection: The JUnit tests specifically target the collision detection mechanism
    within the game, addressing scenarios where the snake interacts with obstacles or consumes fruits.
    2. Separation of Concerns: The collision detection logic is designed as a separate concern, detached from the
    graphical user interface (GUI) components. This separation ensures that the collision detection functionality can
    be rigorously tested independently, without relying on GUI interactions.
    3. Distinct Test Objectives: Each JUnit test focuses on different aspects of collision detection, ensuring
    comprehensive coverage and uniqueness in testing scenarios:
      Boundary Collisions: Testing collisions with walls or boundaries.
      Fruit Collisions: Assessing collision detection when the snake interacts with fruits.
      Self-Collision: Checking collisions between the snake's head and its own body segments.
      Snake Movement Validation: Verifying the correctness of snake movement according to collision detection results.
      Snake Display: Validating the display mechanisms regarding collision outcomes.
      Why a JUnit Testable Component is an Appropriate Concept:
      Ensured Gameplay Integrity: Rigorous testing of collision detection using JUnit ensures the reliability and
      accuracy of gameplay mechanics, minimizing unexpected behaviors or bugs related to collisions during gameplay.

    4. Isolated Testing Environment: By separating the collision detection logic from GUI components, JUnit tests can
    be executed independently, providing a controlled environment for focused testing without external dependencies.

    5. Comprehensive Test Coverage: The distinct test objectives ensure comprehensive coverage of various collision
    scenarios, validating different collision outcomes, and ensuring the reliability of the collision detection
    mechanism in diverse situations.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to Gradescope, named
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

1. GameEntity Abstract Class:
  - Function: Serves as the base for all entities in the game (e.g., Snake, Fruit).
  - Responsibilities:
  - Encapsulates shared properties and methods like position, collision prediction, detection, handling, and painting
    components.
  - Reduces code redundancy by centralizing common functionality among entities.

2. CheckeredBackground:
  - Function: Represents the game court background with a checkered appearance.
  - Responsibilities: Renders the visual background of the game court with a checkered pattern.

3. Fruit:
  - Function: Manages fruit within the game.
  - Responsibilities: Paints a fruit on the screen at random locations. Handles collision detection and actions when
  the snake interacts with the fruit.

4. HelpPopUp:
  - Function: Displays a dialog box for the help menu.
  - Responsibilities: Creates and manages a dialog box containing instructions on how to play the game.

5. MenuScreen:
  - Function: Creates the main menu interface.
  - Responsibilities: Generates the main menu with options to start the game, load a game, access the help menu, or
  quit.

6. Snake:
  - Function: Represents and manages the snake entity.
  - Responsibilities: Implements methods for drawing, positioning, moving, setting direction, and handling collision
  detection for the snake entity.

7. SnakeGame:
  - Function: Main class responsible for game initialization and display.
  - Responsibilities: Draws the game court and displays the game window. Manages functionality for pause/resume,
  saving, quitting, and accessing the help menu at the top of the game window.

8. SnakeGameBorder:
  - Function: Represents the boundaries or borders of the Snake Game.
  - Responsibilities: Handles specific collision logic related to the game borders, potentially impacting the game
  state (e.g., ending the game upon collision).

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  1. Transitioning from LinkedList to 2D Array:
    - Initially, switching from a LinkedList to a 2D array for managing the Snake's body presented complexities.
    - The 2D array structure offered better efficiency and suitability, despite initial challenges.
    - This transition highlighted the significance of selecting appropriate data structures, providing valuable insights into their implementations.

  2. Collision Logic Implementation:
    - The initial approach of directly testing collisions for the snake's interaction with its own body encountered difficulties.
    - By rearranging the movement sequence to shift the tail before the head and focusing on collision prediction rather than direct testing, significant improvements in collision accuracy were achieved.
    - This adjustment refined the collision detection mechanism, enhancing the game's integrity and functionality.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  1. Separation of Functionality:
    - Strengths: The project demonstrates reasonable separation of functionalities among classes like GameEntity, Snake,
    Fruit, and SnakeGame.
    Classes like MenuScreen, HelpPopUp, and CheckeredBackground focus on specific UI-related functionalities, providing
    a clear separation between game logic and user interface components.
    - Refactoring Potential: Despite the general separation, there might be opportunities to further partition
    functionalities, especially concerning collision handling and GUI-related components. Refactoring could enhance
    modularity and code readability.

  2. Private State Encapsulation:
    - Strengths: Private state encapsulation has been relatively effective within classes, limiting direct access to
    internal data and methods where necessary.
    - Classes like Snake, Fruit, and GameEntity encapsulate their state appropriately, restricting access to internal
    properties and methods.
    - Refactoring Potential: There might be instances where certain state variables or methods could be further
    encapsulated to enhance data hiding and maintain stricter access control.

  3. Refactoring Opportunities:
    - Improved Modularity: Consider breaking down complex functionalities within classes into smaller, more manageable
    components, promoting better modularity and re-usability.
    - Enhanced Encapsulation: Review classes to ensure stricter encapsulation by appropriately marking state variables
    and methods as private or protected where necessary.
    - Simplification of Logic: Some complex functionalities, especially in collision detection or movement handling,
    might benefit from refactoring for improved clarity and simplicity.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

* I used the DALL-E AI image generation tool for textures for the snake. Used MS Paint to do the googly eyes and
rotated using Windows Explorer's rotate image button for each head direction.

* Oracle Java Documentation around swing for different layout types:
1. https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
2. https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/CardLayout.html
3. https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
4. https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/GridLayout.html
5. https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/components/layeredpane.html
6. https://docs.oracle.com/javase/8/docs/api/javax/swing/JLayeredPane.html

* W3Schools Introduction to JSON: https://www.w3schools.com/js/js_json_intro.asp
* Baedlung's introduction to serialization/deserialization of Java objects using the Jackson library ObjectMapper:
  https://www.baeldung.com/jackson-object-mapper-tutorial
* Lecture notes
* Google search for "In Java, how to accept a function that takes a single argument and returns void",
  discovered "Consumer" (remembered Runnable from our classes, but was not useful for the particular case of loading
  the game from main menu):
https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/How-to-use-Javas-functional-Consumer-interface-example