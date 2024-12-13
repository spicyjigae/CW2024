# GitHub Repository: <https://github.com/spicyjigae/CW2024>

# Compilation Instructions
- Install JDK version 19 or later.
- Open IntelliJ, and click `Open`.
- Navigate to `Downloads`, where the `.zip` will most likely be and open the
`MohamadRodziAimanHafidz_IntelliJ_19` folder.
- In IntelliJ, navigate to `File` > `Project Structure`.
- In the Project tab, set the SDK to the JDK version you installed. Click `Apply` and
`OK`.
- Navigate to `src` > `main` > `java` > `java.com.example.demo` > `controller` > `Main`.
- In IntelliJ, the `Run` button should be the right-facing triangle in the top right 
corner besides the `debug` button.

## Side Note
- On MacOS, make sure there is only one JDK installation or the program will not run.

# Implemented and Working Features
## Horizontal User Movement
- Horizontal (left/right) movement for user for more movement freedom.

## Main Menu Interface
- Main menu interface that allows user to either start the first level or exit the 
game.

## Pause Menu Interface
- Pause menu interface that can be toggled with `ESC` key that allows the user to 
pause/resume gameplay, restart the current level or exit the game.

## Additional LevelTwo
- New level two gameplay with a new `PhantomPlane` plane type that is a faster plane
variant with faster bullet travel.

## Win Game Interface
- Proper win game interface that allows the user to go to the main menu or exit the game.

## Game Over Interface
- Proper game over interface that allows the user to go to the main menu or exit the game.

# Implemented but Not Working Features
- No features that were implemented were not working properly. The reason most likely for
this is features that aren't working are scrapped completely to maintain a clean 
codebase.

# Features Not Implemented
## Dynamic Resizing 
- Dynamic resizing of window allows the game to be played in multiple resolutions such
as fullscreen, etc. Idea scrapped due to time constraints of the project.

## Boss Health Bar
- Boss health bar allows visualisation of the boss' current health. Idea scrapped due
to time constraints of the project.

## Additional Levels Past Level Two
- Current levels in the game only include Level One, Level Two and Level Boss. Idea
scrapped due to time constraints.

# Java Classes Added
Additions of classes are listed in the order they are added.

### `EventChangeListener.java`
- New interface class in `java.com.example.demo.interfaces` for implementing 
listeners to any event/scene changes.

### `UserControls.java`
- New class in `java.com.example.demo.actors.logic` for containing user control logic
that encompasses gameplay logic that was previously in `LevelParent` class.

### `BossMovementPattern.java`
- New class in `java.com.example.demo.actors.logic` for containing boss movement logic
that was previously in `BossPlane` class.

### `ActorManager.java`
- New class in `java.com.example.demo.levels.logic` for containing actor management logic
in levels that was previously in `LevelParent` class.

### `CollisionHandler.java`
- New class in `java.com.example.demo.levels.logic` for containing collision handling
logic between actors in levels that was previously in `LevelParent` class.

### `TimelineManager.java`
- New class in `java.com.example.demo.levels.logic` for containing timeline management
logic in levels that was previously in `LevelParent` class.

### `SceneState.java`
- New interface class in `java.com.example.demo.interfaces` for implementing different
scenes based on a State design pattern that allows for easy extensibility of scenes.

### `GameplayScene.java`
- New class in `java.com.example.demo.levels.scenes` that implements the `SceneState` 
class for dynamically creating gameplay scenes for each level.

### `SceneManager.java`
- New class in `java.com.example.demo.levels.logic` for managing the changing between 
scenes that implements both State and Singleton design patterns.

### `WinGameScene.java`
- New class in `java.com.example.demo.levels.scenes` that implements the `SceneState` 
class for creating the WinGame scene.

### `GameOverScene.java`
- New class in `java.com.example.demo.levels.scenes` that implements the `SceneState` 
class for creating the GameOver scene.

### `SceneType.java`
- New enum class in `java.com.example.demo.levels.scenes` for differentiating between
static scenes, e.g. `WinGameScene` and `GameOverScene`.

### `ScoutPlane.java`
- New class in `java.com.example.demo.actors.planes` for previous EnemyPlane actor
initialization.

### `MainMenuScene.java`
- New class in `java.com.example.demo.levels.scenes` that implements the `SceneState`
class for creating the Main Menu interface.

