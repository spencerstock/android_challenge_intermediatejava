# Intermediate Java Sprint Challenge

## Requirements

Implement your own double click functionality. You will use multiple interfaces as well as the composition and delegate patterns to allow this functionality to be easily added to any view with minimal code.

You'll need the following files:

- Interfaces:
  - `DoubleClickListener`
    - This will be the equivalent of an `onClickListener`
  - `DoubleClickInterface`
    - This will be in charge of setting the `onClickListener` (includes a method called `setOnClickListener`)
- Classes:
  - `DoubleClickHandler`
    - This is where the double click logic will reside. It will implement `DoubleClickInterface` and have a `DoubleClickListener` data member
    - The constructor will need to accept a view (represents the view to be clicked)
  - `DoubleClickView` (Button)
    - This will extend whichever view you want to apply this feature to, I recommend starting by extending a Button view so you get the standard click animations.
    - This will also implement the `DoubleClickInterface`
    - Use the Composition pattern
      - Store an instance of the `DoubleClickHandler` class
    - Use the delegate pattern
      - Instead of adding your own implementation for the `DoubleClickInterface`, call the one implemented by your `DoubleClickHandler` member.

## Tips

- For the double click to register properly, you'll need to intercept the `performClick` method of your custom view. You should then have a method that will spin up a thread which will wait to see if it is clicked a second time.
- My implementation has 2 flags in the Click Handler. One for if a singleClick should be processed and another if the handler is waiting for a second click. The thread that is spun up upon the first click will set the pending flag and then sleep for a set amount of time. Afterward, if the click is still pending, it will call the parent view's `performClick` method to execute a single click.

Finally, the logic in this project can be complex, don't get hung up on it. The most important parts are the things we've covered this week, the interfaces, the inheritance, locking shared resources. Get that done first, then figure out the rest of the logic.