### `PauseMenuManager.java`
- New class in `java.com.example.demo.levels.logic` for containing game pausing logic.

### `EnemyFactory.java`
- New class in `java.com.example.demo.levels.logic` for creating enemy planes that
implements a Factory design pattern.

### `LevelTwo.java`
- Entirely new class separate from original `LevelTwo` in `java.com.example.demo.levels`
for the implementation of the new `LevelTwo` gameplay.

### `PhantomPlane.java`
- New class in `java.com.example.demo.actors.planes` for the new `PhantomPlane` enemy
type in `LevelTwo`.

### `PhantomProjectile.java`
- New class in `java.com.example.demo.actors.projectiles` for the unique
implementation of the new `PhantomPlane` enemy type's projectile.

### `ScoutProjectile.java`
- New class in `java.com.example.demo.actors.projectiles` for the unique
implementation of `ScoutPlane`'s projectile.

# Java Classes Modified
For each class, changes are listed in the order they are made.

## Debug
### `ShieldImage.java`
- Modified `IMAGE_NAME` string to properly match the path of
`shield.png`.
- Modified `getResource()` method's parameters to the `IMAGE_NAME` string.

### `LevelParent.java`
- Added `user.destroy()` method to clear previous level's resources 
(user), under the `goToNextLevel()` method.

### `BossPlane.java` formerly `Boss.java`
- Modified `Y_POSITION_UPPER_BOUND` and `Y_POSITION_LOWER_BOUND` to avoid boss plane
going out of bounds.
- Declared and instantiated a `ShieldImage` object in the constructor method.
- Modified the `updateShield()` method to include the `showShield()` method 
from the `ShieldImage` class when the shield is activated.
- Modified the `updateShield()` method to include the `hideShield()` method
from the `ShieldImage` class when the shield is deactivated.
- Added a new `getShieldImage()` method that returns the `ShieldImage` object.

### `LevelBoss` formerly `LevelTwo.java`
- Added a method `getRoot().getChildren().add(boss.getShieldImage())` that retrieves
the `ShieldImage` instance from `boss`, adds it as an element in the top-level
JavaFX container `Group` declared as `root` in superclass `LevelParent`.

### `UserPlane.java`
- Modified `Y_POSITION_UPPER_BOUND` and `Y_POSITION_LOWER_BOUND` to avoid user plane
going out of bounds.

## New Features
### `UserPlane.java`
- Renamed `VERTICAL_VELOCITY` to `VELOCITY` to allow use for both horizontal and
vertical movement.
- Divided `velocityMultiplier` into `verticalVelocityMultiplier` and
`horizontalVelocityMultiplier`.
- Added horizontal movement logic in `updatePosition()` method.
- Added `moveLeft()` method for moving user plane to the left.
- Added `moveRight()` method for moving user plane to the right.
- Divided `stop()` method into `stopVertical()` and `stopHorizontal` methods for
separate handling.

### `Controller.java`
- Modified `launchGame()` method to set the current scene state to `MainMenuScene`
instead of `GameplayScene` to bring the player to the main menu instead of
starting `LevelOne` right away.
- Modified `launchGame()` method to no longer directly create a new `MainMenuScene`
object and instead calls `onEventChange()` method of `sceneManager` class.

### `LevelParent.java`
- Declared and instantiated a `PauseMenuManager` object in the constructor method.
- Added new `togglePause()` method that calls the `togglePause()` method of the
`PauseMenuManager` class that handles the game pausing logic.

### `EnemyPlane.java`
- Abstracted `fireProjectile()` method as different enemy types now can return
different projectile types.

### `EnemyProjectile.java`
- Removed `HORIZONTAL_VELOCITY` field as different projectile types can now
vary in speed.

## Refactor
### `UserPlane.java`
- Modified `IMAGE_HEIGHT` integer to a smaller number after cropping the
negative space of `userplane.png` to avoid the user plane appearing bigger than
necessary.
- Modified `PROJECTILE_Y_POSITION_OFFSET` and `PROJECTILE_X_POSITION_OFFSET` to an
appropriate position relative to the plane.
- Renamed `PROJECTILE_X_POSITION` to `PROJECTILE_X_POSITION_OFFSET` for consistency.
- Modified `fireProjectile()` method to call `getProjectileXPosition()` with newly
renamed `PROJECTILE_X_POSITION_OFFSET` string.
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Modified `incrementKillCount()` method to take in a parameter of type `int` to
denote how many kills to increment.
- Modified `updatePosition()` method to be `protected` to impose encapsulation.

### `EnemyPlane.java`
- Modified `IMAGE_HEIGHT` integer to a smaller number after cropping the
negative space of `enemyplane.png` to avoid the enemy plane appearing bigger 
than necessary.
- Modified `PROJECTILE_Y_POSITION_OFFSET` and `PROJECTILE_X_POSITION_OFFSET` to an
appropriate position relative to the plane.
- Renamed `projectileYPostion` to `projectileYPosition` to improve clarity.
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Removed duplicate `updatePosition()` method that is also found in other subclasses
of `ActiveActor` which has no extra implementation.
- Removed original static final `HORIZONTAL_VELOCITY` field.
- Added field initialization for `HORIZONTAL_VELOCITY` in the constructor method.
- Abstracted class to streamline addition of new enemy plane types by removing
`IMAGE_NAME`, `IMAGE_HEIGHT`, `INITIAL_HEALTH`, `FIRE_RATE` and `HORIZONTAL_VELOCTIY`
fields that can be set in the concrete subclasses instead.
- Previous EnemyPlane actor is now known as ScoutPlane in new `ScoutPlane` class.

### `BossPlane.java` formerly `Boss.java`
- Modified `IMAGE_HEIGHT` integer to a smaller number after cropping the
negative space of `bossplane.png` to avoid the boss plane appearing bigger than
necessary.
- Modified the override of `updateActor()` method to call `Boss`' superclass,
`ActiveActorDestructible`'s `updateActor()` method before adding extra
implementation, i.e. `updatePosition()` method.
- Renamed class name to `BossPlane.java`.
- Removed `initializeMovePattern()` and `getNextMove()` methods to be moved to the
`BossMovementPattern` class to adhere to the Single Responsibility Principle.
- Removed `consecutiveMovesInSameDirection` field which was only used in the
`getNextMove()` method.
- Removed `indexofCurrentMove` field which was only used in the `getNextMove()`
method.
- Removed `movePattern` list as it is no longer applicable.
- Removed unnecessary field `ZERO` whose sole purpose was to hold the value 0.
- Declared and instantiated a `BossMovementPattern` object in the constructor
method.
- Modified `updatePosition()` method to be `protected` to impose encapsulation.
- Moved hardcoding of initial positions to `LevelBoss` class.

### `UserProjectile.java`
- Modified `IMAGE_HEIGHT` integer to a smaller number after cropping the
negative space of `enemyFire.png` to avoid the user projectile appearing bigger
than necessary.
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Removed duplicate `updatePosition()` method that is also found in other subclasses
of `ActiveActor` which has no extra implementation.
- Removed original static final `HORIZONTAL_VELOCITY` field.
- Added field initialization for `HORIZONTAL_VELOCITY` in the constructor method.

### `EnemyProjectile.java`
- Modified `IMAGE_HEIGHT` integer to a smaller number after cropping the
negative space of `enemyFire.png` to avoid the enemy projectile appearing bigger 
than necessary.
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Removed duplicate `updatePosition()` method that is also found in other subclasses
of `ActiveActor` which has no extra implementation.F
- Removed original static final `HORIZONTAL_VELOCITY` field.
- Added field initialization for `HORIZONTAL_VELOCITY` in the constructor method.

### `ActiveActorDestructible.java`
- Removed the `setDestroyed()` method and modified `destroy()` method to directly 
set the `isDestroyed` field to be true when it is called.
- Removed the `updatePosition()` abstract method override as it has no extra
implementation.
- Added a concrete `updateActor()` method that can be reused in its subclasses.
- Added `this` keyword in front of the `isDestroyed` field initialization in the
constructor for clarity and minimizing ambiguity.
- Removed previously added `updateActor()` method to be moved up to its superclass
`ActiveActor`.
- Added `takeDamage()` method implementation from its subclass `Projectile` for
reusability and minimizing code duplication.

### `Controller.java`
- Modified `Controller` class to implement from new `LevelChangeListener` interface
class instead of deprecated `Observer` class. 
- Modified `goToLevel()` method to call `addListener()` method instead of
`addObserver()` method.
- Modified `update()` method to `onLevelChange()` method which no longer takes
`Observable` parameters and instead takes a `nextLevel` string.
- `Controller` no longer implements from `LevelChangeListener` as the level changing
implementations have been moved to `SceneManager` class.
- Removed `goToLevel()` methods and `onLevelChange()` methods.
- Declared and instantiated a `SceneManager` object in the constructor method.
- Modified `launchGame()` method to instantiate a new `LevelOne` object and a 
`GameplayScene` object along with calling the `setState()` method of the
`SceneManager` object to set the current scene state to the `GameplayScene` object.
- Removed unnecessary exception throws that are never thrown in `launchGame()`
method.
- Modified `Controller` to no longer directly create a `SceneManager` object and
instead calls `getInstance()` method of the `SceneManager` class to retrieve the
Singleton instance of `SceneManager.`

### `LevelParent.java`
- Modified `LevelParent` class to no longer inherit from `Observable` class.
- Added a List of type `LevelChangeListener` for storing listeners.
- Added `addListener()` method to add a listener to the list when called.
- Added `removeListener()` method to remove a listener from the list when called.
- Added `notifyListeners()` to replace `notifyObservers()` to notify listeners
when a change to the next level is detected.
- Modified `goToNextLevel()` method to call `notifyListeners()` instead of
`notifyObservers()`.
- Modified keybindings of `moveUp()` and `moveDown()` methods to `W` and `S`
respectively for one-handed gameplay.
- Declared and instantiated a `UserControls` object in the constructor method.
- Removed `handle()` methods for handling key presses and key releases nested
in the `background.setOnKeyPressed()` and `background.setOnKeyReleased()` methods.
- Removed `fireProjectile()` method for handling projectile firing.
- Added separate `handleKeyPressed()` and `handleKeyReleased()` methods that
directly calls methods defined in `UserControls` class.
- Added lambda expressions in aforementioned `background.setOnKeyPressed()` and 
`background.setOnKeyReleased()` methods that reference the `handleKeyPressed()`
and `handleKeyReleased()` methods.
- Removed unused `import javafx.event.EventHandler`.
- Removed `initializeFriendlyUnits()`, `generateEnemyFire()`, `spawnEnemyProjectile()`,
`updateActors()`, `removedAllDestroyedActors()`, `removeDestroyedActors()`, 
`getCurrentNumberOfEnemies()` and `addEnemyUnit()` methods to be moved to the
`ActorManager` class to adhere to the Single Responsibility Principle.
- Removed `handlePlaneCollisions()`, `handleUserProjectileCollisions()`,
`handleEnemyProjectileCollisions()`, `handleCollisions()`, `handleEnemyPenetration`
and `enemyhasPenetratedDefenses()` methods to be moved to the `CollisionHandler`
class to adhere to the Single Responsibility Principle.
- Removed lists for actor units to be moved to the `ActorManager` class.
- Declared and instantiated `ActorManager` and `CollisionHandler` objects in
the constructor method.
- Modified calls to deleted methods to call new methods from the new classes.
- Revised `updateKillCount()` method to use a simpler and more comprehensible
implementation to replace the previous `for` loop, i.e. an `if` statement that 
checks if there has been enemies destroyed in the last frame through a variable 
`enemiesDestroyed` before incrementing kill count based on aforementioned variable.
- Removed `initializeTimeline()` method to be moved to the `TimelineManager` class
to adhere to the Single Responsibility Principle.
- Modified `startGame()`, `winGame()` and `loseGame` methods to call methods
from the `TimelineManager` class.
- Replaced `initializeScene()` method with a `initializeLevel()` method that no
longer calls `initializeBackground()` and no longer returns a `Scene` object.
- Moved `initializeBackground()` method call to the constructor method.
- Added a `stopLevel()` method that properly clears the resources of the level by
stopping the timeline, clearing all elements and setting the background image to null
before progressing to the next level.
- Removed redundant `startGame()` method that calls to start the timeline as that
is now directly called in `initializeLevel()`.
- Replaced `showWinImage()` and `showGameOverImage()` method calls in `winGame()` and
`loseGame()` methods with `notifyListeners()` methods to induce change to respective
scenes.
- Modified `instantiateLevelView()` method to no longer be abstract.
- Declared and instantiated a new `playerInitialHealth` instance variable.
- Modified `getClass().getResource()` method to require the resource to be non-null
by adding `Objects.requireNonNull()` at the front.
- Modified lambda expressions in `initializeBackground()` method to method references
for clarity.
- Modified `goToNextLevel()`, `winGame()` and `loseGame()` methods to no longer
handle clearing resources such as calling `timelineManager` to stop the
timeline or destroying the user's resources as this is handled in the `exitScene()`
method of each scene's implementation.
- Declared and initialized a `spawnTimeline` object that handles the delay
between spawning enemies.
- Added `startSpawning()` and `stopSpawning()` methods to control `spawnTimeline`.

### `LevelView.java`
- Renamed `LOSS_SCREEN_Y_POSISITION` to `LOSS_SCREEN_Y_POSITION` for clarity.
- Removed `WinImage` and `GameOverImage` objects that used to be added on top
of current scenes.
- Removed `showWinImage()` and `showGameOverImage()` methods to replace
images of `WinGame` and `GameOver` with separate scenes.

### `LevelViewLevelTwo.java` (Deleted)
- Removed duplicate `showShield()` method whose sole purpose is to call
`showShield()` method from `ShieldImage` class which is unnecessary as the 
`Boss` class already directly calls it in the `updateShield()` method.
- Removed duplicate `hideShield()` method whose sole purpose is to call
`hideShield()` method from `ShieldImage` class which is unnecessary as the
`Boss` class already directly calls it in the `updateShield()` method.
- Class ultimately deleted due to redundancy in the way that it does not serve a 
purpose different to `LevelView` class.

### `BossProjectile.java`
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Removed duplicate `updatePosition()` method that is also found in other subclasses
of `ActiveActor` which has no extra implementation.
- Removed original static final `HORIZONTAL_VELOCITY` field.
- Added field initialization for `HORIZONTAL_VELOCITY` in the constructor method.

### `Projectile.java`
- Removed duplicate `updateActor()` method that is also found in other subclasses of
`ActiveActorDestructible` which has no extra implementation.
- Removed `takeDamage()` method to be moved up to its superclass
`ActiveActorDestructible` as the implementation is similar to those in `Plane`
classes.

### `ActiveActor.java`
- Added `updatePosition()` method with concrete implementation for reusability in 
its subclasses.
- Added new `protected` field `HORIZONTAL_VELOCITY` that can be accessed by its
subclasses.
- Added `updateActor()` method from its subclass `ActiveActorDestructible`.
- Modified `getClass().getResource()` method to require the resource to be non-null
by adding `Objects.requireNonNull()` at the front.
- Modified `updatePosition()` method to be `protected` to impose encapsulation.

### `Plane.java` formerly `FighterPlane.java`
- Renamed class name to `FighterPlane.java`.
- In the `takeDamage()` method override, added `this` keyword for consistency.
- Replaced previous `takeDamage()` method implementation with a call to its
superclass' `takeDamage()` method.

### `Main.java`
- Removed unnecessary exception throws that are never thrown in `start()`
method.

### `LevelOne.java`
- Removed `instantiateLevelView()` method implementation as the method has been
modified to be a concrete method in the `LevelParent` superclass.
- Abstracted `userHasReachedKillTarget()` method and `KILLS_TO_ADVANCE` field to
`LevelParent` superclass for reusability in future levels.
- Fixed issue where enemy planes kept spawning past `TOTAL_ENEMIES` count when an
enemy is killed.
- Removed direct enemy initialization and instead calls the `createEnemy()` method of
the new `EnemyFactory` class that handles enemy creation.
- Implementation of `spawnTimeline` timeline object in `LevelParent` makes it so
the enemies no longer spawn at the same time, therefore negating the possibility of
them overlapping.

### `LevelBoss` formerly `LevelTwo.java`
- Removed `instantiateLevelView()` method implementation as the method has been
modified to be a concrete method in the `LevelParent` superclass.
- Renamed class name to `LevelBoss.java`.
- Removed direct enemy initialization and instead calls the `createEnemy()` method of
the new `EnemyFactory` class that handles enemy creation.

### `WinImage.java` (Deleted)
- Class deleted as there is no longer a use for WinImage as per the addition of 
proper scenes separate from the levels itself.

### `GameOverImage.java` (Deleted)
- Class deleted as there is no longer a use for GameOverImage as per the addition
of proper scenes separate from the levels itself.

# Unexpected Problems
## Time Management
- Experienced trouble in balancing time between coursework of other modules. As a
result, I started the DMS coursework reasonably later than I should've. On top of
that, I spent way too much time on refactoring the code compared to implementing new
features.
